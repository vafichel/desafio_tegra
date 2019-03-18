package br.com.tegra.dev.apibuscavoo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Voo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	private String operadora;
	
	@JsonInclude(Include.NON_NULL)
	private String identificador;
	
	@JsonInclude(Include.NON_NULL)
	private String origem;
	
	@JsonInclude(Include.NON_NULL)
	private String destino;
	
	@JsonInclude(Include.NON_NULL)
	private LocalDate data;
	
	@JsonInclude(Include.NON_NULL)
	private LocalTime horarioSaida;
	
	@JsonInclude(Include.NON_NULL)
	private LocalTime horarioChegada;
	
	@JsonInclude(Include.NON_NULL)
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(LocalTime horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	public LocalTime getHorarioChegada() {
		return horarioChegada;
	}

	public void setHorarioChegada(LocalTime horarioChegada) {
		this.horarioChegada = horarioChegada;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


}
