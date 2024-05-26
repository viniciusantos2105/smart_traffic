package com.smart_traffic.config.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.smart_traffic.exceptions.commons.TokenException;
import com.smart_traffic.models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerandoToken(UserModel user)  {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("smart-traffic-API")
                    .withSubject(user.getUserEmail())
                    .withExpiresAt(gerandoTempoToken())
                    .sign(algorithm);
        } catch (Exception ex) {
            throw TokenException.createTokenException("Token invalido");
        }
    }


    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("smart-traffic-API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception ex) {
            throw TokenException.createTokenException("Token invalido");
        }
    }

    private Instant gerandoTempoToken() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }

}
