package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.carrito;


@Repository
public interface repositorioCarrito extends JpaRepository<carrito, Long> {

}
