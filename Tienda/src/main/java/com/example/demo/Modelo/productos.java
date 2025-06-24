package com.example.demo.Modelo;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class productos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idProductos;

    @Column(name="nombre", length=100, nullable=false)
    private String nombre;

    @Column(name="descripcion", length=300, nullable=false)
    private String descripcion;

    @Column(name="precio", nullable=false)
    private int precio;

    @Column(name="cantidad", nullable=false)
    private int cantidad;
    
    @Column(name="imagen",nullable=false, length=200)
    private String imagen;

    
    @ManyToOne
    @JoinColumn(name="idAdministrador", referencedColumnName="idAdministrador")
    private administrador administrador;
    
	public productos() {
		super();
		// TODO Auto-generated constructor stub
	}





	public productos(Long idProductos, String nombre, String descripcion, int precio, int cantidad, String imagen,
			com.example.demo.Modelo.administrador administrador) {
		super();
		this.idProductos = idProductos;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
		this.imagen = imagen;
		this.administrador = administrador;
	}





	public Long getIdProductos() {
		return idProductos;
	}





	public void setIdProductos(Long idProductos) {
		this.idProductos = idProductos;
	}





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getDescripcion() {
		return descripcion;
	}





	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}





	public int getPrecio() {
		return precio;
	}





	public void setPrecio(int precio) {
		this.precio = precio;
	}





	public int getCantidad() {
		return cantidad;
	}





	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}





	public String getImagen() {
		return imagen;
	}





	public void setImagen(String imagen) {
		this.imagen = imagen;
	}





	public administrador getAdministrador() {
		return administrador;
	}





	public void setAdministrador(administrador administrador) {
		this.administrador = administrador;
	}


}