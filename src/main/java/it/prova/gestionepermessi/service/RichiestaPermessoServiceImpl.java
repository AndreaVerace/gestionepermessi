package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.repository.richiestaPermesso.RichiestaPermessoRepository;

@Service
public class RichiestaPermessoServiceImpl implements RichiestaPermessoService {

	@Autowired
	private RichiestaPermessoRepository repository;
	
	@Override
	public List<RichiestaPermesso> listAll() {
		return (List<RichiestaPermesso>) repository.findAll();
	}

	@Override
	public RichiestaPermesso caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

}
