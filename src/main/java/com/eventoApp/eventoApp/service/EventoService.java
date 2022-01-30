package com.eventoApp.eventoApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
	
	public String SalvarEvento(Eventos novoEvento) {
		repo.save(novoEvento);
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

	public String salvarConvidado(long codEvento, Convidado convidado) {
		Eventos evento = repo.findById(codEvento).get();
		convidado.setEvento(evento);
		repoConvidado.save(convidado);
		return null;
	}
}
