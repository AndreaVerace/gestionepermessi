package it.prova.gestionepermessi.service;

import java.io.IOException;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.attachment.AttachmentRepository;
import it.prova.gestionepermessi.repository.richiestaPermesso.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {

	@Autowired
	private RichiestaPermessoRepository repository;
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<RichiestaPermesso> listAll() {
		return (List<RichiestaPermesso>) repository.findAll();
	}

	@Override
	public RichiestaPermesso caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Page<RichiestaPermesso> findByExample(RichiestaPermesso example, Integer pageNo, Integer pageSize,
			String sortBy) {
		Specification<RichiestaPermesso> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getCodiceCertificato()))
				predicates
						.add(cb.like(cb.upper(root.get("codiceCertificato")), "%" + example.getCodiceCertificato().toUpperCase() + "%"));
			
			if (StringUtils.isNotEmpty(example.getNota()))
				predicates
						.add(cb.like(cb.upper(root.get("nota")), "%" + example.getNota().toUpperCase() + "%"));

			if (example.getTipoPermesso() != null)
				predicates.add(cb.equal(root.get("tipoPermesso"), example.getTipoPermesso()));
			
			if(example.getDipendente() != null)
				predicates.add(root.join("dipendente").in(example.getDipendente()));
			
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
	@Transactional
	public void inserisciNuovo(RichiestaPermesso richiestaPermesso,MultipartFile multipartFile) {
		
		
		
		repository.save(richiestaPermesso);
		
		if (multipartFile != null) {
			Attachment newfile = new Attachment();
			newfile.setNomeFile(multipartFile.getOriginalFilename());
			newfile.setContentType(multipartFile.getContentType());
			try {
				newfile.setPayload(multipartFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			newfile.setRichiestaPermesso(richiestaPermesso);
			attachmentRepository.save(newfile);
		}
		
	}

	@Override
	public List<RichiestaPermesso> trovaRichiesteUtente(String codiceRuolo, String username) {
		return repository.trovaRichiesteUtente(codiceRuolo, username);
	}

	@Override
	public void aggiorna(RichiestaPermesso richiesta) {
		RichiestaPermesso richiestaReloaded = repository.findById(richiesta.getId()).orElse(null);
		if(richiestaReloaded == null)
			throw new RuntimeException("Elemento non trovato");
		
		repository.save(richiestaReloaded);
		
		
	}

	@Override
	public void delete(RichiestaPermesso richiestaPermesso) {
		repository.delete(richiestaPermesso);
		
	}

}
