package com.example.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.productos;


@Repository
public interface repositorioProductos extends JpaRepository<productos,Long> {
	
	List<productos> findByCantidadLessThan(int cantidad);

}
