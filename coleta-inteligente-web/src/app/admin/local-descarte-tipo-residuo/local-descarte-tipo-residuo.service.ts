import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LocalDescarteTipoResiduoService {

  private api = '/api/admin/local-residuo';

  constructor(private http: HttpClient) {}

  listar() {
    return this.http.get<any[]>(this.api);
  }

  vincular(localId: number, tipoId: number) {
    return this.http.post(
      `${this.api}?localId=${localId}&tipoId=${tipoId}`,
      {}
    );
  }

  remover(localId: number, tipoId: number) {
    return this.http.delete(
      `${this.api}?localId=${localId}&tipoId=${tipoId}`
    );
  }
}
