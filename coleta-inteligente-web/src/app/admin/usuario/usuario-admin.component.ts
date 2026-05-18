import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from './usuario.service';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { ChangeDetectorRef } from '@angular/core';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-usuario-admin',
  standalone: true,
  imports: [CommonModule,
    FormsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatSelectModule,
    MatSnackBarModule
  ],
  templateUrl: './usuario-admin.component.html',
  styleUrls: ['./usuario-admin.component.css']
})
export class UsuarioAdminComponent {

  usuarios: any[] = [];

  usuario: any = {
    nome: '',
    email: '',
    senha: '',
    perfil: 'OPERADOR',
    ativo: true
  };

  editando = false;

  constructor(private service: UsuarioService, private cd: ChangeDetectorRef, private snackBar: MatSnackBar) {

  }

  ngOnInit(): void {
    this.autalizarLista();
  }

  listar() {
    this.service.listar().subscribe(data => {
      this.usuarios = [...data];//força atualização visual da tabela
      this.cd.detectChanges();
    });
  }

  salvar() {
    if (this.editando && this.usuario.usuarioId) {
      this.service.atualizar(this.usuario.usuarioId, this.usuario).subscribe(() => {
          this.autalizarLista();
        });

    } else {
      this.service.criar(this.usuario).subscribe(() => {
          this.autalizarLista();
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

  editar(u: any) {
    this.usuario = { ...u };
    this.editando = true;
  }

  toggleStatus(u: any) {
    const usuarioAtualizado = {
      ...u,
      ativo: !u.ativo
    };
    this.service.atualizar(u.usuarioId, usuarioAtualizado).subscribe(() => this.autalizarLista());
  }

  autalizarLista() {
    this.listar();
    this.limpar();
    this.cd.detectChanges();
  }

  limpar() {
    this.usuario.nome = '';
    this.usuario.email = '';
    this.usuario.senha = '';
    this.usuario.perfil = 'OPERADOR';
    this.usuario.ativo = true;
    this.usuario.usuarioId = null;
    this.editando = false;
  }

  get isLogado(): boolean {
    return localStorage.getItem('logado') === 'true';
  }

}
