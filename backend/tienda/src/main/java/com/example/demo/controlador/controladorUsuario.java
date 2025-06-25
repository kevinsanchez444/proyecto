package com.example.demo.controlador;

import com.example.demo.Modelo.Usuario;
import com.example.demo.Repositorio.repositorioUsuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ver/usuarios")
public class controladorUsuario{

@Autowired
    private repositorioUsuario usuarioRepository;

    

@PostMapping("/registro")
public String registrarUsuario(
    @RequestParam Long cedula,
    @RequestParam String nombre,
    @RequestParam String apellido,
    @RequestParam String email,
    @RequestParam Long telefono,
    @RequestParam String contraseña
) {
    if (usuarioRepository.findByEmail(email).isPresent()) {
        return "Correo ya registrado";
    }

    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setCedula(cedula);
    nuevoUsuario.setNombre(nombre);
    nuevoUsuario.setApellido(apellido);
    nuevoUsuario.setEmail(email);
    nuevoUsuario.setTelefono(telefono);
    nuevoUsuario.setContrasena(contraseña);

    usuarioRepository.save(nuevoUsuario);
    return "Registro exitoso";
}

   
    
   
    
@PostMapping("/login")
public ResponseEntity<?> iniciarSesion(@RequestBody Usuario usuario) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(usuario.getEmail());

    if (usuarioOpt.isPresent() && usuarioOpt.get().getContrasena().equals(usuario.getContrasena())) {
        return ResponseEntity.ok(usuarioOpt.get()); 
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(Map.of("mensaje", "Datos incorrectos"));
    }
}



@PutMapping("/actualizar")
public ResponseEntity<?> actualizarPerfil(@RequestBody Usuario usuarioActualizado) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioActualizado.getCedula());

    if (usuarioOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }

    Usuario usuario = usuarioOpt.get();


    Optional<Usuario> correoExistente = usuarioRepository.findByEmail(usuarioActualizado.getEmail());
    if (correoExistente.isPresent() && !correoExistente.get().getCedula().equals(usuario.getCedula())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está en uso por otro usuario");
    }

    usuario.setNombre(usuarioActualizado.getNombre());
    usuario.setApellido(usuarioActualizado.getApellido());
    usuario.setEmail(usuarioActualizado.getEmail());
    usuario.setTelefono(usuarioActualizado.getTelefono());
    usuario.setContrasena(usuarioActualizado.getContrasena());

    usuarioRepository.save(usuario);

    return ResponseEntity.ok("Perfil actualizado");

}

@PostMapping("/eliminar")
public String eliminarCuenta(@RequestParam String email,
                             @RequestParam String contrasena) {

    Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

    if (usuarioOpt.isEmpty()) {
        return "Usuario no encontrado";
    }

    Usuario usuario = usuarioOpt.get();

    if (!usuario.getContrasena().equals(contrasena)) {
        return "Datos incorrectos";
    }

    usuarioRepository.deleteById(usuario.getCedula());
    return "Cuenta eliminada correctamente";
}
}