package it.prova.gestionepermessi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.repository.ruolo.RuoloRepository;

@Service
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Override
	public Ruolo caricaSingoloElemento(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
		
	}

	@Transactional(readOnly = true)
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		return ruoloRepository.findByDescrizioneAndCodice(descrizione, codice);
	}

	@Override
	public List<Ruolo> listAll() {
		return (List<Ruolo>)ruoloRepository.findAll();
	}

}
