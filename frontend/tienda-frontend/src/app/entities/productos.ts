import { Administrador } from "./administrador";

export class Productos {
    idProducto: number;
    cantidad: number;
    descripcion: string;
    imagen: string;
    nombre: string;
    precio: number;
    idAdministrador: Administrador;
    idCarrito: number;

    constructor(){
        this.idProducto = 0;
        this.cantidad = 0;
        this.descripcion = '';
        this.imagen = '';
        this.nombre = '';
        this.precio = 0;
        this.idAdministrador = new Administrador();
        this.idCarrito = 0;
    }
}
