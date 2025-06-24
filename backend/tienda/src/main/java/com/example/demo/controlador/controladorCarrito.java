package com.example.demo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.carrito;
import com.example.demo.repositorio.repositorioCarrito;

@RestController
@RequestMapping("/ver/carrito")
@CrossOrigin(origins = "http://localhost:4200/")
public class controladorCarrito {
	  
	@Autowired
	private repositorioCarrito repositorio;

	
	@GetMapping("/verTodo")
	public List<carrito> verTodo(){
		return repositorio.findAll();	
		}
	
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
	public ResponseEntity<String> actualizarCantidad(@RequestBody Map<String, Object> datos) {
	    Long idCarrito = Long.valueOf(datos.get("idCarrito").toString());
	    int cantidadNueva = Integer.parseInt(datos.get("cantidad").toString());

	    Optional<carrito> c = repositorio.findById(idCarrito);

	    if (c.isPresent()) {
	        carrito item = c.get();
	        int disponible = item.getProductos().getCantidad();

	        if (cantidadNueva <= disponible) {
	            item.setCantidad(cantidadNueva);
	            repositorio.save(item);
	            return ResponseEntity.ok("Cantidad actualizada");
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Cantidad solicitada no disponible");
	        }
	    }

	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado");
	}

	@PostMapping("/eliminar")
	public Optional <carrito> eliminar(@RequestBody Long idCarrito) {
	    Optional <carrito> c = repositorio.findById(idCarrito);
	        repositorio.deleteById(c.get().getIdCarrito());
	        return c;
	    
	}


	@PostMapping("/vaciarCarrito")
	public ResponseEntity<?> vaciarCarrito(@RequestParam Long cedula) {
	    Map<String, String> respuesta = new HashMap<>();

	    try {
	        repositorio.vaciarCarrito(cedula);

	        int cantidad = repositorio.contarCarrito(cedula);
	        if (cantidad == 0) {
	            respuesta.put("mensaje", "✅ Carrito vaciado exitosamente");
	            return ResponseEntity.ok(respuesta);
	        } else {
	            respuesta.put("mensaje", "⚠️ Algunos productos no pudieron eliminarse");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
	        }

	    } catch (Exception e) {
	        respuesta.put("mensaje", "❌ Error al vaciar el carrito");
	        respuesta.put("error", e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
	    }
	}

	
	}

