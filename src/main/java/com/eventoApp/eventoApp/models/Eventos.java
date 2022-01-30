package com.eventoApp.eventoApp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Eventos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigoEvento;
	
	@JoinColumn(nullable = true)
	private String nome;
	@JoinColumn(nullable = true)
	private String local;
	@JoinColumn(nullable = true)
	private String data;
	@JoinColumn(nullable = true)
	private String horario;
	
	@OneToMany
	private List<Convidado> convidados;
	
	
	
	public Eventos(Long codigoEvento, String nome, String local, String data, String horario) {
		this.codigoEvento = codigoEvento;
		this.nome = nome;
		this.local = local;
		this.data = data;
		this.horario = horario;
	}
	
	public Eventos() {
		
	}
	
	public List<Convidado> getConvidados() { //------
		return convidados;
	}
	
	public Long getCodigoEvento() {
		return codigoEvento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoEvento == null) ? 0 : codigoEvento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eventos other = (Eventos) obj;
		if (codigoEvento == null) {
			if (other.codigoEvento != null)
				return false;
		} else if (!codigoEvento.equals(other.codigoEvento))
			return false;
		return true;
	}
	
	
	
}
