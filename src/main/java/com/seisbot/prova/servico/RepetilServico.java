package com.seisbot.prova.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seisbot.prova.excecao.RepetilNotFoundException;
import com.seisbot.prova.modelo.Repetil;
import com.seisbot.prova.repositorio.RepetilRepositorio;


@Service
public class RepetilServico {
	
	@Autowired
	private RepetilRepositorio repetilRepositorio;
	
	public Repetil criarRepetil(Repetil repetil) {
		return repetilRepositorio.save(repetil);
	}
	
	public List<Repetil> buscarTodosRepetils() {
		return repetilRepositorio.findAll();
	}
	
	public Repetil buscarRepetilPorId(Long id)throws RepetilNotFoundException {
		Optional<Repetil> opt = repetilRepositorio.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new RepetilNotFoundException("Repetil com id : " + id + " não existe");
		}		
	}	
	
	public void apagarRepetil(Long id) throws RepetilNotFoundException {
		Repetil repetil = buscarRepetilPorId(id);
		repetilRepositorio.delete(repetil);
	}
	
	public Repetil alterarRepetil(Repetil repetil) {
		return repetilRepositorio.save(repetil);
	}
	
	public List<Repetil> buscarTodosRepetilsPorTamanho(String tamanho) {
		return repetilRepositorio.findBytamanhoContainingIgnoreCase(tamanho);
	}

}
