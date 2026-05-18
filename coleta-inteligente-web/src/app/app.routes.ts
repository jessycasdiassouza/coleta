import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdminLayoutComponent } from './admin/layout/admin-layout.component';
import { ConsultaComponent } from './publico/consulta/consulta.component';
import { PublicoComponent } from './publico/publico.component';

import { authGuard } from './auth/guards/auth.guard';
import { roleGuard } from './auth/guards/role.guard';

export const routes: Routes = [

  { path: 'login', component: LoginComponent },

  { path: 'publico', component: PublicoComponent },

  { path: 'consulta', component: ConsultaComponent },

  {
    path: 'admin',
    component: AdminLayoutComponent,
    canActivate: [authGuard],
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./admin/principal/admin-principal.component')
            .then(m => m.AdminPrincipalComponent)
      },

      {
        path: 'usuarios',
        loadComponent: () =>
          import('./admin/usuario/usuario-admin.component')
            .then(m => m.UsuarioAdminComponent),
        canActivate: [roleGuard(['ADMIN'])]
      },

      {
        path: 'local-descarte',
        loadComponent: () =>
          import('./admin/local-descarte/local-descarte.component')
            .then(m => m.LocalDescarteComponent),
        canActivate: [roleGuard(['ADMIN', 'OPERADOR'])]
      },

      {
        path: 'tipo-residuo',
        loadComponent: () =>
          import('./admin/tipo-residuo/tipo-residuo.component')
            .then(m => m.TipoResiduoComponent),
        canActivate: [roleGuard(['ADMIN', 'OPERADOR'])]
      },

      {
        path: 'local-descarte-tipo-residuo',
        loadComponent: () =>
          import('./admin/local-descarte-tipo-residuo/local-descarte-tipo-residuo.component')
            .then(m => m.LocalDescarteTipoResiduoComponent),
        canActivate: [roleGuard(['ADMIN', 'OPERADOR'])]
      },

    ]
  },

  { path: '', redirectTo: 'publico', pathMatch: 'full' }
];
