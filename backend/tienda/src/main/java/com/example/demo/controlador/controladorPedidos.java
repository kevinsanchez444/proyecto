package com.example.demo.controlador;


import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Modelo.Usuario;
import com.example.demo.Modelo.carrito;
import com.example.demo.Modelo.pedidos;
import com.example.demo.Repositorio.repositorioCarrito;
import com.example.demo.Repositorio.repositorioPedidos;
import com.example.demo.Repositorio.repositorioUsuario;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pedidos")
public class controladorPedidos {

	@Autowired
    private repositorioPedidos repositorio;
	
	@Autowired
	private repositorioCarrito carritoRepo;
	
	@Autowired
	private repositorioUsuario usuarioRepo;

	@Transactional
	@PostMapping("/generar")
	public ResponseEntity<?> generarPedidoDesdeCarrito(@RequestParam Long cedula,
	                                                   @RequestParam String direccion,
	                                                   @RequestParam String ciudad,
	                                                   @RequestParam String referencia) {

	    List<carrito> productosCarrito = carritoRepo.findByIdUsuarioCedula(cedula);
	    if (productosCarrito.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El carrito está vacío");
	    }

	    Usuario usuario = usuarioRepo.findById(cedula).orElse(null);
	    if (usuario == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
	    }

	    int total = productosCarrito.stream()
	        .mapToInt(item -> item.getProductos().getPrecio() * item.getCantidad())
	        .sum();

	    pedidos nuevoPedido = new pedidos();
	    nuevoPedido.setIdUsuario(usuario);
	    nuevoPedido.setFechaPedido(LocalDateTime.now());
	    nuevoPedido.setDireccion(direccion);
	    nuevoPedido.setCiudad(ciudad);
	    nuevoPedido.setReferencia(referencia);
	    nuevoPedido.setEstado("Pendiente");
	    nuevoPedido.setTotal(total);

	    repositorio.save(nuevoPedido);
	    carritoRepo.deleteAll(productosCarrito);

	    Map<String, Object> respuesta = new HashMap<>();
	    respuesta.put("mensaje", "Pedido generado exitosamente");
	    respuesta.put("pedido", nuevoPedido);

	    return ResponseEntity.ok(respuesta);
	}
   
	
	@GetMapping("/historial")
	public List<pedidos> obtenerPedidosPorUsuario(@RequestParam Long cedula) {
	    return repositorio.findByIdUsuarioCedula(cedula);
	}

	
	@GetMapping("/detalle")
	public ResponseEntity<?> obtenerDetallePedido(@RequestParam Long idPedido) {
	    Optional<pedidos> pedido = repositorio.findById(idPedido);
	    if (pedido.isPresent()) {
	        return ResponseEntity.ok(pedido.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
	    }
	}
	@PutMapping("/cancelar")
	public ResponseEntity<?> cancelarPedido(@RequestParam Long idPedido) {
	    Optional<pedidos> pedido = repositorio.findById(idPedido);
	    if (pedido.isPresent()) {
	        pedidos p = pedido.get();
	        p.setEstado("Cancelado");
	        repositorio.save(p);
	        return ResponseEntity.ok("Pedido cancelado con éxito");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no encontrado");
	    }
	}
	}
	