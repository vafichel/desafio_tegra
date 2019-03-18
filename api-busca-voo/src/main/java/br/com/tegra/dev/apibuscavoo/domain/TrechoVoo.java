package br.com.tegra.dev.apibuscavoo.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TrechoVoo {
	@JsonInclude(Include.NON_NULL)
	private String origem;
	
	@JsonInclude(Include.NON_NULL)
	private String destino;
	
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime saida;
	
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime chegada;
	
	@JsonInclude(Include.NON_NULL)
	List<Voo> trechos;
	
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public LocalDateTime getSaida() {
		return saida;
	}
	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}
	public LocalDateTime getChegada() {
		return chegada;
	}
	public void setChegada(LocalDateTime chegada) {
		this.chegada = chegada;
	}
	public List<Voo> getTrechos() {
		return trechos;
	}
	public void setTrechos(List<Voo> trechos) {
		this.trechos = trechos;
	}

	

}
