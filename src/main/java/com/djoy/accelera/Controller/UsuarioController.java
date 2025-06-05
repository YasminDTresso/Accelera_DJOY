package com.djoy.accelera.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.Dtos.CriarUsuarioDto;
import com.djoy.accelera.Entity.Dtos.LoginUsuarioDto;
import com.djoy.accelera.Entity.Dtos.ObterTokenJwtDto;
import com.djoy.accelera.Entity.Dtos.ObterUsuarioDto;
import com.djoy.accelera.Service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    /* ================Incluir================ */
    @PostMapping
    public ResponseEntity<Void> incluir(@RequestBody CriarUsuarioDto usuario) {
        boolean novo = usuarioService.criarUsuario(usuario);

        if (novo) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /* ================Login================ */
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginUsuarioDto loginUsuarioDto) {
        try {
            ObterTokenJwtDto token = usuarioService.autenticarUsuario(loginUsuarioDto);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

    /* ================Listar Todos================ */
    @GetMapping
    public ResponseEntity<List<ObterUsuarioDto>> listarTodos() {
        List<ObterUsuarioDto> lista = usuarioService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    /* ================Editar================ */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> editar(@PathVariable int id,
            @RequestBody UsuarioEntity usuario) {
        UsuarioEntity atualizado = usuarioService.editar(id, usuario);

        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* ================Excluir================ */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable PessoaFisicaEntity id) {
        usuarioService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}