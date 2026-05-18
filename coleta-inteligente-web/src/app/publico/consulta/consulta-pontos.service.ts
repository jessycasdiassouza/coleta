import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalDescarteConsultaDTO } from './local-descarte-consulta.dto';

@Injectable({
  providedIn: 'root'
})
export class ConsultaPontosService {

  private api = '/api/publico/consulta';

  constructor(private http: HttpClient) {}

  listar(): Observable<LocalDescarteConsultaDTO[]> {
    return this.http.get<LocalDescarteConsultaDTO[]>(this.api);
  }
}
