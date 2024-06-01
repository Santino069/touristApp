package com.example.ReservaTourBack.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() != null && loginRequest.getUsername().equals("santino") &&
                loginRequest.getPassword().equals("s123456789")) {
            // Aquí deberías validar las credenciales y generar el token JWT
            String token = generateJwtToken(loginRequest.getUsername());
            String perfil = "Admin"; // Obtener el perfil del usuario según tus necesidades
            return ResponseEntity.ok(new JwtAuthenticationResponse(token, perfil));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    private String generateJwtToken(String username) {
        // Aquí deberías generar el token JWT utilizando alguna librería como jjwt
        return "token"; // Ejemplo: implementación simplificada
    }
}
