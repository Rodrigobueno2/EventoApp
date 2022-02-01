package com.eventoApp.eventoApp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoApp.eventoApp.models.Convidado;
import com.eventoApp.eventoApp.models.Eventos;
import com.eventoApp.eventoApp.service.EventoService;

@Controller
public class EventoController {
	@Autowired
	private EventoService service;
	
	@GetMapping("/cadastrarEvento")
	public String form() {
		return service.formulario();
	}
	
	@PostMapping("/cadastrarEvento")
	public String form(@Valid Eventos evento, BindingResult result, RedirectAttributes attributes) {
		return service.SalvarEvento(evento, result, attributes);
	}
	
	@GetMapping("/eventos")
	public ModelAndView ListarEventos() {
		return service.ListarEventos();
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhesEvento(@PathVariable("id") long id) {
		return service.ListarEventoPorId(id);
	}
	
	@PostMapping("/{id}")
	public String salvarConvidado(@PathVariable("id") long codEvento, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){
		 service.salvarConvidado(codEvento, convidado, result, attributes);
		 return "redirect:/{id}";
	}
	
	@RequestMapping("/delete")
	public String deletarEvento(long codigoEvento) {
		service.deletarEvento(codigoEvento);
		
		return "redirect:/eventos";
	}
	
	@RequestMapping("/deleteConvidado")
	public String deletarConvidado(String rg) {
		return service.deletarConvidado(rg);
		
	}
	
}
