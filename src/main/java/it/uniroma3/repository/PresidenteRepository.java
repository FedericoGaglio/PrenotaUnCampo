package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.Presidente;

public interface PresidenteRepository extends CrudRepository<Presidente, Long> {

	@Override
	public void delete(Presidente c);
	@Override
	public long count();
	@Override
	boolean existsById(Long id);
	@Override
	Iterable<Presidente> findAll();
	@Override
	Optional<Presidente> findById(Long id);
	List<Presidente> findByNome(String nome);

}