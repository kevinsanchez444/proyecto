package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelo.productos;

@Repository
public interface repositorioProductos extends JpaRepository<productos,Long> {

}
