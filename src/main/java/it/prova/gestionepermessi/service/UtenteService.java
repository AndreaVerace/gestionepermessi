package it.prova.gestionepermessi.service;

import it.prova.gestionepermessi.model.Utente;

public interface UtenteService {

	public Utente caricaSingoloUtente(Long id);
	
	public void inserisciNuovo(Utente utenteInstance);
	
	public void aggiorna(Utente utenteInstance);
	
	public Utente findByUsernameAndPassword(String username, String password);
	
	public void changeUserAbilitation(Long utenteInstanceId);
	
	public Utente findByUsername(String username);
	
}
