package it.uniroma3.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.Campo;

public interface CampoRepository extends CrudRepository<Campo, Long> {
	
	@Override
	public void delete(Campo c);
	@Override
	public long count();
	@Override
	boolean existsById(Long id);
	@Override
	Optional<Campo> findById(Long id);
	@Override
	List<Campo> findAll();
	List<Campo> findByNome(String nome);

}