import { Carrito } from './../entities/carrito';
import { Component } from '@angular/core';
import generatePDF from '../lib/pdf'; // Adjust the import path as necessary
import { CarritoService } from '../servicio/carrito.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Productos } from '../entities/productos'; // Adjust the import path as necessary
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { OnInit } from '@angular/core';
import { promises } from 'dns';

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './carrito.component.html',
  styleUrl: './carrito.component.css'
})

export class CarritoComponent implements OnInit {
  productos: Productos[] = []; // Array para almacenar los productos
  carrito: Carrito[] = []; // Inicializa con un objeto Carrito vacío

  direccion: string = '';
  ciudad: string = '';
  referencia: string = '';
  mensaje: string = '';

  constructor(private carritoServicio: CarritoService) {}

  ngOnInit() {
    this.verProductos();
  }

  verProductos() {
    this.carritoServicio.obtenerProductos().subscribe((datos: Carrito[]) => {
      this.carrito = datos;
    }, error => {
      console.error('❌ Error al obtener productos del carrito:', error);
    });
  }

  get totalCompra(): number {
    return this.carrito.reduce((suma, item) => {
      return suma + (item.productos.precio * item.cantidad);
    }, 0);
  }

  eliminarProductos(idCarrito: number): void {
    this.carritoServicio.eliminarProducto(idCarrito).subscribe(dato => {
      try {
        alert('✅ Producto eliminado correctamente');
        this.ngOnInit();
      } catch (error) {
        console.error('❌ Error al eliminar el producto:', error);
        alert('⚠️ No se pudo eliminar el producto');
      }
    });
  }

  actualizarCantidad(idCarrito: number, cantidad: number): void {
    if (!cantidad || cantidad <= 0) return;

    this.carritoServicio.actualizarCantidad(idCarrito, cantidad).subscribe({
      next: (respuesta) => {
        alert('✅ Cantidad actualizada correctamente');
        this.ngOnInit();
      },
      error: (error) => {
        if (error.status === 400) {
          alert('⚠️ No se pudo actualizar: cantidad no disponible');
        }
      }
    });
  }

  vaciarCarrito(cedula: number): void {
    this.carritoServicio.vaciarCarrito(cedula).subscribe(dato => {
      try {
        if (dato != false) {
          alert('✅ Carrito vaciado correctamente');
          this.ngOnInit();
        } else {
          alert('⚠️ No se pudo vaciar el carrito: El carrito ya está vacío');
        }
      } catch (error) {
        console.error('❌ Error al vaciar el carrito:', error);
        alert('⚠️ No se pudo vaciar el carrito');
      }
    });
  }

  onGeneratePDF() {
    const productos = this.carrito.map(item => ({
      nombre: item.productos.nombre,
      cantidad: item.cantidad,
      total: item.productos.precio * item.cantidad
    }));

    const totalGeneral = this.totalCompra;
    const fecha = new Date().toLocaleDateString();
    const reciboNo = Math.floor(Math.random() * 100000).toString();

    generatePDF(productos, reciboNo, fecha);
  }

  enviarPedido(): void {
    this.mensaje = '✅ Pedido enviado correctamente (simulación)';
  }
}
