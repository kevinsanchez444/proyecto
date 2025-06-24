import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CarritoService {
n: any;
  constructor(private httpClient: HttpClient) { }

private bdTraer: string= 'http://localhost:8080/ver/carrito/verTodo';
public obtenerProductos(): Observable<any> {
  return this.httpClient.get(this.bdTraer);
}

private bdElim: string = 'http://localhost:8080/ver/carrito/eliminar';
public eliminarProducto(idCarrito: number): Observable<Object> {
  this.n= this.httpClient.post(`${this.bdElim}`,idCarrito);
  return this.n
}

private bdActualizar: string = 'http://localhost:8080/ver/carrito/actualizarCantidad';
public actualizarCantidad(idCarrito: number, cantidad: number): Observable<Object> {
  return this.httpClient.post(`${this.bdActualizar}`, { idCarrito, cantidad });
}
private bdVaciar: string = 'http://localhost:8080/ver/carrito/vaciarCarrito';

public vaciarCarrito(cedula: number): Observable<Object> {
  return this.httpClient.post(`${this.bdVaciar}?cedula=${cedula}`, {}); // enviar body vacío
}


  
}

