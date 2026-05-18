import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';

import { LocalDescarteService } from '../local-descarte/local-descarte.service';
import { TipoResiduoService } from '../tipo-residuo/tipo-residuo.service';
import { LocalDescarteTipoResiduoService } from './local-descarte-tipo-residuo.service';


@Component({
  selector: 'app-local-descarte-tipo-residuo',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    MatTableModule,
    MatSelectModule
  ],
  templateUrl: './local-descarte-tipo-residuo.component.html',
  styleUrls: ['./local-descarte-tipo-residuo.component.css']
})
export class LocalDescarteTipoResiduoComponent {

  locais: any[] = [];
  tipos: any[] = [];
  vinculados: any[] = [];
  loading = true;

  localId!: number;
  tipoId!: number;

  constructor(
    private localService: LocalDescarteService,
    private tipoService: TipoResiduoService,
    private vinculoService: LocalDescarteTipoResiduoService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.carregarLocais();
    this.carregarTipos();
    this.atualizarLista();

  }

  carregarLocais() {
    this.localService.listar().subscribe(data => this.locais = data);
  }

  carregarTipos() {
    this.tipoService.listar().subscribe(data => this.tipos = data);
  }

  listarVinculos() {
    this.vinculoService.listar().subscribe(data => {
      this.vinculados = [...data];
      this.cd.detectChanges();
    });
  }

  vincular() {

    this.vinculoService.vincular(this.localId, this.tipoId)
      .subscribe(() => {

        this.atualizarLista();

        this.localId = 0;
        this.tipoId = 0;
      });
  }

  remover(v: any) {
    this.vinculoService.remover(v.localId, v.tipoId).subscribe(() => {
      this.atualizarLista();
    });
  }


  atualizarLista() {
    this.listarVinculos();
    this.cd.detectChanges();
  }

  get isLogado(): boolean {
    return localStorage.getItem('logado') === 'true';
  }

}
