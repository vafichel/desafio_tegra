package br.com.tegra.dev.apibuscavoo.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.tegra.dev.apibuscavoo.domain.DetalheErro;
import br.com.tegra.dev.apibuscavoo.services.exceptions.DadosNaoEncontradosException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(DadosNaoEncontradosException.class)
	public ResponseEntity<DetalheErro> handlerDadosNaoEncontradosException(DadosNaoEncontradosException e,
			HttpServletRequest request) {
		
		DetalheErro erro = new DetalheErro();
		
		erro.setStatus(404l);
		erro.setTitulo("Não foram encontrados dados para os argumentos de pesquisa");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
