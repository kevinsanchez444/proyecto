import { Component } from '@angular/core';
import { Usuario } from '../entidades/usuario';
import { UsuarioService } from '../servicios/usuario.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [CommonModule, FormsModule,]
})
export class LoginComponent {
  usuario: Usuario = new Usuario();
  mensaje: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router) {}
iniciarSesion() {
  this.usuarioService.iniciarSesion(this.usuario).subscribe({
    next: (usuario: Usuario) => {
      this.usuarioService.setUsuarioSesion(usuario);

    
      if (usuario.cedula) {
        localStorage.setItem('cedula', usuario.cedula.toString());
      }

      this.router.navigate(['/principal']);
    },
    error: () => {
      this.mensaje = 'Credenciales incorrectas';
    }
  });
}
irARegistro() {
  this.router.navigate(['/registro']);
}
}
