import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

import { AuthService } from '../auth/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loading = false;
  error = '';

  form: any;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    this.form = this.fb.group({

      email: ['', [
        Validators.required,
        Validators.email
      ]],

      senha: ['', [
        Validators.required
      ]]
    });
  }

  login(): void {

    this.error = '';

    if (this.form.invalid) {
      this.error = 'Preencha todos os campos corretamente.';
      return;
    }

    this.loading = true;

    this.authService.login(this.form.value)
      .subscribe({

        next: (res: any) => {

          localStorage.setItem('token', res.token);
          localStorage.setItem('user', JSON.stringify(res));
          localStorage.setItem('logado', 'true');

          this.loading = false;
          console.log(res);
          this.router.navigate(['/admin']);
        },

        error: (err) => {
          console.error(err);
          this.error = err.error?.message || 'Erro ao fazer login';
          this.loading = false;
          this.cdr.detectChanges();
        }
      });
  }
}
