import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PrincipalComponent } from './principal/principal.component';
import { RegistroComponent } from './registro/registro.component';
import { ActualizarComponent } from './actualizar/actualizar.component';
import { EliminarComponent } from './eliminar/eliminar.component';

export const routes: Routes = [
{ path: '', component: LoginComponent },
{ path: 'principal', component: PrincipalComponent },
{ path: 'registro', component: RegistroComponent },
{ path: 'actualizar', component: ActualizarComponent },
{ path: 'eliminar', component: EliminarComponent },
{ path: 'login', component: LoginComponent },
{ path: 'historial',loadComponent: () => import('./historial-pedidos/historial-pedidos.component').then(m => m.HistorialPedidosComponent)}];