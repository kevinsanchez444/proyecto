package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.administrador;


@Repository
public interface repositorioAdministrador extends JpaRepository<administrador,Long> {

}
