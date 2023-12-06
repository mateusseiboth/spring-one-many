package com.seisbot.prova.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seisbot.prova.modelo.Tipo;
import com.seisbot.prova.modelo.Repetil;
import com.seisbot.prova.servico.TipoServico;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tipo")
public class TipoControle {
	
	@Autowired
	private TipoServico tipoServico;
	
	@GetMapping("/novo")
    public String novoTipo(Model model) {	
		Tipo tipo = new Tipo();
		model.addAttribute("novoTipo",tipo);
        return "/novo-tipo";
    }
	
	@PostMapping("/gravar")
	public String gravarTipo(@ModelAttribute("novoTipo") @Valid Tipo tipo,
			BindingResult erros,
			RedirectAttributes attributes) {
		if(erros.hasErrors()) {
			return "/novo-tipo";
		}
		tipoServico.gravar(tipo);
		attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
        return "redirect:/listar";
    }

}
