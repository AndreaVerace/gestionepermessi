package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();
	
	public Utente caricaSingoloUtente(Long id);
	
	public Utente caricaSingoloUtenteConRuoli(Long id);
	
	public void inserisciNuovo(Utente utenteInstance);
	
	public void aggiorna(Utente utenteInstance);
	
	public Utente findByUsernameAndPassword(String username, String password);
	
	public void changeUserAbilitation(Long utenteInstanceId);
	
	public Utente findByUsername(String username);

	public Page<Utente> findByExample(Utente example, Integer pageNo, Integer pageSize, String sortBy);
	
	public List<Utente> findByRuolo(String codice);
	
	public Utente cercaPerUsername(String username);
	
}
