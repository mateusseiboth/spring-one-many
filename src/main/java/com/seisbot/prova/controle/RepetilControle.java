package com.seisbot.prova.controle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seisbot.prova.excecao.RepetilNotFoundException;
import com.seisbot.prova.modelo.Tipo;
import com.seisbot.prova.modelo.Repetil;
import com.seisbot.prova.servico.TipoServico;
import com.seisbot.prova.servico.RepetilServico;

import jakarta.validation.Valid;

@Controller
public class RepetilControle {
	
	@Autowired
	private RepetilServico repetilServico;
	
	@Autowired
	private TipoServico tipoServico;
	
	@GetMapping("/listar")
    public String listarRepeteis(Model model) {	
		ArrayList<Repetil> tratados = new ArrayList<>();
		
		List<Repetil> repeteis = repetilServico.buscarTodosRepetils();
		for(Repetil r : repeteis) {
			if(r.getTipo() == null) {
				r.setTipo(new Tipo());
			}
		}
		model.addAttribute("listaRepetils",repeteis);
		return "/lista-repetil";
    }
	
	@PostMapping("/buscar")
    public String buscarRepeteis(Model model, @Param("tamanho") String tamanho) {	
		if (tamanho == null) {
			return "redirect:/";
		}
		List<Repetil> repeteis = repetilServico.buscarTodosRepetilsPorTamanho(tamanho);
		model.addAttribute("listaRepeteis",repeteis);
		return "/lista-repetil	";
    }
	
	@GetMapping("/")
    public String novoRepetil(Model model) {	
		Repetil repetil = new Repetil();
		model.addAttribute("novoRepetil",repetil);
		
		List<Tipo> tipos = tipoServico.listar();
		model.addAttribute("tipos",tipos);
		
        return "/novo-repetil";
    }
	
	@PostMapping("/gravar")
	public String gravarRepetil(@ModelAttribute("novoRepetil") @Valid Repetil repetil,
			BindingResult erros,
			RedirectAttributes attributes, Model model) {
		if(erros.hasErrors()) {
			List<Tipo> tipos = tipoServico.listar();
			model.addAttribute("tipos",tipos);
			return "/novo-repetil";
		}
		Repetil repetilCadastrado = repetilServico.criarRepetil(repetil);
		attributes.addFlashAttribute("mensagem", "Repetil salvo com sucesso!");
		model.addAttribute("objetoRepetil",repetilCadastrado);
        return "/mostrar";
    }
	
	@GetMapping("/apagar/{id}")
    public String apagarRepetil(@PathVariable("id") long id, RedirectAttributes attributes) {	
		try {
			repetilServico.apagarRepetil(id);
		} catch (RepetilNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
        return "redirect:/";
    }
	
	
	@GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") long id, RedirectAttributes attributes,
    		Model model) {	
		try {
			Repetil repetil = repetilServico.buscarRepetilPorId(id);
			model.addAttribute("objetoRepetil", repetil);
			List<Tipo> tipos = tipoServico.listar();
			model.addAttribute("tipos",tipos);
			return "/alterar-repetil";
		} catch (RepetilNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
        return "redirect:/listar";
    }
	
	@PostMapping("/editar/{id}")
	public String editarRepetil(@PathVariable("id") long id, 
								@ModelAttribute("objetoRepetil") @Valid Repetil repetil, 
								BindingResult erros, Model model) {
		if (erros.hasErrors()) {
			repetil.setId(id);
			List<Tipo> tipos = tipoServico.listar();
			model.addAttribute("tipos",tipos);
	        return "/alterar-repetil";
	    }
		repetilServico.alterarRepetil(repetil);
		return "redirect:/listar";
	}

}
