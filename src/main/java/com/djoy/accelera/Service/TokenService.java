package com.djoy.accelera.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.djoy.accelera.Entity.UsuarioEntity;

@Service
public class TokenService {
    // Variável de ambiente
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UsuarioEntity usuario){
        try {
            
            // Permitindo que os algoritmos de rest sejam únicos por aplicação, através da secret
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                            .withIssuer("accelera-api")
                            .withSubject(usuario.getLogin())
                            .withExpiresAt(genExpirationDate())
                            .sign(algorithm);
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            System.out.println("Header: " + decodedJWT.getHeader());
            System.out.println("Payload: " + decodedJWT.getPayload());
            System.out.println("Signature: " + decodedJWT.getSignature());
        } catch (JWTDecodeException e) {
            System.out.println("Erro ao decodificar token: " + e.getMessage());
        }            
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token:", exception);
        }
    }

    // Defininindo o tempo de expiração do token para 2hrs
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    // Método para validar token gerado
    public String validateToken(String token){
         try {
            DecodedJWT decodedJWT = JWT.decode(token);
            System.out.println("Header: " + decodedJWT.getHeader());
            System.out.println("Payload: " + decodedJWT.getPayload());
            System.out.println("Signature: " + decodedJWT.getSignature());
        } catch (JWTDecodeException e) {
            System.out.println("Erro ao decodificar token: " + e.getMessage());
        }   
        try {
            
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                      .withIssuer("accelera-api")
                      .build()
                      .verify(token)
                      .getSubject(); 
        } catch (JWTVerificationException exception) {
            return "Erro ao validar token!!!\n";
        }
    }

}
