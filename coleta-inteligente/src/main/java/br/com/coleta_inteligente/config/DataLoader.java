package br.com.coleta_inteligente.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.coleta_inteligente.model.Perfil;
import br.com.coleta_inteligente.model.Usuario;
import br.com.coleta_inteligente.repository.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public DataLoader(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        boolean existeAdmin = repository.findByEmail("admin@coleta.com").isPresent();//verifica se existe usuario admin no banco de dados.
        if (!existeAdmin) {//caso não exista, cria um usuário admin com email "
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@coleta.com");
            admin.setSenha(encoder.encode("123456"));
            admin.setAtivo(true);
            admin.setPerfil(Perfil.ADMIN);
            repository.save(admin);
            System.out.println("Usuário admin criado com sucesso!");
        }
    }
}