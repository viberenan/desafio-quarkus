package br.com.renan.exception;

public class PagamentoException extends RuntimeException {

	private static final long serialVersionUID = 8698395713258267076L;

	public PagamentoException() {
		super();
	}

	public PagamentoException(String message) {
		super(message);
	}
}
