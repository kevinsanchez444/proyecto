package com.example.demo.Modelo;






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
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idProductos", referencedColumnName = "idProductos")
    private productos idProductos;

    @Column(name="cantidad", nullable=false)
    private int cantidad;


	public carrito() {
		super();
		// TODO Auto-generated constructor stub
	}


	public carrito(Long idCarrito, Usuario idUsuario, productos idProductos, int cantidad) {
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


	public Usuario getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Usuario idUsuario) {
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