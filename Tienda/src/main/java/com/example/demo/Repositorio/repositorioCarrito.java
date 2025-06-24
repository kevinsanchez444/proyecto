package com.example.demo.Repositorio;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Modelo.carrito;


@Repository
public interface repositorioCarrito extends JpaRepository<carrito, Long> {
	   List<carrito> findByIdUsuarioCedula(Long cedula);
	   void deleteByIdUsuarioCedula(Long cedula);
	  

}
