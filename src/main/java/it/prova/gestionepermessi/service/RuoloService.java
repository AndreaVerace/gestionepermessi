package it.prova.gestionepermessi.service;

import it.prova.gestionepermessi.model.Ruolo;

public interface RuoloService {

	public Ruolo caricaSingoloElemento(Long id) ;
	
	public void inserisciNuovo(Ruolo ruoloInstance) ;
	
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) ;
	
}
