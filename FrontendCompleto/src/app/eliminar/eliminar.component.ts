import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../servicios/usuario.service';
import { Usuario } from '../entidades/usuario';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-eliminar',
  standalone: true,
  templateUrl: './eliminar.component.html',
  styleUrls: ['./eliminar.component.css'],
  imports: [CommonModule, FormsModule, HttpClientModule]
})
export class EliminarComponent implements OnInit {
  usuario: Usuario = new Usuario();
  mensaje: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router) {}

  ngOnInit(): void {
    const usuarioSesion = this.usuarioService.getUsuarioSesion();
    if (usuarioSesion) {
      this.usuario.cedula = usuarioSesion.cedula;
    } else {
      this.mensaje = 'No hay usuario en sesión';
    }
  }

  eliminarCuenta(): void {
    this.usuarioService.eliminarUsuario(this.usuario).subscribe(
      res => {
        this.mensaje = res;
        setTimeout(() => this.router.navigate(['/']), 2000); 
      },
      err => {
        this.mensaje = 'Error al eliminar la cuenta';
      }
    );
  }
  volver() {
  this.router.navigate(['/principal']); 
}
}