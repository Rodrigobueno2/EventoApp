package com.eventoApp.eventoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.eventoApp.eventoApp.models.Eventos;

public interface EventoRepository extends JpaRepository<Eventos, Long>{
   @Query(value = "select * from eventos where DATE(data) = DATE(NOW())", nativeQuery = true)
   List<Eventos> buscarEventosHoje();
}
