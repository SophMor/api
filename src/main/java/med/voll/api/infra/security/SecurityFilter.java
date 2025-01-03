package med.voll.api.infra.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
     @Autowired
    private TokenService tokenService;
     @Autowired
     private UsuarioRepository usuarioRepository;
    @Override
    //Obtener tOKEN del header
    //Bearer es el prefijo
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token  = request.getHeader("Authorization");
//Activa != null
        if (token != null ) {
         token = token.replace("Bearer ", "");
         var subject = tokenService.getSubject(token);
         if(subject != null) {
             //token valido
             var usuario = usuarioRepository.findByLogin(subject);
             var autenthication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
             SecurityContextHolder.getContext()
                     .setAuthentication(autenthication);
         }
            //OBTENGO EL TOKEN Y FUERZA EL INICIO SESION

                }
        filterChain.doFilter(request, response);

    }

}

