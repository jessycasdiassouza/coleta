import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConsultaPontosService } from './consulta-pontos.service';

@Component({
  selector: 'app-consulta',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  pontosColeta: any[] = [];

  expandedId: number | null = null;

  constructor(
    private service: ConsultaPontosService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    this.carregarPontos();

  }

  carregarPontos(): void {

    this.service.listar().subscribe({
      next: (dados) => {

        console.log(dados);

        this.pontosColeta = [...dados];
        this.cdr.detectChanges();

      },
      error: (erro) => {
        console.error(erro);
      }
    });

  }

  toggleExpand(id: number): void {

    this.expandedId =
      this.expandedId === id
        ? null
        : id;

  }

}
