package it.uniroma3.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.Pagamento;

public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {

	@Override
	public void delete(Pagamento d);
	@Override
	public long count();
	@Override
	boolean existsById(Long id);
	@Override
	Iterable<Pagamento> findAll();
	@Override
	Optional<Pagamento> findById(Long id);

}