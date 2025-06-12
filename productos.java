package com.example.demo.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

   
    @OneToMany(mappedBy = "idCarrito", cascade = {CascadeType.ALL})
    private List<carrito> carritos;

   
    @OneToMany(mappedBy = "idDetallePedido", cascade = {CascadeType.ALL})
    private List<detallePedido> detallesPedidos;

 
	public productos() {
		super();
		// TODO Auto-generated constructor stub
	}


	public productos(Long idProductos, String nombre, String descripcion, int precio, int cantidad,String imagen,
			com.example.demo.modelo.administrador administrador, List<carrito> carritos,
			List<detallePedido> detallesPedidos) {
		super();
		this.idProductos = idProductos;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
		this.administrador = administrador;
		this.carritos = carritos;
		this.detallesPedidos = detallesPedidos;
		this.imagen=imagen;
	}


	public Long getId() {
		return idProductos;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public void setId(Long idProductos) {
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


	public administrador getAdministrador() {
		return administrador;
	}


	public void setAdministrador(administrador administrador) {
		this.administrador = administrador;
	}


	public List<carrito> getCarritos() {
		return carritos;
	}


	public void setCarritos(List<carrito> carritos) {
		this.carritos = carritos;
	}


	public List<detallePedido> getDetallesPedidos() {
		return detallesPedidos;
	}


	public void setDetallesPedidos(List<detallePedido> detallesPedidos) {
		this.detallesPedidos = detallesPedidos;
	}

  
}