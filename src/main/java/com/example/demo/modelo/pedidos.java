package com.example.demo.modelo;


import java.time.LocalDateTime;
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
@Table(name="pedidos")
public class pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPedidos;

    @ManyToOne
    @JoinColumn(name="cedula", referencedColumnName="cedula")
    private usuario idUsuario;

  
    @OneToMany(mappedBy = "idPedido", cascade = {CascadeType.ALL})
    private List<detallePedido> detallesPedidos;

    @Column(name="fechaPedido", nullable=false)
    private LocalDateTime fechaPedido;

    @Column(name="direccion", length=20, nullable=false)
    private String direccion;

    @Column(name="ciudad", length=30, nullable=false)
    private String ciudad;

    @Column(name="referencia", length=100, nullable=false)
    private String referencia;

    @Column(name="estado", length=30, nullable=false)
    private String estado;

    @Column(name="total", nullable=false)
    private int total;



	public pedidos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public pedidos(Long idPedidos, usuario idUsuario, productos idProducto, LocalDateTime fechaPedido, String direccion,
			String ciudad, String referencia, String estado, int total) {
		super();
		this.idPedidos = idPedidos;
		this.idUsuario = idUsuario;
		this.fechaPedido = fechaPedido;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.referencia = referencia;
		this.estado = estado;
		this.total = total;
	}

	public Long getIdPedidos() {
		return idPedidos;
	}

	public void setIdPedidos(Long idPedidos) {
		this.idPedidos = idPedidos;
	}

	public usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(usuario idUsuario) {
		this.idUsuario = idUsuario;
	}


	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
