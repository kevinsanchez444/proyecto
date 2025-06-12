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
@Table(name="detallePedido")
public class detallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idDetallePedido;

    @ManyToOne
    @JoinColumn(name="idPedidos", referencedColumnName="idPedidos")
    private pedidos idPedido;

    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idProductos")
    private productos idProducto;
    @Column(name="cantidad", nullable=false)
    private int cantidad;



	public detallePedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public detallePedido(Long idDetallePedido, pedidos idPedido, productos idProducto, int cantidad) {
		super();
		this.idDetallePedido = idDetallePedido;
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public Long getIdDetallePedido() {
		return idDetallePedido;
	}

	public void setIdDetallePedido(Long idDetallePedido) {
		this.idDetallePedido = idDetallePedido;
	}

	public pedidos getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(pedidos idPedido) {
		this.idPedido = idPedido;
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
