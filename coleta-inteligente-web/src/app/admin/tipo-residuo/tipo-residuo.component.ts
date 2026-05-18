import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { TipoResiduoService } from './tipo-residuo.service';

@Component({
  selector: 'app-tipo-residuo',
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
  templateUrl: './tipo-residuo.component.html',
  styleUrls: ['./tipo-residuo.component.css']
})
export class TipoResiduoComponent {

  tipos: any[] = [];

  tipo: any = {
    nomeTipo: '',
    descricaoTipo: ''
  };

  editando = false;

  constructor(
    private service: TipoResiduoService,
    private cd: ChangeDetectorRef,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.atualizarLista();
  }

  listar() {
    this.service.listar().subscribe(data => {
      this.tipos = [...data];
      this.cd.detectChanges();
    });
  }

  salvar() {

    if (this.editando && this.tipo.tipoResiduoId) {

      this.service.atualizar(this.tipo.tipoResiduoId, this.tipo)
        .subscribe(() => {
          this.atualizarLista();
        });

    } else {

      this.service.criar(this.tipo)
        .subscribe(() => {
          this.atualizarLista();
        });
    }
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
  }

  editar(tipo: any) {

    this.tipo = { ...tipo };

    this.editando = true;
  }

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

    this.tipo = {
      nomeTipo: '',
      descricaoTipo: ''
    };

    this.editando = false;
  }

  get isLogado(): boolean {
    return localStorage.getItem('logado') === 'true';
  }
}
