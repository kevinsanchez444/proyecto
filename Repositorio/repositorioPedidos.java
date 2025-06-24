package com.example.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelo.pedidos;

@Repository
public interface repositorioPedidos extends JpaRepository<pedidos,Long> {
	
    List<pedidos> findByIdUsuarioCedula(Long cedula);


    List<pedidos> findByEstado(String estado);
}