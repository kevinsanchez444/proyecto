package com.example.demo.controlador;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Modelo.carrito;
import com.example.demo.Repositorio.repositorioCarrito;

@RestController
@RequestMapping("/ver/carrito")
public class controladorCarrito {
	  
	private repositorioCarrito repositorio;
	
	@PostMapping("/guardar")
	public ResponseEntity<carrito> guardarProducto(@RequestBody carrito idProducto){
		try {
			carrito nuevopro = this.repositorio.save(idProducto);
			 return ResponseEntity.status(HttpStatus.CREATED).body(nuevopro);
		}catch(Exception c) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping("/actualizarCantidad")
    public ResponseEntity<?> actualizar(@RequestParam Long idProducto, @RequestBody carrito c) {
        Optional<carrito> carritoOptional = repositorio.findById(idProducto);

        if (!carritoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("producto con id " + idProducto + " no encontrado");
        }

        try {
        	carrito carrito = carritoOptional.get();
        	carrito actualizado = repositorio.save(carrito);
            return ResponseEntity.ok(actualizado);
        } catch (Exception excep) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar la cantidad: " + excep.getMessage());
        }
    }
	
	@DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam Long idProducto) {
        if (!repositorio.existsById(idProducto)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("producto con id " + idProducto + " no existe, no se puede eliminar");
        }
        try {
            repositorio.deleteById(idProducto);
            return ResponseEntity.ok("producto eliminada correctamente");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar el producto: " + ex.getMessage());
        }
    }
	

}
