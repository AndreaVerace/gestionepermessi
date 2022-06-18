package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.repository.dipendente.DipendenteRepository;
import it.prova.gestionepermessi.repository.utente.UtenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService {

	@Autowired
	private DipendenteRepository repository;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public void inserisciNuovo(Dipendente dipendente) {
		repository.save(dipendente);
		
	}

	@Override
	public List<Dipendente> listAllUtenti() {
		return (List<Dipendente>) repository.findAll();
	}

	@Override
	public Page<Dipendente> findByExample(Dipendente example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Dipendente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates
						.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));
			
			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates
						.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));
			
			if (StringUtils.isNotEmpty(example.getCodiceFiscale()))
				predicates
						.add(cb.like(cb.upper(root.get("codiceFiscale")), "%" + example.getCodiceFiscale().toUpperCase() + "%"));
			
			if (StringUtils.isNotEmpty(example.getEmail()))
				predicates
						.add(cb.like(cb.upper(root.get("email")), "%" + example.getEmail().toUpperCase() + "%"));

			if (example.getSesso() != null)
				predicates.add(cb.equal(root.get("sesso"), example.getSesso()));

			if (example.getDataNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataNascita"), example.getDataNascita()));
			
			if (example.getDataAssunzione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataAssunzione"), example.getDataAssunzione()));
			
			if(!example.getRichiestePermesso().isEmpty()) {
				predicates.add(root.join("richiestePermesso").in(example.getRichiestePermesso()));
			}
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

	@Override
	public Dipendente caricaSingoloDipendente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Dipendente caricaSingoloDipendenteConRichieste(Long id) {
		return repository.findByIdConRichieste(id).orElse(null);
	}

	@Override
	public void aggiorna(Dipendente dipendenteInstance) {
		Dipendente dipendenteReloaded = repository.caricaSingoloDipendenteConUtente(dipendenteInstance.getId()).orElse(null);
		if(dipendenteReloaded == null || dipendenteReloaded.getUtente() == null)
			throw new RuntimeException("Elemento non trovato");
		
		dipendenteInstance.setUtente(dipendenteReloaded.getUtente());
		
		dipendenteInstance.getUtente().setUsername(dipendenteReloaded.getNome().substring(0, 1) + "." + dipendenteReloaded.getCognome());
		dipendenteInstance.setEmail(dipendenteReloaded.getUtente().getUsername() + "@prova.it");
		
		utenteService.aggiorna(dipendenteInstance.getUtente());
		
		repository.save(dipendenteInstance);
		
	}

	@Override
	public Dipendente caricaSingoloDipendenteConUtente(Long id) {
		return repository.caricaSingoloDipendenteConUtente(id).orElse(null);
	}

	@Override
	public Dipendente cercaPerUsername(String username) {
		return repository.cercaPerUsername(username);
	}

}
