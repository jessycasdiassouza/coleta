package br.com.coleta_inteligente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.coleta_inteligente.model.Perfil;
import br.com.coleta_inteligente.model.Usuario;
import br.com.coleta_inteligente.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Usuario criar(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setAtivo(true);
        if (usuario.getPerfil() == null) {
            usuario.setPerfil(Perfil.OPERADOR);
        }
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Usuario atualizar(Long id, Usuario novo) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setNome(novo.getNome());
        usuario.setEmail(novo.getEmail());
        usuario.setPerfil(novo.getPerfil());
        usuario.setAtivo(novo.getAtivo());
        if (novo.getSenha() != null && !novo.getSenha().isBlank()) {
            usuario.setSenha(encoder.encode(novo.getSenha()));
        }
        return repository.save(usuario);
    }

    public void inativar(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setAtivo(false);
        repository.save(usuario);
    }
}