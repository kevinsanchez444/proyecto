package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelo.detallePedido;

@Repository
public interface repositorioDetallePedido extends JpaRepository<detallePedido,Long>{

}
