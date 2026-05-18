import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private api = '/api/auth';

  constructor(private http: HttpClient, private router: Router) {}

  // =========================
  // 🔐 LOGIN
  // =========================
  login(dto: any) {
    return this.http.post(`${this.api}/login`, dto);
  }

  // =========================
  // 💾 TOKEN
  // =========================
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    localStorage.removeItem('logado');
    this.router.navigate(['/']);
  }

  // =========================
  // 👤 USUÁRIO
  // =========================
  getUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  getUserName(): string | null {
    return this.getUser()?.nome ?? null;
  }

  getUserRole(): string | null {
    return this.getUser()?.perfil ?? null;
  }

  // =========================
  // 🔐 PERMISSÕES
  // =========================
  isAdmin(): boolean {
    const user = this.getUser();
    return user?.perfil === 'ADMIN';
  }

  isUser(): boolean {
    const user = this.getUser();
    return user?.perfil === 'OPERADOR';
  }
}
