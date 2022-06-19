package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Messaggio;

public interface MessaggioService {

	public void inserisciNuovo(Messaggio messaggio);
	
    public List<Messaggio> listAllMessaggi();
	
	public Page<Messaggio> findByExample(Messaggio example, Integer pageNo, Integer pageSize, String sortBy);
	
	public Messaggio caricaSingoloMessaggio(Long id);
	
	public void aggiorna(Messaggio messaggio);
	
	public Long contaMessaggiNonLetti();

	public List<Messaggio> listAllMessaggiNonLetti();
	
	public Messaggio findByRichiestaPermesso_Id(Long idRichiesta);
	
	public void delete(Messaggio messaggio);
	
}
