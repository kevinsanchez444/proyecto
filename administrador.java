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

    @Column(name="contraseña",length=10, nullable=false,unique=true)
    private String contraseña;

    
    @OneToMany(mappedBy = "administrador", cascade = {CascadeType.ALL})
    private List<productos> productos;


	public administrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public administrador(Long idAdministrador, String contraseña) {
		super();
		this.idAdministrador = idAdministrador;
		this.contraseña = contraseña;
	}

	public Long getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(Long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
	
}
