import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [FormsModule]
})
export class LoginComponent {
  idAdministrador: number = 0;
  contrasena: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  iniciarSesion() {
    const adminLogin = {
      idAdministrador: this.idAdministrador,
      contrasena: this.contrasena
    };

    this.http.post('http://localhost:8080/ver/adm1/login', adminLogin, { responseType: 'text' })
      .subscribe({
        next: (respuesta) => {
          alert(respuesta);
          this.router.navigate(['/administrador']);
        },
        error: (err) => {
          alert(err.error);
        }
      });
  }
}



