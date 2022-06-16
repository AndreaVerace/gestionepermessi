package it.prova.gestionepermessi;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.Sesso;
import it.prova.gestionepermessi.model.StatoUtente;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RuoloService;
import it.prova.gestionepermessi.service.UtenteService;

@SpringBootApplication
public class GestionepermessiApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private DipendenteService dipendenteServiceInstance;
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionepermessiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("BO User", "ROLE_BO_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("BO User", "ROLE_BO_USER"));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della password non lo 
		//faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", new Date());
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			Dipendente adminDipendente = new Dipendente("Mario","Rossi","MRRSS70","m.rossi@prova.it",new SimpleDateFormat("yyyy-MM-dd").parse("1970-08-08"),new SimpleDateFormat("yyyy-MM-dd").parse("1997-09-18"),Sesso.MASCHIO,admin);
			
			
			utenteServiceInstance.inserisciNuovo(admin);
			dipendenteServiceInstance.inserisciNuovo(adminDipendente);
			
			admin.setDipendente(adminDipendente);
			utenteServiceInstance.aggiorna(admin);
			
			//l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("bo_user") == null) {
			Utente boUser = new Utente("bo_user", "bo_user", new Date());
			boUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("BO User", "ROLE_BO_USER"));
			Dipendente boUserDipendente = new Dipendente("Antonio","Longhi","NTLNG70","a.longhi@prova.it",new SimpleDateFormat("yyyy-MM-dd").parse("1970-08-08"),new SimpleDateFormat("yyyy-MM-dd").parse("1997-09-18"),Sesso.MASCHIO,boUser);
			
			
			utenteServiceInstance.inserisciNuovo(boUser);
			dipendenteServiceInstance.inserisciNuovo(boUserDipendente);
			
			boUser.setDipendente(boUserDipendente);
			utenteServiceInstance.aggiorna(boUser);
			//l'inserimento avviene come created ma io voglio attivarlo
			utenteServiceInstance.changeUserAbilitation(boUser.getId());
		}

		
	}

}
