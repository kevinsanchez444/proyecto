package com.example.demo.controlador;


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

import com.example.demo.modelo.administrador;
import com.example.demo.Repositorio.repositorioAdministrador;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/ver/adm1/")
@CrossOrigin(origins = "http://localhost:4200")
public class controladorAdministrador {
	
	@Autowired 
	private repositorioAdministrador repositorio;
	
	@PostMapping("/registrar")
    public ResponseEntity<String> registrarAdministrador(@RequestBody administrador adminNuevo) {

        if (adminNuevo.getIdAdministrador() == null || adminNuevo.getContrasena() == null || adminNuevo.getContrasena().isEmpty()) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios.");
        }

        if (repositorio.existsById(adminNuevo.getIdAdministrador())) {
            return ResponseEntity.badRequest().body("Ya existe un administrador con ese ID.");
        }

        repositorio.save(adminNuevo);

        return ResponseEntity.ok("Administrador registrado exitosamente.");
    }
	
	
	
	
	@PostMapping("/login")
	public ResponseEntity<String> iniciarSesionAdministrador(@RequestBody administrador adminLogin) {
	    if (adminLogin.getIdAdministrador() == null || adminLogin.getContrasena() == null || adminLogin.getContrasena().isEmpty()) {
	        return ResponseEntity.badRequest().body("Debes ingresar ID y contraseña.");
	    }

	    administrador adminBD = repositorio.findById(adminLogin.getIdAdministrador()).orElse(null);

	    if (adminBD != null && adminBD.getContrasena().equals(adminLogin.getContrasena())) {
	        return ResponseEntity.ok("Inicio de sesión exitoso. Bienvenido al sistema.");
	    } else {
	        return ResponseEntity.status(401).body("Datos incorrectos.");
	    }
	}

	
	@PostMapping("/cerrarSesion")
    public ResponseEntity<String> cerrarSesion(HttpSession session) {
        session.invalidate(); 
        return ResponseEntity.ok("Sesión cerrada correctamente. Redirigiendo a inicio de sesión...");
    }
	
	
	@GetMapping("administrador")
	public ResponseEntity<administrador> obtenerAdministrador(@RequestParam Long id) {
	    Optional<administrador> admin = repositorio.findById(id);
	    return admin.map(ResponseEntity::ok)
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

}
	


	
	
	


