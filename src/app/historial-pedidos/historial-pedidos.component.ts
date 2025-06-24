import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PedidoService } from '../servicios/pedido.service';
import { UsuarioService } from '../servicios/usuario.service';

@Component({
  selector: 'app-historial-pedidos',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './historial-pedidos.component.html',
  styleUrls: ['./historial-pedidos.component.css']
})
export class HistorialPedidosComponent implements OnInit {
  pedidos: any[] = [];
  pedidoSeleccionado: any = null;

  constructor(
    private pedidoService: PedidoService,
    private usuarioService: UsuarioService
  ) {}

  ngOnInit(): void {
    const usuario = this.usuarioService.getUsuarioSesion();
    if (usuario && usuario.cedula) {
      this.pedidoService.obtenerHistorial(usuario.cedula).subscribe({
        next: (data: any[]) => this.pedidos = data,
        error: (err) => console.error('Error al obtener el historial', err)
      });
    }
  }

  verDetalles(idPedido: number) {
    this.pedidoService.obtenerDetallePedido(idPedido).subscribe({
      next: (data) => this.pedidoSeleccionado = data,
      error: () => alert('No se pudieron cargar los detalles del pedido')
    });
  }

  cancelarPedido(idPedido: number) {
    if (!confirm('¿Estás seguro de que deseas cancelar este pedido?')) return;

    this.pedidoService.cancelarPedido(idPedido).subscribe({
      next: () => {
        alert('Pedido cancelado correctamente');

  
        if (this.pedidoSeleccionado?.idPedidos === idPedido) {
          this.pedidoSeleccionado.estado = 'Cancelado';
        }

        const pedidoIndex = this.pedidos.findIndex(p => p.idPedidos === idPedido);
        if (pedidoIndex !== -1) {
          this.pedidos[pedidoIndex].estado = 'Cancelado';
        }
      },
      error: () => alert('Error al cancelar el pedido')
    });
  }
}
