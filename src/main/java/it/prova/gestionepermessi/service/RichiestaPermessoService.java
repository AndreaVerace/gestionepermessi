package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import it.prova.gestionepermessi.model.RichiestaPermesso;


public interface RichiestaPermessoService {

	public List<RichiestaPermesso> listAll();
	
	public RichiestaPermesso caricaSingoloElemento(Long id);
	
	public Page<RichiestaPermesso> findByExample(RichiestaPermesso example, Integer pageNo, Integer pageSize, String sortBy);
	
	public void inserisciNuovo(RichiestaPermesso richiestaPermesso,MultipartFile multipartFile);
}
