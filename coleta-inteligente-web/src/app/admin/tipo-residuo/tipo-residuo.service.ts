import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TipoResiduoService {

  private api = '/api/admin/tipos-residuo';

  constructor(private http: HttpClient) {}

  listar() {
    return this.http.get<any[]>(this.api);
  }

  criar(tipo: any) {
    return this.http.post(this.api, tipo);
  }

  atualizar(id: number, tipo: any) {
    return this.http.put(`${this.api}/${id}`, tipo);
  }

  deletar(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }
}
