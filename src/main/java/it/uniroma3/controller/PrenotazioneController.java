package it.uniroma3.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import it.uniroma3.controller.validator.PrenotazioneValidator;
import it.uniroma3.model.Campo;
import it.uniroma3.model.Prenotazione;
import it.uniroma3.model.Utente;
import it.uniroma3.service.CampoService;
import it.uniroma3.service.PrenotazioneService;
import it.uniroma3.service.UtenteService;

@Controller
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private CampoService campoService;
	
	@Autowired
	private PrenotazioneValidator validator;
	
	@Autowired
	private UtenteService utenteService;


	@RequestMapping("/prenotazioni")
	public String prenotazioniAdmin(Model model) {
		model.addAttribute("prenotazioni", this.prenotazioneService.findAll());
		model.addAttribute("utenti", this.utenteService.findAll());
		return "admin";
	}
	
	@RequestMapping("/addPrenotazione/{id}")
	public String addPrenotazione(@RequestParam("quando")String quando, @PathVariable("id") Long idUtente, @RequestParam(name = "campoSelect") Long idCampo, Model model) {
		int anno = Integer.parseInt(quando.substring(0, 4));
		int mese = Integer.parseInt(quando.substring(5, 7));
		int giorno = Integer.parseInt(quando.substring(8, 10));
		System.out.println(giorno + " " + mese + " " + anno);
		Date data = new Date(anno-1900, mese-1, giorno);
		Campo campo = campoService.findById(idCampo);
		Utente utenteCorrente = this.utenteService.findById(idUtente);
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setCampo(campo);
		prenotazione.setUtente(utenteCorrente);
		prenotazione.setData(data);
		prenotazioneService.save(prenotazione);
		return "redirect:/benvenuto";
	}
	
	@RequestMapping(value = "/prenotazioni/{id}", method = RequestMethod.GET)
	public String getUtentePrenotazione(@PathVariable("id") Long idUtente, Model model) {
		Utente utenteCorrente = this.utenteService.findById(idUtente);
		List<Prenotazione> prenotazioni = this.prenotazioneService.findByUtente(utenteCorrente);
		System.out.println(prenotazioni.size());
		model.addAttribute("prenotazioni" , prenotazioni);
		return "prenotazioniList";
	}

	@RequestMapping(value = "/prenotazione", method = RequestMethod.POST)
	public String newPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, 
			Model model, BindingResult bindingResult) {
		this.validator.validate(prenotazione, bindingResult);
		if (this.prenotazioneService.alreadyExists(prenotazione)) {
			model.addAttribute("exists", "Prenotazione already exists");
			return "prenotazioneForm";
		}
		else {
			if (!bindingResult.hasErrors()) {
				this.prenotazioneService.save(prenotazione);
				model.addAttribute("prenotazioni", this.prenotazioneService.findAll());
				return "prenotazioniList";
			}
		}
		return "prenotazioneForm";
	}

}