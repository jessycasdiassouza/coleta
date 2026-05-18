import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const roleGuard = (roles: string[]): CanActivateFn => {

  return () => {

    const auth = inject(AuthService);
    const router = inject(Router);

    const user = auth.getUser();

    if (!user || !user.ativo) {
      router.navigate(['/login']);
      return false;
    }

    if (!roles.includes(user.perfil)) {
      router.navigate(['/admin']);
      return false;
    }

    return true;
  };
};
