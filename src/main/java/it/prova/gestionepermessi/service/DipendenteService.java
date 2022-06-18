package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepermessi.model.Dipendente;

public interface DipendenteService {

	public void inserisciNuovo(Dipendente dipendente);
	
	public void aggiorna(Dipendente dipendenteInstance);
	
	public List<Dipendente> listAllUtenti();
	
	public Page<Dipendente> findByExample(Dipendente example, Integer pageNo, Integer pageSize, String sortBy);
	
	public Dipendente caricaSingoloDipendente(Long id);
	
	public Dipendente caricaSingoloDipendenteConRichieste(Long id);
	
	public Dipendente  caricaSingoloDipendenteConUtente(Long id);

	public Dipendente cercaPerUsername(String username);
	
}
