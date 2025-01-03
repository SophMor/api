package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.usuarios.Usuarios;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;
    /*Metodo encargado de generar el token
    * con el metodo hash3
    * @Param{Usuario}*/
    public String generarToken(Usuarios usuarios){

        try{
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String token = JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuarios.getUsername())
                    .withClaim("id",usuarios.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
            return token;
            //ISSUAES QUIEN
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token){
      if (token==null){throw new RuntimeException("NULL");}
        DecodedJWT verifier = null;
        try {
         Algorithm algorithm = Algorithm.HMAC256(apiSecret);

        verifier  = JWT.require(algorithm)
                 .withIssuer("voll med")
                 .build()
                 .verify(token);

         verifier.getSubject();
     } catch (JWTVerificationException e) {
         throw new RuntimeException(e);
     }
        if(verifier.getSubject() == null){
            throw  new RuntimeException("invalid");
        }
     return verifier.getSubject();
    }

}
