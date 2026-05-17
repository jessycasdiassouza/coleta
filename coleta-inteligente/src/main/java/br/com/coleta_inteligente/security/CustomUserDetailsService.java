package br.com.coleta_inteligente.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.coleta_inteligente.model.Usuario;
import br.com.coleta_inteligente.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public CustomUserDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado")
                );

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),usuario.getSenha(), usuario.getAtivo(), true, true, true,
                List.of(() -> "ROLE_" + usuario.getPerfil().name())
        );
    }
}