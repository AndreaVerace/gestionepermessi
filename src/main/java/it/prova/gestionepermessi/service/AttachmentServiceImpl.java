package it.prova.gestionepermessi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.Attachment;
import it.prova.gestionepermessi.repository.attachment.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentRepository repository;
	
	@Override
	public Attachment caricaSingoloAttachment(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void delete(Attachment attachment) {
		repository.delete(attachment);
		
	}

}
