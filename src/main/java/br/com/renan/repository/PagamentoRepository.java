package br.com.renan.repository;

import br.com.renan.entity.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {

	public Pagamento salvar(Pagamento pagamento) {
		persist(pagamento);
		return pagamento;
	}
	
}
