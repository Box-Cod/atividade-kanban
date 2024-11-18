package com.atividade.kanban.config;

import com.atividade.kanban.enums.Papel;
import com.atividade.kanban.model.Usuario;
import com.atividade.kanban.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class UsuarioAdimConfig implements CommandLineRunner {

    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioAdimConfig(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var usuarioAdmin = usuarioRepository.findByUsername("ADMIN");

        usuarioAdmin.ifPresentOrElse(
                usuario -> {
                    System.out.println("admin já existe");
                },
                () -> {
                    var usuario = new Usuario();
                    usuario.setUsername("ADMIN");
                    usuario.setPassword(passwordEncoder.encode("123"));
                    usuarioRepository.save(usuario);
                }
        );
    }

}
