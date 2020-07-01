package it.uniroma3.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.UtenteRepository;

@Transactional
@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;

	public void cancellaUtente(Utente u) {
		this.utenteRepository.delete(u);
	}

	public long contaUtenti() {
		return this.utenteRepository.count();
	}

	public boolean esisteUtente(Long id) {
		return this.utenteRepository.existsById(id);
	}

	public Utente save(Utente u) {
		return this.utenteRepository.save(u);
	}

	public List<Utente> findAll() {
		return (List<Utente>) this.utenteRepository.findAll();
	}

	public Utente findById(Long id) {
		Optional<Utente> utente = this.utenteRepository.findById(id);
		if (utente.isPresent()) 
			return utente.get();
		else
			return null;
	}

	public List<Utente> findByNome(String nome) {
		return this.utenteRepository.findByNome(nome);
	}

	public List<Utente> findByCognome(String cognome) {
		return this.utenteRepository.findByNome(cognome);
	}

	public boolean alreadyExists(Utente utente) {
		List<Utente> utenti = this.utenteRepository.findByCognome(utente.getCognome());
		if (utenti.size() > 0)
			return true;
		else 
			return false;
	}

	public Utente findByUsername(String username) {
		return this.utenteRepository.findByUsername(username);
	}
	
	public List<Utente> findByNomeAndCognome(String nome, String cognome){
		return this.utenteRepository.findByNomeAndCognome(nome, cognome);
	}
}