package com.eventoApp.eventoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventoApp.eventoApp.models.Eventos;

public interface EventoRepository extends JpaRepository<Eventos, Long>{

}
