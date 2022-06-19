package it.prova.gestionepermessi.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.repository.messaggio.MessaggioRepository;

@Service
public class MessaggioServiceImpl implements MessaggioService {

	@Autowired
	private MessaggioRepository repository;
	
	@Override
	public void inserisciNuovo(Messaggio messaggio) {
		repository.save(messaggio);
		
	}

	@Override
	public List<Messaggio> listAllMessaggi() {
		return (List<Messaggio>) repository.findAll();
	}

	@Override
	public Page<Messaggio> findByExample(Messaggio example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Messaggio> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getTesto()))
				predicates
						.add(cb.like(cb.upper(root.get("testo")), "%" + example.getTesto().toUpperCase() + "%"));
			
			if (StringUtils.isNotEmpty(example.getOggetto()))
				predicates
						.add(cb.like(cb.upper(root.get("oggetto")), "%" + example.getOggetto().toUpperCase() + "%"));
			
			if (example.getDataInserimento() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataInserimento"), example.getDataInserimento()));
			
			if (example.getDataLettura() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataLettura"), example.getDataLettura()));
			
			if(example.getRichiestaPermesso() != null) {
				predicates.add(root.join("richiestaPermesso").in(example.getRichiestaPermesso()));
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
	public Messaggio caricaSingoloMessaggio(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Messaggio messaggio) {
		Messaggio messReloaded = repository.findById(messaggio.getId()).orElse(null);
		if(messReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		
		repository.save(messReloaded);
		
	}

	@Override
	public Long contaMessaggiNonLetti() {
		return repository.countByLetto(false);
	}

	@Override
	public List<Messaggio> listAllMessaggiNonLetti() {
		return repository.findAllByLettoIs(false);
	}
	
}
