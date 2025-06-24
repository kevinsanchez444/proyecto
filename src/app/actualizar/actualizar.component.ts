import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Usuario } from '../entidades/usuario';
import { UsuarioService } from '../servicios/usuario.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-actualizar',
  imports:[ CommonModule, FormsModule, HttpClientModule],
  templateUrl: './actualizar.component.html',
  styleUrl: './actualizar.component.css'
})
export class ActualizarComponent {
  usuario: Usuario = new Usuario();
  mensaje: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router) {}

ngOnInit(): void {
    const datosUsuario = this.usuarioService.getUsuarioSesion();
    if (datosUsuario) {
      this.usuario = { ...datosUsuario }; 
    } else {
      this.mensaje = 'No se encontró un usuario en sesión';
    }
  }

actualizarPerfil() {
  this.usuarioService.actualizarUsuario(this.usuario).subscribe({
    next: (res) => {
      this.mensaje = 'Perfil actualizado correctamente';
      setTimeout(() => this.router.navigate(['/principal']), 1500);
    },
    error: (err) => {
      this.mensaje = 'Error al actualizar perfil';
    }
  });
}
    volver() {
    this.router.navigate(['/principal']); 
  }
}




