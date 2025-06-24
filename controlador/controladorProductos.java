package com.example.demo.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.modelo.administrador;
import com.example.demo.modelo.productos;
import com.example.demo.Repositorio.repositorioAdministrador;
import com.example.demo.Repositorio.repositorioProductos;

@RestController
@RequestMapping("/ver/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class controladorProductos {
	
	 @Autowired
	    private repositorioProductos productoRepo;

	    @Autowired
	    private repositorioAdministrador adminRepo;

	    @PostMapping("/guardar")
	    public ResponseEntity<String> guardarProducto(
	            @RequestParam("archivo") MultipartFile archivo,
	            @RequestParam("nombre") String nombre,
	            @RequestParam("descripcion") String descripcion,
	            @RequestParam("precio") int precio,
	            @RequestParam("cantidad") int cantidad,
	            @RequestParam("idAdministrador") Long idAdministrador) {

	        try {
	            Optional<administrador> adminOpt = adminRepo.findById(idAdministrador);
	            if (adminOpt.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                        .body("Administrador no válido.");
	            }

	            Path path = Paths.get("src/main/resources/static/images", archivo.getOriginalFilename());
	            Files.createDirectories(path.getParent());
	            Files.write(path, archivo.getBytes());

	            productos producto = new productos();
	            producto.setNombre(nombre);
	            producto.setDescripcion(descripcion);
	            producto.setPrecio(precio);
	            producto.setCantidad(cantidad);
	            producto.setImagen("images/" + archivo.getOriginalFilename());
	            producto.setAdministrador(adminOpt.get());

	            productoRepo.save(producto);

	            return ResponseEntity.status(HttpStatus.CREATED)
	                    .body("Producto guardado con éxito.");
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error al guardar imagen: " + e.getMessage());
	        }
	    }
	    
	    @GetMapping
	    public List<productos> obtenerTodos() {
	        return productoRepo.findAll();
	    }


	    
	    @DeleteMapping("/eliminar")
	    public ResponseEntity<String> eliminarProducto(
	            @RequestParam Long idProducto,
	            @RequestParam Long idAdministrador) {
	        try {
	            if (!adminRepo.existsById(idAdministrador)) {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                        .body("El Administrador no es válido.");
	            }

	            Optional<productos> productoOptional = productoRepo.findById(idProducto);
	            if (productoOptional.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("El Producto no se ah encontrado.");
	            }

	            productoRepo.deleteById(idProducto);
	            return ResponseEntity.ok("El Producto se ah eliminado correctamente.");

	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error al eliminar el producto.");
	        }
	    }
	    
	    @GetMapping("/disponibilidad")
	    public ResponseEntity<String> consultarDisponibilidad(@RequestParam Long idProducto) {
	        Optional<productos> productoOptional = productoRepo.findById(idProducto);

	        if (productoOptional.isPresent()) {
	            int stock = productoOptional.get().getCantidad();
	            return ResponseEntity.ok("Stock disponible: " + stock + " unidades.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Producto con ID " + idProducto + " no encontrado.");
	        }
	    }
	    
	    @GetMapping("/actualizar")
	    public ResponseEntity<String> actualizarProducto(
	        @RequestParam("idProducto") Long idProducto,
	        @RequestParam(value = "nuevoNombre", required = false) String nuevoNombre,
	        @RequestParam(value = "descripcion", required = false) String nuevaDescripcion,
	        @RequestParam(value = "precio", required = false) Integer nuevoPrecio,
	        @RequestParam(value = "cantidad", required = false) Integer nuevaCantidad
	    ) {
	        Optional<productos> productoOpt = productoRepo.findById(idProducto);

	        if (productoOpt.isPresent()) {
	            productos producto = productoOpt.get();

	            if (nuevoNombre != null) producto.setNombre(nuevoNombre);
	            if (nuevaDescripcion != null) producto.setDescripcion(nuevaDescripcion);
	            if (nuevoPrecio != null) producto.setPrecio(nuevoPrecio);
	            if (nuevaCantidad != null) producto.setCantidad(nuevaCantidad);

	            productoRepo.save(producto);
	            return ResponseEntity.ok("Producto actualizado correctamente.");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Producto con ID '" + idProducto + "' no encontrado.");
	        }
	    }
	    
	    
	    
	    @GetMapping("/buscarPorId")
	    public ResponseEntity<productos> buscarPorId(@RequestParam Long idProducto) {
	        Optional<productos> productoOpt = productoRepo.findById(idProducto);

	        if (productoOpt.isPresent()) {
	            return ResponseEntity.ok(productoOpt.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }


	    
	    @GetMapping("/alerta-stock")
	    public ResponseEntity<List<productos>> verAlertaStock() {
	        int stockMinimo = 5;
	        List<productos> productosBajoStock = productoRepo.findByCantidadLessThan(stockMinimo);

	        if (productosBajoStock.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
	        } else {
	            return ResponseEntity.ok(productosBajoStock);
	        }
	    }
	    
	    @PostMapping("/cambiar-precio")
	    public ResponseEntity<String> cambiarPrecio(
	            @RequestParam Long idProducto,
	            @RequestParam int nuevoPrecio) {

	        if (nuevoPrecio <= 0) {
	            return ResponseEntity.badRequest().body("El precio debe ser un valor positivo.");
	        }

	        Optional<productos> productoOptional = productoRepo.findById(idProducto);
	        
	        if (productoOptional.isPresent()) {
	            productos producto = productoOptional.get();
	            producto.setPrecio(nuevoPrecio);
	            productoRepo.save(producto);
	            return ResponseEntity.ok("Precio actualizado");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Producto no encontrado");
	        }
	    }

	    @PostMapping("/reducir-stock")
	    public ResponseEntity<String> reducirStock(
	            @RequestParam Long idProducto,
	            @RequestParam int cantidadReducir) {

	        Optional<productos> productoOptional = productoRepo.findById(idProducto);

	        if (productoOptional.isPresent()) {
	            productos producto = productoOptional.get();

	            if (producto.getCantidad() >= cantidadReducir) {
	                producto.setCantidad(producto.getCantidad() - cantidadReducir);
	                productoRepo.save(producto);

	                return ResponseEntity.ok("Stock actualizado correctamente. Nuevo stock: " + producto.getCantidad());
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Stock insuficiente. Solo hay " + producto.getCantidad() + " unidades disponibles.");
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Producto con ID " + idProducto + " no encontrado.");
	        }
	    }
	    
	    @PostMapping("/aumentarStock")
	    public ResponseEntity<String> aumentarStock(
	            @RequestParam Long idProducto,
	            @RequestParam int cantidadAgregar) {
	        try {
	            Optional<productos> productoOptional = productoRepo.findById(idProducto);

	            if (productoOptional.isPresent()) {
	                productos producto = productoOptional.get();
	                int nuevoStock = producto.getCantidad() + cantidadAgregar;
	                producto.setCantidad(nuevoStock);
	                productoRepo.save(producto);

	                return ResponseEntity.ok("Stock actualizado correctamente. Nuevo stock: " + nuevoStock);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("Producto con ID " + idProducto + " no encontrado.");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error al actualizar el stock: " + e.getMessage());
	        }
	    }





}


