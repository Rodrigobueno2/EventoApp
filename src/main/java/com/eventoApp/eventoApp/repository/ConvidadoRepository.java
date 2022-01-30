package com.eventoApp.eventoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eventoApp.eventoApp.models.Convidado;
import com.eventoApp.eventoApp.models.Eventos;

public interface ConvidadoRepository extends JpaRepository<Convidado, String>{
	@Query(value = "select * from convidado where evento_codigo_evento = :codEvento", nativeQuery = true)
	List<Convidado> findByEvento(long codEvento);
}
