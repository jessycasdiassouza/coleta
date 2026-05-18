import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class UsuarioService {

  private api = '/api/admin/usuarios';

  constructor(private http: HttpClient) {}

  listar() {
    return this.http.get<any[]>(this.api);
  }

  criar(u: any) {
    return this.http.post(this.api, u);
  }

  atualizar(id: number, u: any) {
    return this.http.put(`${this.api}/${id}`, u);
  }

  inativar(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }
}
