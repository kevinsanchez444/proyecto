package com.example.demo.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.detallePedido;
import com.example.demo.repositorio.repositorioDetallePedido;

@RestController
@RequestMapping("/ver/detallePedido")
public class controladorDetallePedido {
 private repositorioDetallePedido repositorio;
 
 @GetMapping("/verTodo")
 public List<detallePedido> verTodo(){
	 return repositorio.findAll();
	 }
 
 @PostMapping("/guardar")
 public ResponseEntity<detallePedido> guardarDetalle(@RequestBody detallePedido idDetallePedido){
	 try {
		 detallePedido nuevoDe = this.repositorio.save(idDetallePedido);
		 return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDe);
	 }catch(Exception d) {
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	 }
	 
 }
 
 
}
