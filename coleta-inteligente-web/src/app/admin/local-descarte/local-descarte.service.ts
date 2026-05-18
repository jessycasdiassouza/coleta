import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LocalDescarteService {

  private api = '/api/admin/locais-descarte';

  constructor(private http: HttpClient) {}

  listar() {
    return this.http.get<any[]>(this.api);
  }

  criar(local: any) {
    return this.http.post(this.api, local);
  }

  atualizar(id: number, local: any) {
    return this.http.put(`${this.api}/${id}`, local);
  }

  deletar(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }
}
