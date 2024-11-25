package com.victorgadelha.library_management.infra.jwt;

import java.time.Instant;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.victorgadelha.library_management.app.usecases.user.FindUserByEmailUseCase;
import com.victorgadelha.library_management.web.dtos.auth.JWTLoginResponseDTO;

@Service
public class JWTService {
    private final JwtEncoder jwtEncoder;
    private final FindUserByEmailUseCase findUserByEmailUseCase;

    public JWTService(JwtEncoder jwtEncoder, FindUserByEmailUseCase findUserByEmailUseCase) {
        this.jwtEncoder = jwtEncoder;
        this.findUserByEmailUseCase = findUserByEmailUseCase;
    }

    public JWTLoginResponseDTO generateToken(String email) {
        var now = Instant.now();
        var expiresIn = 300L;

        var user = this.findUserByEmailUseCase.execute(email);

        var scope = user.role();


        var claims = JwtClaimsSet.builder()
                .issuer("library-management")
                .subject(user.id().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scope)
                .build();

        return new JWTLoginResponseDTO(jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(), expiresIn);
    }
}
