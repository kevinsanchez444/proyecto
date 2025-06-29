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

    @ManyToOne
    @JoinColumn(name = "cedula", referencedColumnName = "cedula")
    private usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idProductos", referencedColumnName = "idProductos")
    private productos idProductos;

    @Column(name="cantidad", nullable=false)
    private int cantidad;


	public carrito() {
		super();
		// TODO Auto-generated constructor stub
	}


	public carrito(Long idCarrito, usuario idUsuario, productos idProductos, int cantidad) {
		super();
		this.idCarrito = idCarrito;
		this.idUsuario = idUsuario;
		this.idProductos = idProductos;
		this.cantidad = cantidad;
	}


	public Long getIdCarrito() {
		return idCarrito;
	}


	public void setIdCarrito(Long idCarrito) {
		this.idCarrito = idCarrito;
	}


	public usuario getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(usuario idUsuario) {
		this.idUsuario = idUsuario;
	}


	public productos getProductos() {
		return idProductos;
	}


	public void setProductos(productos productos) {
		this.idProductos = productos;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}