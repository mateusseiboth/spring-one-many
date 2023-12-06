package com.seisbot.prova.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Tipo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O Pais deve ser informado")
	@Size(min = 2, message = "O Pais deve ter no mínimo 2 caracteres")
	private String pais;
	
	@OneToMany(mappedBy = "tipo")
	private List<Repetil> repeteis;
	
	public Tipo(){};

	public Tipo(Long id,
			@NotBlank(message = "O Pais deve ser informado") @Size(min = 2, message = "O Pais deve ter no mínimo 2 caracteres") String pais,
			List<Repetil> repeteis) {
		super();
		this.id = id;
		this.pais = pais;
		this.repeteis = repeteis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Repetil> getRepeteis() {
		return repeteis;
	}

	public void setRepeteis(List<Repetil> repeteis) {
		this.repeteis = repeteis;
	}


}
