package it.prova.gestionepermessi.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionepermessi.dto.DipendenteDTO;
import it.prova.gestionepermessi.dto.RichiestaPermessoDTO;
import it.prova.gestionepermessi.model.Dipendente;
import it.prova.gestionepermessi.model.RichiestaPermesso;
import it.prova.gestionepermessi.model.Ruolo;
import it.prova.gestionepermessi.model.TipoPermesso;
import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.DipendenteService;
import it.prova.gestionepermessi.service.RichiestaPermessoService;
import it.prova.gestionepermessi.service.UtenteService;

@Controller
@RequestMapping(value = "/richiestaPermesso")
public class RichiestaPermessoController {

	@Autowired
	private DipendenteService dipendenteService;
	
	@Autowired
	private RichiestaPermessoService richiestaPermessoService;
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping
	public ModelAndView listAllRichiestePermesso() {
		ModelAndView mv = new ModelAndView();
		List<RichiestaPermesso> richiestePermesso = richiestaPermessoService.listAll();
		mv.addObject("richiestaPermesso_list_attribute", richiestePermesso);
		mv.setViewName("richiestaPermesso/list");
		return mv;
	}

	@GetMapping("/search")
	public String searchRichiestaPermesso(Model model) {
		model.addAttribute("search_richiestaPermesso_attr", new RichiestaPermessoDTO());
		model.addAttribute("search_dipendenti_list",DipendenteDTO.createDipendenteDTOListFromModelList(dipendenteService.listAllUtenti()));
		return "richiestaPermesso/search";
	}

	@PostMapping("/list")
	public String listUtenti(RichiestaPermessoDTO richiestaExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {
				
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Utente u = utenteService.findByUsername(auth.getName());
		
		List<RichiestaPermesso> richiestePermesso = null;
		
		if(u.isBO()) {
		 richiestePermesso  = richiestaPermessoService
				.findByExample(richiestaExample.buildRichiestaPermessoModel(), pageNo, pageSize, sortBy).getContent();
		}
		
		if(u.isDipendente()) {
			richiestePermesso = richiestaPermessoService.trovaRichiesteUtente(Ruolo.ROLE_DIPENDENTE_USER, u.getUsername());
		}
		
		model.addAttribute("richiestaPermesso_list_attribute", RichiestaPermessoDTO.createRichiestaPermessoDTOListFromModelList(richiestePermesso));
		return "richiestaPermesso/list";
	}
	
	@GetMapping("/insert")
	public String createRichiestaPermesso(Model model) {
		model.addAttribute("insert_richiestaPermesso_attr", new RichiestaPermessoDTO());
		return "richiestaPermesso/insert";
	}

	@PostMapping("/save")
	public String saveRichiestaPermesso(@Valid @ModelAttribute("insert_richiestaPermesso_attr") RichiestaPermessoDTO richiestaPermessoDTO,Model model, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			model.addAttribute("errorMessage","Qualcosa è andato storto");
			return "richiestaPermesso/insert";
		}
		
		if(richiestaPermessoDTO.getTipoPermesso().equals(TipoPermesso.MALATTIA) && richiestaPermessoDTO.getCodiceCertificato().isBlank()) {
			model.addAttribute("errorMessage","Il codice certificato deve essere valorizzato in caso di Malattia");
			return "richiestaPermesso/insert";
		}
		
		RichiestaPermesso richiesta = richiestaPermessoDTO.buildRichiestaPermessoModel();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null) {
			throw new RuntimeException("Non sei autenticato");
		}
		
		Dipendente dipendenteLoggato = dipendenteService.cercaPerUsername(auth.getName());
		if (dipendenteLoggato == null) {
			throw new RuntimeException("Non sei autenticato");
		}
		
		
		
		richiesta.setDipendente(dipendenteLoggato);
		
		richiestaPermessoService.inserisciNuovo(richiesta,richiestaPermessoDTO.getAttachment());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richiestaPermesso";
	}
	
	@GetMapping("/show/{idRichiestaPermesso}")
	public String show(@PathVariable(required = true) Long idRichiestaPermesso, Model model) {
		model.addAttribute("show_richiestaPermesso_attr", richiestaPermessoService.caricaSingoloElemento(idRichiestaPermesso));
		return "richiestaPermesso/show";
	}
	
	@GetMapping("/approva/{idRichiestaPermesso}")
	public String approva(@PathVariable(required = true) Long idRichiestaPermesso, Model model,RedirectAttributes redirectAttrs) {
		
		RichiestaPermesso richiestaDaApprovare = richiestaPermessoService.caricaSingoloElemento(idRichiestaPermesso);
		
		richiestaDaApprovare.setApprovato(true);
		
		richiestaPermessoService.aggiorna(richiestaDaApprovare);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/richiestaPermesso";
	}
	
}