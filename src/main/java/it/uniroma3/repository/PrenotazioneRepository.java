package it.uniroma3.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.Prenotazione;
import it.uniroma3.model.Utente;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long> {

	@Override
	public void delete(Prenotazione c);
	@Override
	public long count();
	@Override
	boolean existsById(Long id);
	@Override
	Optional<Prenotazione> findById(Long id);
	Prenotazione findByData(Date data);
	List<Prenotazione> findByUtente(Utente utente);
	List<Prenotazione> findByUtenteAndData(Utente utente, Date data);

}