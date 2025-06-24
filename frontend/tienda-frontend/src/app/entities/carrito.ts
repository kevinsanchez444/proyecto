import { Productos } from "./productos";
import { Usuario } from "./usuario";

export class Carrito {
  idCarrito: number;
  cantidad: number;
  productos: Productos; // <== debe llamarse igual que en el JSON
  idUsuario: Usuario;

  constructor() {
    this.idCarrito = 0;
    this.cantidad = 0;
    this.productos = new Productos();
    this.idUsuario = new Usuario();
  }
}

