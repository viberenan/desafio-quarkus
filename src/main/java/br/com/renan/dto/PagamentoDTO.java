package br.com.renan.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import br.com.renan.entity.Pagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PagamentoDTO {

	private Long id;

	@NotBlank(message = "O nome do cliente é obrigatório")
	private String nomeCliente;

	@NotNull(message = "O valor do pagamento é obrigatório")
	@Positive(message = "O valor do pagamento deve ser positivo")
	private BigDecimal valor;

	@NotNull(message = "Data do pagamento é obrigatória")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataPagamento;

	public PagamentoDTO() {
		super();
	}

	public PagamentoDTO(Long id, String nomeCliente, BigDecimal valor, LocalDateTime dataPagamento) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
	}

	public PagamentoDTO(Pagamento pagamento) {
		this.id = pagamento.getId();
		this.nomeCliente = pagamento.getNomeCliente();
		this.valor = pagamento.getValor();
		this.dataPagamento = pagamento.getDataPagamento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public String toString() {
		return "PagamentoDTO [id=" + id + ", nomeCliente=" + nomeCliente + ", valor=" + valor + ", dataPagamento="
				+ dataPagamento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataPagamento, id, nomeCliente, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagamentoDTO other = (PagamentoDTO) obj;
		return Objects.equals(dataPagamento, other.dataPagamento) && Objects.equals(id, other.id)
				&& Objects.equals(nomeCliente, other.nomeCliente) && Objects.equals(valor, other.valor);
	}

	public Pagamento toEntity() {
		Pagamento pagamento = new Pagamento();
		pagamento.setId(id);
		pagamento.setNomeCliente(nomeCliente);
		pagamento.setValor(valor);
		pagamento.setDataPagamento(dataPagamento);
		return pagamento;
	}

}
