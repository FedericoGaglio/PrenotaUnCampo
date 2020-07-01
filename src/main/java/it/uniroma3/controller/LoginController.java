package it.uniroma3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import it.uniroma3.controller.validator.UtenteValidator;
import it.uniroma3.model.Campo;
import it.uniroma3.model.Prenotazione;
import it.uniroma3.model.Utente;
import it.uniroma3.service.CampoService;
import it.uniroma3.service.UtenteService;

@Controller
public class LoginController {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private UtenteValidator validator;

	private static String authorizationRequestBaseUri = "oauth2/authorization";

	Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	CampoService campoService;

	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
		model.addAttribute("user", new Utente());
		return "login";
	}

//	@RequestMapping("/oauth_login")
//	public String loginOauth(Model model, HttpSession session) {
//		model.addAttribute("user", new Utente());
//		return "oauth_login";
//	}

	@RequestMapping("/role")
	public String loginRole(HttpSession session, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String role = auth.getAuthorities().toString();
		Utente user = this.utenteService.findByUsername(auth.getName());
		System.out.println("\n"+user.getUsername()+"\n");

		if(role.contains("ROLE_USER")) {
			session.setAttribute("user", user);
			return "redirect:/benvenuto";
		} else if(role.contains("ROLE_ADMIN")) {
			session.setAttribute("user", user);
			return "redirect:/prenotazioni";
		}
		return "redirect:/benvenuto";
	}

	@RequestMapping("/errore403")
	public String error403() {
		return "errore403";
	}

	@RequestMapping(value = "/registrazioneUtente", method = RequestMethod.POST)
	public String nuovoUtente(@ModelAttribute("user") Utente utente, Model model, BindingResult bindingResult, HttpSession session) {  
		System.out.println("\n\nERROR\n\n");
		this.validator.validate(utente, bindingResult);
		utente.setRole("ROLE_USER");
		if (this.utenteService.alreadyExists(utente)) {
			System.out.println("\n\nERROR\n\n");
			model.addAttribute("message", "Questo username è già presente");
		}
		else {
			if (!bindingResult.hasErrors()) {
				System.out.println("\n\nERROR\n\n");
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				utente.setPassword(bCryptPasswordEncoder.encode(utente.getPassword()));
				utenteService.save(utente);
				session.setAttribute("user", utente);
			}
		}
		return "redirect:/login";
	}

//	@GetMapping("/oauth_login")
//	public String getLoginPage(Model model) {
//		Iterable<ClientRegistration> clientRegistrations = null;
//		ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
//				.as(Iterable.class);
//		if (type != ResolvableType.NONE && 
//				ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
//			clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
//		}
//
//		clientRegistrations.forEach(registration -> 
//		oauth2AuthenticationUrls.put(registration.getClientName(), 
//				authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
//		model.addAttribute("urls", oauth2AuthenticationUrls);
//
//		return "oauth_login";
//	}

	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@GetMapping("/loginSuccess")
	public String getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient client = authorizedClientService
				.loadAuthorizedClient(
						authentication.getAuthorizedClientRegistrationId(), 
						authentication.getName());

		String userInfoEndpointUri = client.getClientRegistration()
				.getProviderDetails().getUserInfoEndpoint().getUri();
		
		Map userAttributes = null;
		
		if (!StringUtils.isEmpty(userInfoEndpointUri)) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
			.getTokenValue());
			HttpEntity entity = new HttpEntity("", headers);
			ResponseEntity <Map>response = restTemplate
					.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
			userAttributes = response.getBody();
			model.addAttribute("name", userAttributes.get("name"));
			
			System.out.println("--------------" + userAttributes.get("name"));
		}
		String[] ciao = userAttributes.get("name").toString().split(" ");
		String nome = ciao[0];
		String cognome= ciao [1];
		
		if(this.utenteService.findByNomeAndCognome(nome, cognome).size() >=0) {
			System.out.println("---------------- entrato");
			List<Campo> listaCampi = campoService.findAll();
			model.addAttribute("campi", listaCampi);
			model.addAttribute("prenotazione", new Prenotazione());
			return "index";
		}
		System.out.println("non entra!!!!------------");
		return "redirect:/login";
		
	}
}