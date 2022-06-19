package it.prova.gestionepermessi.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.MessaggioDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Messaggio;
import it.prova.gestionepermessi.service.MessaggioService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;

@Controller
@RequestMapping(value = "/messaggio")
public class MessaggioController {

	@Autowired
	private MessaggioService messaggioService;
	
	@Autowired
	private RichiestaPermessoService richiestaPermessoService;
	
	@GetMapping
	public ModelAndView listAllMessaggi() {
		ModelAndView mv = new ModelAndView();
		List<Messaggio> messaggi = messaggioService.listAllMessaggi();
		mv.addObject("messaggio_list_attribute", messaggi);
		mv.setViewName("messaggio/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchMessaggio(Model model) {
		model.addAttribute("richieste_list_attribute",RichiestaPermessoDTO.createRichiestaPermessoDTOListFromModelList(richiestaPermessoService.listAll()));
		model.addAttribute("insert_messaggio_attr", new MessaggioDTO());
		return "messaggio/search";
	}

	@PostMapping("/list")
	public String listMessaggi(MessaggioDTO messaggioExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {

		List<Messaggio> messaggi = messaggioService
				.findByExample(messaggioExample.buildMessaggioModel(), pageNo, pageSize, sortBy).getContent();

		model.addAttribute("messaggio_list_attribute", MessaggioDTO.createMessaggioDTOListFromModelList(messaggi));
		return "messaggio/list";
	}
	
	
	@GetMapping("/show/{idMessaggio}")
	public String show(@PathVariable(required = true) Long idMessaggio, Model model) {
		Messaggio messDaVisualizzare = messaggioService.caricaSingoloMessaggio(idMessaggio);
		
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
		 Date today = (Date) calendar.getTime();
		 messDaVisualizzare.setDataLettura(today);
		 messDaVisualizzare.setLetto(true);
		 messaggioService.aggiorna(messDaVisualizzare);
		
		model.addAttribute("show_messaggio_attr", messDaVisualizzare);
		model.addAttribute("show_messaggio_richiestaPermesso_attr", RichiestaPermessoDTO.buildRichiestaPermessoDTOFromModel(messDaVisualizzare.getRichiestaPermesso()));
		model.addAttribute("show_messaggio_dipendente_attr", DipendenteDTO.buildDipendenteDTOFromModel(messDaVisualizzare.getRichiestaPermesso().getDipendente()));
		return "messaggio/show";
	}
	
	@GetMapping("/listMessaggiNonLetti")
	public ModelAndView listAllMessaggiNonLetti() {
		ModelAndView mv = new ModelAndView();
		List<Messaggio> messaggi = messaggioService.listAllMessaggiNonLetti();
		mv.addObject("messaggio_list_attribute", messaggi);
		mv.setViewName("messaggio/list");
		return mv;
	}
}
