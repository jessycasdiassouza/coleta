import { Component, signal } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterOutlet, RouterLink, Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    MatToolbarModule,
    RouterOutlet,
    RouterLink,
    MatButtonModule,
    NgIf
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {

  protected readonly title = signal('Coleta Inteligente');

  constructor(private router: Router) {}

  get isLogado(): boolean {
    return localStorage.getItem('logado') === 'true';
  }

  logout() {
    localStorage.removeItem('logado');
    this.router.navigate(['/']);
  }
}
