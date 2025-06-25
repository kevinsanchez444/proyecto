package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.carrito;

import jakarta.transaction.Transactional;

@Repository
public interface repositorioCarrito extends JpaRepository<carrito, Long> {
	 @Modifying
	    @Transactional
	    @Query(value="DELETE FROM carrito WHERE cedula = :cedu", nativeQuery=true)
	    void vaciarCarrito(@Param("cedu") Long cedu);

	    @Query(value="SELECT COUNT(*) FROM carrito WHERE cedula = :cedu", nativeQuery=true)
	    int contarCarrito(@Param("cedu") Long cedu);
}
