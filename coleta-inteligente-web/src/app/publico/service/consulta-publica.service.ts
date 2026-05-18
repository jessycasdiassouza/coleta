import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { LocalDescarte } from '../model/local-descarte';

@Injectable({
  providedIn: 'root'
})
export class ConsultaPublicaService {

  private api = '/api/publico/consulta';

  constructor(
    private http: HttpClient
  ) {}

  listar(): Observable<LocalDescarte[]> {

    return this.http.get<LocalDescarte[]>(
      this.api
    );
  }
}
