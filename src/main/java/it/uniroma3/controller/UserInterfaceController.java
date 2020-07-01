package it.uniroma3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import it.uniroma3.model.Campo;
import it.uniroma3.model.Prenotazione;
import it.uniroma3.service.CampoService;

@Controller
public class UserInterfaceController {
	
	
	@Autowired
	CampoService campoService;
	
	@RequestMapping("/index")
	public String index(Model model)
	{
		return "index";
	}
	
	@RequestMapping("/chisiamo")
	public String chiSiamo(Model model)
	{
		return "chisiamo";
	}
	
	@RequestMapping("/circoli")
	public String circoli(Model model)
	{
		return "circoli";
	}	
		
	@RequestMapping("/benvenuto")
	public String benvenuto(Model model)
	{
		List<Campo> listaCampi = campoService.findAll();
		model.addAttribute("campi", listaCampi);
		model.addAttribute("prenotazione", new Prenotazione());
		System.out.println("LUNGHEZZA = " + listaCampi.size());
		return "benvenuto";
	}	
}