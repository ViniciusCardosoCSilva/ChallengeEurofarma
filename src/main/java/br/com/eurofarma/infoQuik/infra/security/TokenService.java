package br.com.eurofarma.infoQuik.infra.security;

import br.com.eurofarma.infoQuik.model.Funcionario;
import br.com.eurofarma.infoQuik.model.Treinador;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;

    public String generateToken(Treinador treinador){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("infoquik-api")
                    .withSubject(treinador.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
//            throw new RuntimeException("Error while generating token", exception);
            return "";

        }
    }

    public String generateTokenFuncionario(Funcionario funcionario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("infoquik-api")
                    .withSubject(funcionario.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while generating token", exception);
//            return "";

        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var tokenValidado = JWT.require(algorithm)
                    .withIssuer("infoquik-api")
                    .build()
                    .verify(token);

            String subject = tokenValidado.getSubject();

            System.out.println(subject);

            return  subject;

        }catch (JWTVerificationException e){
//            e.printStackTrace();
//            throw new RuntimeException("erro na validação do token");
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
