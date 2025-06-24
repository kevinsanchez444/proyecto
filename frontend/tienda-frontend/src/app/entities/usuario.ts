export class Usuario {
    cedula: number;
    apellido: string;
    contraseña: string;
    email: string;
    nombre: string;
    telefono:number;

    constructor() {
        this.cedula = 0;
        this.apellido = '';
        this.contraseña = '';
        this.email = '';
        this.nombre = '';
        this.telefono = 0;
    }
}
