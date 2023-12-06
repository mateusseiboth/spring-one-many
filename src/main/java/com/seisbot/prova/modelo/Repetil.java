package com.seisbot.prova.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Repetil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O tamanho deve ser informado")
	private String tamanho;
	
	@NotBlank(message = "A temperatura deve ser informada")
	private String temperatura;
	
	@ManyToOne
	private Tipo tipo;
	
	
	public Repetil() {};

	public Repetil(Long id, @NotBlank(message = "O tamanho deve ser informado") String tamanho,
			@NotBlank(message = "A temperatura deve ser informada") String temperatura, Tipo tipo) {
		super();
		this.id = id;
		this.tamanho = tamanho;
		this.temperatura = temperatura;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	
	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
