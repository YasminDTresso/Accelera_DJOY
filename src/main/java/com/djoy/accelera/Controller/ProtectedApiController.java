package com.djoy.accelera.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.Entity.UsuarioEntity;

@RestController
public class ProtectedApiController {

    @GetMapping("/api/protegida")
    public ResponseEntity<Map<String, Object>> getUserInfo(Authentication auth) {
        if (auth != null) {
            UsuarioEntity usuario = (UsuarioEntity) auth.getPrincipal();
            Map<String, Object> dados = new HashMap<>();
            dados.put("nome", usuario.getNome());
            dados.put("email", usuario.getUsername());
            dados.put("permissao", usuario.getAuthorities());
            return ResponseEntity.ok(dados);
        } else {
            // Se estiver sem autenticação, retorne 401 Unauthorized.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
