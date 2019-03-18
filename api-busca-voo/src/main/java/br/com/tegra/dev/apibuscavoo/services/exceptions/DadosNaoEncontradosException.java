package br.com.tegra.dev.apibuscavoo.services.exceptions;

public class DadosNaoEncontradosException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1266073051890030274L;

	public DadosNaoEncontradosException (String mensagem) {
		super(mensagem);
	}
	
	public DadosNaoEncontradosException (String mensagem, Throwable causa) {
		super (mensagem, causa);
	}

}
