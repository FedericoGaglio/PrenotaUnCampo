package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.Dipendente;

public interface DipendenteRepository extends CrudRepository<Dipendente,Long> {

	@Override
	public void delete(Dipendente d);
	@Override
	public long count();
	@Override
	boolean existsById(Long id);
	@Override
	Iterable<Dipendente> findAll();
	@Override
	Optional<Dipendente> findById(Long id);
	List<Dipendente> findByNome(String nome);

}