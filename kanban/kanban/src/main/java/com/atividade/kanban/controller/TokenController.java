package com.atividade.kanban.controller;

import com.atividade.kanban.controller.dto.LoginRequest;
import com.atividade.kanban.controller.dto.LoginResponse;
import com.atividade.kanban.repository.UsuarioRepository;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;

    private final UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UsuarioRepository usuarioRepository) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        var usuario = usuarioRepository.findByUsername(loginRequest.username());

        if (usuario.isEmpty() || !usuario.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)) {
            throw  new BadCredentialsException("Usuario ou senha invalidos");
        }
        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(usuario.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));

    }
}
