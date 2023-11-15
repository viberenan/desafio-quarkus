package br.com.renan.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.renan.dto.PagamentoDTO;
import br.com.renan.entity.Pagamento;
import br.com.renan.exception.PagamentoException;
import br.com.renan.repository.PagamentoRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PagamentoService {

	@Inject
	PagamentoRepository repository;

	private static final Logger log = LoggerFactory.getLogger(PagamentoService.class);

	public PagamentoDTO salvar(PagamentoDTO dto) {
		try {
			Pagamento pagamento = dto.toEntity();
			pagamento = repository.salvar(pagamento);
			return new PagamentoDTO(pagamento);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}

	public PagamentoDTO buscarPorId(Long id) {
		try {
			Pagamento pagamento = repository.findById(id);
			if (Objects.isNull(pagamento))
				throw new PagamentoException("Não encontrado dados do pagamento com id: " + id);
			return new PagamentoDTO(pagamento);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	public List<PagamentoDTO> buscarTodosPagamentos() {
		try {
			PanacheQuery<Pagamento> panache = repository.findAll();
			return panache.stream().map(PagamentoDTO::new).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
		
	}

}
