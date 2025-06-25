import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Usuario } from '../entidades/usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private baseURL = 'http://localhost:8080/ver/usuarios';
  private usuarioEnSesion: Usuario | null = null;

  constructor(private http: HttpClient) {}

  iniciarSesion(usuario: Usuario): Observable<Usuario> {
    return new Observable(observer => {
      this.http.post<Usuario>(`${this.baseURL}/login`, usuario).subscribe(
        (response) => {
          this.usuarioEnSesion = response; 
          observer.next(response);
          observer.complete();
        },
        error => observer.error(error)
      );
    });
  }

  registrarUsuario(usuario: Usuario, confirmacion: string): Observable<any> {
    const params = new HttpParams()
      .set('cedula', usuario.cedula)
      .set('nombre', usuario.nombre)
      .set('apellido', usuario.apellido)
      .set('email', usuario.email)
      .set('telefono', usuario.telefono)
      .set('contraseña', usuario.contrasena)
      .set('confirmacion', confirmacion);

    return this.http.post(`${this.baseURL}/registro`, {}, { params, responseType: 'text' });
  }

  actualizarUsuario(usuario: Usuario): Observable<string> {
    return this.http.put(`${this.baseURL}/actualizar`, usuario, { responseType: 'text' });
  }

  getUsuarioSesion(): Usuario | null {
    return this.usuarioEnSesion;
  }

  setUsuarioSesion(usuario: Usuario): void {
    this.usuarioEnSesion = usuario;
  }

  eliminarUsuario(usuario: Usuario): Observable<string> {
    const params = new HttpParams()
      .set('email', usuario.email)
      .set('contrasena', usuario.contrasena); 

    return this.http.post('http://localhost:8080/ver/usuarios/eliminar', {}, { params, responseType: 'text' });
  }

  limpiarSesion(): void {
    this.usuarioEnSesion = null;
  }
}
