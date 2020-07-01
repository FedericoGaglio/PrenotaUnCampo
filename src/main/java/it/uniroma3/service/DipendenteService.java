package it.uniroma3.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.model.Dipendente;
import it.uniroma3.repository.DipendenteRepository;

@Service
@Transactional
public class DipendenteService {

	@Autowired
	private DipendenteRepository dipendenteRepository;

	public void cancellaDipendente(Dipendente d) {
		this.dipendenteRepository.delete(d);
	}

	public long contaDipendenti() {
		return this.dipendenteRepository.count();
	}

	public boolean esisteDipendente(Long id) {
		return this.dipendenteRepository.existsById(id);
	}

	public Dipendente save(Dipendente d) {
		return this.dipendenteRepository.save(d);
	}

	public List<Dipendente> findAll() {
		return (List<Dipendente>) this.dipendenteRepository.findAll();
	}

	public Dipendente findById(Long id) {
		Optional<Dipendente> dipendente = this.dipendenteRepository.findById(id);
		if (dipendente.isPresent()) 
			return dipendente.get();
		else
			return null;
	}

	public List<Dipendente> findByNome(String nome) {
		return this.dipendenteRepository.findByNome(nome);
	}

	public List<Dipendente> findByCognome(String cognome) {
		return this.dipendenteRepository.findByNome(cognome);
	}

	public boolean alreadyExists(Dipendente dipendente) {
		Optional<Dipendente> dipendenti = this.dipendenteRepository.findById(dipendente.getId());
		if (dipendenti.isPresent())
			return true;
		else 
			return false;
	}
}