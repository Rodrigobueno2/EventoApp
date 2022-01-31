package com.eventoApp.eventoApp.service;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Optional;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoApp.eventoApp.models.Convidado;
import com.eventoApp.eventoApp.models.Eventos;
import com.eventoApp.eventoApp.repository.ConvidadoRepository;
import com.eventoApp.eventoApp.repository.EventoRepository;



@Service
public class EventoService {
	@Autowired
	private EventoRepository repo;
	@Autowired
	private ConvidadoRepository repoConvidado;
	
	public String formulario() {
		return "evento/formEvento";
	}
	
	public String SalvarEvento(Eventos novoEvento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Verifiquem os campos!");
			return "redirect:/cadastrarEvento";
		}
		repo.save(novoEvento);
		attributes.addFlashAttribute("mensagem","Evento salvo com sucesso!");
		return "redirect:/cadastrarEvento";
	}
	
	public ModelAndView ListarEventos(){
		if(repo.findAll().isEmpty()) {
			throw new RuntimeException("Não tem nenhum evento cadastrado");
		}
		
		ModelAndView mv = new ModelAndView("index");
		List<Eventos> eventos = repo.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}

	public ModelAndView ListarEventoPorId(long id) {
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		Eventos evento = repo.findById(id).get();
		List<Convidado> convidados =  repoConvidado.findByEvento(id);
		mv.addObject("evento", evento);
		mv.addObject("convidados", convidados);
		return mv;
	}

	public String salvarConvidado(long codEvento, Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Verifiquem os campos!");
			return null;
		}
		Eventos evento = repo.findById(codEvento).get();
		convidado.setEvento(evento);
		repoConvidado.save(convidado);
		attributes.addFlashAttribute("mensagem","Convidado adicionado com sucesso!");
		return null;
	}
}
