import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private baseUrl = 'http://localhost:8080/pedidos';

  constructor(private http: HttpClient) {}

  generarPedido(cedula: number, direccion: string, ciudad: string, referencia: string): Observable<string> {
    const params = new HttpParams()
      .set('cedula', cedula)
      .set('direccion', direccion)
      .set('ciudad', ciudad)
      .set('referencia', referencia);

    return this.http.post(`${this.baseUrl}/generar`, {}, { params, responseType: 'text' });
  }

  obtenerHistorial(cedula: number): Observable<any[]> {
  return this.http.get<any[]>(`${this.baseUrl}/historial`, {
    params: { cedula: cedula.toString() }
  });
}
obtenerDetallePedido(idPedido: number): Observable<any> {
  return this.http.get(`${this.baseUrl}/detalle`, {
    params: { idPedido },
    responseType: 'json'
  });
}
cancelarPedido(idPedido: number) {
  return this.http.put(`${this.baseUrl}/cancelar`, null, {
    params: { idPedido: idPedido.toString() },
    responseType: 'text'
  });
}

}
