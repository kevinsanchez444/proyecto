package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.detallePedido;

@Repository
public interface repositorioDetallePedido extends JpaRepository<detallePedido,Long>{

}
