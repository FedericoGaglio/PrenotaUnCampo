package it.uniroma3.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.model.Campo;
import it.uniroma3.repository.CampoRepository;

@Service
@Transactional
public class CampoService {

	@Autowired
	private CampoRepository campoRepository;

	public void cancellaCampo(Campo c) {
		this.campoRepository.delete(c);
	}

	public long contaCampi() {
		return this.campoRepository.count();
	}

	boolean esisteCampo(Long id) {
		return this.campoRepository.existsById(id);
	}

	public Campo save(Campo campo) {
		return this.campoRepository.save(campo);
	}

	public List<Campo> findByNome(String nome) {
		return this.campoRepository.findByNome(nome);
	}

	public List<Campo> findAll() {
		return this.campoRepository.findAll();
	}

	public boolean alreadyExists(Campo campo) {
		List<Campo> campi = this.campoRepository.findByNome(campo.getNome());
		if (campi.size() > 0)
			return true;
		else 
			return false;
	}

	public Campo findById(Long id) {
		Optional<Campo> campo = this.campoRepository.findById(id);
		if (campo.isPresent()) 
			return campo.get();
		else
			return null;
	}
}