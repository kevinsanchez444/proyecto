import { Component, ElementRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { UsuarioService } from '../servicios/usuario.service';
import { PedidoService } from '../servicios/pedido.service';
import { FormsModule } from '@angular/forms';



import { Modal } from 'bootstrap';

@Component({
  selector: 'app-principal',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent {

  mensaje: string = '';
  direccion = '';
  ciudad = '';
  referencia = '';
  mostrarFormulario = false;
  resumenPedido: any = null;


  @ViewChild('modalPedido') modalPedido!: ElementRef;

  constructor(
    private router: Router,
    private usuarioService: UsuarioService,
    private pedidoService: PedidoService
  ) {}

  cerrarSesion(): void {
    this.usuarioService.limpiarSesion();
    this.router.navigate(['/']);
  }

  generarPedido() {
    const usuario = this.usuarioService.getUsuarioSesion();

    if (!usuario || !usuario.cedula) {
      this.mensaje = 'Debes iniciar sesión para generar el pedido';
      return;
    }

    this.mostrarFormulario = true;
  }

  enviarPedido() {
  const usuario = this.usuarioService.getUsuarioSesion();

  if (!usuario || !usuario.cedula) {
    this.mensaje = 'Cédula no disponible. Inicia sesión.';
    return;
  }

  this.pedidoService.generarPedido(
    usuario.cedula,
    this.direccion,
    this.ciudad,
    this.referencia
  ).subscribe({
    next: (res: any) => {
      this.mensaje = res.mensaje;
      this.resumenPedido = res.pedido;
      this.direccion = this.ciudad = this.referencia = '';
      
  
      this.mostrarFormulario = false;

    
    const modalElement = document.getElementById('modalPedido') as HTMLElement;
if (modalElement) {
  const modalInstance = Modal.getOrCreateInstance(modalElement);
  setTimeout(() => {
    modalInstance.hide();
  }, 100); 
}
const backdrops = document.querySelectorAll('.modal-backdrop');
backdrops.forEach(b => b.remove());},
    error: (err) => {
      this.mensaje = 'Carrito vacío o error al generar el pedido';
      console.error(err);
    }
  });
}
  cancelarFormulario() {
    this.mostrarFormulario = false;
    this.direccion = this.ciudad = this.referencia = '';
  }
}
