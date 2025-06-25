package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.usuario;



@Repository
public interface repositorioUsuario extends JpaRepository<usuario,Long> {

}
