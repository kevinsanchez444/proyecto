package com.example.demo.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class usuario {
    @Id
    @Column(name="cedula", unique=true)
    private Long cedula;

    @Column(name="nombre", length=100, nullable=false)
    private String nombre;

    @Column(name="apellido",length=100, nullable=false)
    private String apellido;

    @Column(name="email",length=100, nullable=false,unique=true)
    private String email;

    @Column(name="telefono", nullable=false)
    private Long telefono;

    @Column(name="contrasena", length=40, nullable=false)
    private String contrasena;

    @OneToMany(mappedBy = "idUsuario", cascade = {CascadeType.ALL})
    private List<pedidos> pedidos;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
    private List<carrito>carrito;

	public usuario() {
		super();
		// TODO Auto-generated constructor stub
	}


	public usuario(Long cedula, String nombre, String apellido, String email, Long telefono, String contrasena,
			List<carrito> carritos, List<com.example.demo.modelo.pedidos> pedidos) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.contrasena = contrasena;
		
		this.pedidos = pedidos;
	}


	public Long getCedula() {
		return cedula;
	}


	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getTelefono() {
		return telefono;
	}


	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContraseña(String contrasena) {
		this.contrasena = contrasena;
	}




	public List<pedidos> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<pedidos> pedidos) {
		this.pedidos = pedidos;
	}

   
}
