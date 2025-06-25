package com.example.demo.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="administrador")
public class administrador {
    @Id
    @Column(name="idAdministrador")
    private Long idAdministrador;

    @Column(name="contrasena",length=40, nullable=false)
    private String contrasena;

    
    @OneToMany(mappedBy = "administrador", cascade = {CascadeType.ALL})
    private List<productos> productos;


	public administrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public administrador(Long idAdministrador, String contrasena) {
		super();
		this.idAdministrador = idAdministrador;
		this.contrasena = contrasena;
	}

	public Long getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(Long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContraseña(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
	
}
