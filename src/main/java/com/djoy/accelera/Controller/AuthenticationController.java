package com.djoy.accelera.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.Entity.AuthenticationDTO;
import com.djoy.accelera.Entity.CadastroUsuarioDTO;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Repository.UsuarioRepository;
import com.djoy.accelera.Service.TokenService;
import com.djoy.accelera.Service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
public class AuthenticationController {
    @SuppressWarnings("unused")

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    // Método de Login
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        // Receber login e senha
        var Senhausuario = new UsernamePasswordAuthenticationToken(data.login(), data.senha());

        // Autenticar usuário - Encode da senha
        var auth = this.authenticationManager.authenticate(Senhausuario);

        // Gerar e retornar o token ao usuário
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    // Método de criar novos usuários
    @PostMapping("/cadastrar-usuario")
    public ResponseEntity register(@RequestBody @Valid CadastroUsuarioDTO data){
        /*Verificar se o usuário já existe */
        if(this.usuarioRepository.findByLogin(data.login()) != null && data.cpf() != null){
            return ResponseEntity.badRequest().build();
        } 

        // Encriptando a senha
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        // Criando usuário
        // UsuarioEntity novoUsuario = new UsuarioEntity(data.login(), encryptedPassword, data.tipoPermissao());
        usuarioService.incluir(data.cpf(), data.login(), data.tipoPermissao(), encryptedPassword, data.nome(), data.dataNascimento(), data.email(), data.telefone());
        // Salvando usuário
        // this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

}
