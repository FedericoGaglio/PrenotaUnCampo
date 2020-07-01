package it.uniroma3.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.model.Pagamento;
import it.uniroma3.repository.PagamentoRepository;

@Service
@Transactional
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	public void cancellaPagamento(Pagamento p) {
		this.pagamentoRepository.delete(p);
	}

	public long contaPagamenti() {
		return this.pagamentoRepository.count();
	}

	public boolean esistePagamento(Long id) {
		return this.pagamentoRepository.existsById(id);
	}

	public Pagamento save(Pagamento p) {
		return this.pagamentoRepository.save(p);
	}

	public List<Pagamento> findAll() {
		return (List<Pagamento>) this.pagamentoRepository.findAll();
	}

	public Pagamento findById(Long id) {
		Optional<Pagamento> pagamento = this.pagamentoRepository.findById(id);
		if (pagamento.isPresent()) 
			return pagamento.get();
		else
			return null;
	}

	public boolean alreadyExists(Pagamento pagamento) {
		Optional<Pagamento> pagamenti = this.pagamentoRepository.findById(pagamento.getId());
		if (pagamenti.isPresent())
			return true;
		else 
			return false;
	}

}