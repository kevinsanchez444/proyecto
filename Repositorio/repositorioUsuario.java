package com.example.demo.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelo.Usuario;

@Repository
public interface repositorioUsuario extends JpaRepository<Usuario,Long> {
	Optional<Usuario> findByEmail(String email);
}
