package com.example.demo.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="carrito")
public class carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCarrito;

    @OneToOne
    @JoinColumn(name = "cedula", referencedColumnName = "cedula", unique = true)
    private usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idProductos")
    private productos idProducto;

    @Column(name="cantidad", nullable=false)
    private int cantidad;


	public carrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public carrito(Long idCarrito, usuario idUsuario, productos idProducto, int cantidad) {
		super();
		this.idCarrito = idCarrito;
		this.idUsuario = idUsuario;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return idCarrito;
	}

	public void setId(Long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public productos getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(productos idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}