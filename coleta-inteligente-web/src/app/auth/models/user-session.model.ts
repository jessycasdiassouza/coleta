export interface UserSession {
  usuarioId: number;
  nome: string;
  email: string;
  perfil: 'ADMIN' | 'OPERADOR';
  ativo: boolean;
  token?: string | null;
}
