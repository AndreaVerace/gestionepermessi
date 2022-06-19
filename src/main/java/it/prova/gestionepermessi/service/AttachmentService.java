package it.prova.gestionepermessi.service;

import it.prova.gestionepermessi.model.Attachment;

public interface AttachmentService {

	public Attachment caricaSingoloAttachment(Long id);

	public void delete(Attachment attachment);
	
}
