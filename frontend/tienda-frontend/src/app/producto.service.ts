import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private url = 'http://localhost:8080/ver/productos';

  

  constructor(private http: HttpClient) { }

  obtenerProductos(): Observable<any[]> {
    return this.http.get<any[]>(this.url);
  }
}
