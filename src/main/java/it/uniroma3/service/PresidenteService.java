package it.uniroma3.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.model.Presidente;
import it.uniroma3.repository.PresidenteRepository;

@Service
@Transactional
public class PresidenteService {

	@Autowired
	private PresidenteRepository presidenteRepository;

	public Presidente findById(Long id) {
		Optional<Presidente> presidente = this.presidenteRepository.findById(id);
		if (presidente.isPresent()) 
			return presidente.get();
		else
			return null;
	}

	public List<Presidente> findByNome(String nome) {
		return this.presidenteRepository.findByNome(nome);
	}

	public List<Presidente> findByCognome(String cognome) {
		return this.presidenteRepository.findByNome(cognome);
	}

}