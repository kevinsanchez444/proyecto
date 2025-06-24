
export class Usuario {
  cedula: number;
  nombre: string ;
  apellido: string ;
  email: string ;
  telefono: number;
  contrasena: string ;

  constructor() {
    this.cedula = 0;
    this.nombre = '';
    this.apellido = '';
    this.email = '';
    this.telefono = 0;
    this.contrasena = '';
  }
}