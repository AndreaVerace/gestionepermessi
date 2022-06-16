package it.prova.gestionepermessi.service;

import java.util.List;

import it.prova.gestionepermessi.model.RichiestaPermesso;


public interface RichiestaPermessoService {

	public List<RichiestaPermesso> listAll();
	
	public RichiestaPermesso caricaSingoloElemento(Long id);
	
}
