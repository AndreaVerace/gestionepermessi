package it.prova.gestionepermessi.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.prova.gestionepermessi.model.Utente;
import it.prova.gestionepermessi.service.UtenteService;


@Controller
@RequestMapping(value = "/pwd")
public class ResetPasswordController {

	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/password")
	public String passwordForm() {
		return "formpwd";
	}
	
	
	@RequestMapping(value = "/resetPassword", method = {RequestMethod.POST,RequestMethod.GET})
	public String resetPassword(@RequestParam(value = "vecchiaPassword", required = true) String vecchiaPassword,
			@RequestParam(value = "nuovaPassword", required = true) String nuovaPassword,
			@RequestParam(value = "confermaPassword", required = true) String confermaPassword,
			Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			model.addAttribute("errorePwd","Errore! ");
			return "index";
		}
		
		Utente utente = utenteService.findByUsername(auth.getName());
		if (utente == null || !passwordEncoder.matches(vecchiaPassword,utente.getPassword())) {
			model.addAttribute("errorePwd","Errore su vecchia password! ");
			return "index";
		}
		
		if (nuovaPassword.equals(confermaPassword)) {
			utente.setPassword(passwordEncoder.encode(nuovaPassword));
			utenteService.aggiorna(utente);
		}
		else {
			model.addAttribute("errorePwd","Errore su nuova o conferma password! ");
			return "index"; 
		}
		
		model.addAttribute("infoMessage","Password cambiata correttamente.");
		return "redirect:/logout";

	}
	
}
