import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../entidades/usuario';
import { UsuarioService } from '../servicios/usuario.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-registro',
  imports: [CommonModule, FormsModule],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {
    usuario: Usuario = new Usuario();
  confirmacion: string = '';
  mensaje: string = '';

  constructor(private usuarioService: UsuarioService,private router: Router) {}

  registrar() {
  this.usuarioService.registrarUsuario(this.usuario, this.confirmacion).subscribe({
    next: (res) => {
      this.mensaje = res;
      this.router.navigate(['/principal']); 
    },
    error: (err) => {
      this.mensaje = err.error?.mensaje || 'Error en el registro';
    }
  });
}
volverAlLogin() {
  this.router.navigate(['/login']);
}
}
