import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';


import { LocalDescarteService } from './local-descarte.service';

@Component({
  selector: 'app-local-descarte',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatSnackBarModule
  ],
  templateUrl: './local-descarte.component.html',
  styleUrls: ['./local-descarte.component.css']
})
export class LocalDescarteComponent {

  locais: any[] = [];

  local: any = {
    titulo: '',
    descricao: '',
    logradouro: '',
    numero: null,
    complemento: '',
    referencia: '',
    bairro: '',
    cidade: '',
    uf: ''
  };

  editando = false;

  constructor(
    private service: LocalDescarteService,
    private cd: ChangeDetectorRef,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {

    this.atualizarLista();
  }

  listar() {

    this.service.listar().subscribe(data => {

      this.locais = [...data];

      this.cd.detectChanges();
    });
  }

  salvar() {

    if (this.editando && this.local.localDescarteId) {

      this.service.atualizar(
        this.local.localDescarteId,
        this.local
      ).subscribe(() => {

        this.atualizarLista();
      });

    } else {

      this.service.criar(this.local)
        .subscribe(() => {

          this.snackBar.open(
            'Registro salvo com sucesso.',
            'Fechar',
            {
              duration: 3000,
              horizontalPosition: 'center',
              verticalPosition: 'bottom',
              panelClass: ['snackbar-sucesso']
            }
          );

          this.atualizarLista();
        });
    }
  }

  editar(local: any) {

    this.local = { ...local };

    this.editando = true;
  }

  /*deletar(id: number) {

    this.service.deletar(id)
      .subscribe(() => this.listar());
  }*/


  deletar(id: number) {
    this.service.deletar(id).subscribe({
      next: () => {
        this.atualizarLista();
        this.snackBar.open(
          'Registro removido com sucesso.',
          'Fechar',
          { duration: 3000 }
        );
      },

      error: (erro) => {

        this.snackBar.open(
        'Não é possível excluir. Existem vínculos associados.',
        'Fechar',
        {
          duration: 4000,
          horizontalPosition: 'right',
          verticalPosition: 'top',
          panelClass: ['snackbar-erro']
        }
      );
      }

    });
  }

  atualizarLista() {

    this.listar();

    this.limpar();

    this.cd.detectChanges();
  }

  limpar() {

    this.local = {
      titulo: '',
      descricao: '',
      logradouro: '',
      numero: null,
      complemento: '',
      referencia: '',
      bairro: '',
      cidade: '',
      uf: ''
    };

    this.editando = false;
  }

  get isLogado(): boolean {
    return localStorage.getItem('logado') === 'true';
  }

}
