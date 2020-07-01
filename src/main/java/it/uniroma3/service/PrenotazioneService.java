package it.uniroma3.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.model.Prenotazione;
import it.uniroma3.model.Utente;
import it.uniroma3.repository.PrenotazioneRepository;

@Service
@Transactional
public class PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prenotazioneRepository;

	public void cancellaPrenotazione(Prenotazione p) {
		this.prenotazioneRepository.delete(p);
	}

	public long contaPrenotazioni() {
		return this.prenotazioneRepository.count();
	}

	public boolean esistePrenotazione(Long id) {
		return this.prenotazioneRepository.existsById(id);
	}

	public Prenotazione save(Prenotazione p) {
		return this.prenotazioneRepository.save(p);
	}

	public List<Prenotazione> findAll() {
		return (List<Prenotazione>) this.prenotazioneRepository.findAll();
	}

	public Prenotazione findById(Long id) {
		Optional<Prenotazione> pagamento = this.prenotazioneRepository.findById(id);
		if (pagamento.isPresent()) 
			return pagamento.get();
		else
			return null;
	}

	public boolean alreadyExists(Prenotazione prenotazione) {
		Optional<Prenotazione> prenotazioni = this.prenotazioneRepository.findById(prenotazione.getId());
		if (prenotazioni.isPresent())
			return true;
		else 
			return false;
	}

	public List<Prenotazione> findByUtente(Utente utente) {
		List<Prenotazione> prenotazioni = this.prenotazioneRepository.findByUtente(utente);
		return prenotazioni;
	}

	public Prenotazione findByData(Date data){
		return this.prenotazioneRepository.findByData(data);
	}
	
	public List<Prenotazione> findByUtenteAndData(Utente utente, Date data){
		return this.prenotazioneRepository.findByUtenteAndData(utente, data);
	}
}