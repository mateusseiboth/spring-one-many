package com.seisbot.prova.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seisbot.prova.modelo.Repetil;

public interface RepetilRepositorio extends JpaRepository<Repetil, Long> {
	List<Repetil> findBytamanhoContainingIgnoreCase(String tamanho);
}
