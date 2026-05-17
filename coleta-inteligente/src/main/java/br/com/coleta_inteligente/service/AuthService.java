package br.com.coleta_inteligente.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.coleta_inteligente.dto.auth.LoginRequestDTO;
import br.com.coleta_inteligente.dto.auth.LoginResponseDTO;
import br.com.coleta_inteligente.model.Usuario;
import br.com.coleta_inteligente.repository.UsuarioRepository;
import br.com.coleta_inteligente.security.JwtService;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final UsuarioRepository repository;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authManager, UsuarioRepository repository, JwtService jwtService) {
        this.authManager = authManager;
        this.repository = repository;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        try {
            // 1. valida email + senha via Spring Security
            authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.senha()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Email ou senha inválidos");
        }
        // 2. busca usuário apenas por email
        Usuario usuario = repository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        // 3. gera token JWT
        String token = jwtService.generateToken(usuario);
        // 4. retorna resposta
        return new LoginResponseDTO(usuario.getUsuarioId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil().name(), usuario.getAtivo(), token);
    }
}