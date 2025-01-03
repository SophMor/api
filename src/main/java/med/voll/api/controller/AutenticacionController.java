package med.voll.api.controller;

import med.voll.api.infra.security.TokenService;
import med.voll.api.domain.dto.DatosJWTOken;
import med.voll.api.domain.usuarios.DatosAutenticacionUser;
import med.voll.api.domain.usuarios.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    public AutenticacionController() {}
    @PostMapping
    public ResponseEntity autenticacionUser(@RequestBody DatosAutenticacionUser datosAutenticacionUser) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUser.login(), datosAutenticacionUser.clave());
        var usuario =authenticationManager.authenticate(authToken);

        var JWToken = tokenService.generarToken((Usuarios) usuario.getPrincipal());
        return ResponseEntity.ok(new DatosJWTOken(JWToken));
    }



    @GetMapping
    public ResponseEntity<String> loginInfo() {
        return ResponseEntity.ok("Login endpoint - use POST for authentication");
    }
}
