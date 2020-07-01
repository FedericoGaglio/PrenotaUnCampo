package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.service.UtenteService;

@Controller
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@RequestMapping("/utenti")
	public String utenti(Model model) {
		model.addAttribute("utenti", this.utenteService.findAll());
		return "admin";
	}

	@RequestMapping(value = "/utente/{id}", method = RequestMethod.GET)
	public String getUtente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("utente", this.utenteService.findById(id));
		return "showUtente";
	}
}