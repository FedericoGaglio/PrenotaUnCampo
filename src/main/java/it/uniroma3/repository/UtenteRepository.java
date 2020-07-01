package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.Utente;

public interface UtenteRepository extends CrudRepository <Utente, Long> {
	
	@Override
	public void delete(Utente u);
	@Override
	public long count();
	@Override
	boolean existsById(Long id);
	@Override
	Iterable<Utente> findAll();
	@Override
	Optional<Utente> findById(Long id);
	List<Utente> findByNome(String nome);
	List<Utente> findByCognome(String cognome);
	List<Utente> findByNomeAndCognome(String nome, String cognome);
	Utente findByUsername(String username);

}