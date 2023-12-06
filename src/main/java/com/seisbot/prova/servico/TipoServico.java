package com.seisbot.prova.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seisbot.prova.modelo.Tipo;
import com.seisbot.prova.repositorio.TipoRepositorio;

@Service
public class TipoServico {
	
	@Autowired
	private TipoRepositorio tipoRepositorio;
	
	public Tipo gravar(Tipo tipo) {
		return tipoRepositorio.save(tipo);
	}
	
	public List<Tipo> listar(){
		return tipoRepositorio.findAll();
	}

}
