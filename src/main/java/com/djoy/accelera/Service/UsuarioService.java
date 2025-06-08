package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.Dtos.CriarUsuarioDto;
import com.djoy.accelera.Entity.Dtos.LoginUsuarioDto;
import com.djoy.accelera.Entity.Dtos.ObterTokenJwtDto;
import com.djoy.accelera.Entity.Dtos.ObterUsuarioDto;
import com.djoy.accelera.Infra.SecurityConfiguration;
import com.djoy.accelera.Infra.UserDetailsImpl;
import com.djoy.accelera.Repository.UsuarioRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfiguration securityConfiguration;
    private final UsuarioRepository usuarioRepository;

    // Pega o usuario e a senha do usuário chamando o serviço de login para que ele 
    // possa fazer as verificações e possivelmente gerar o token de autenticação 
    public ObterTokenJwtDto autenticarUsuario(LoginUsuarioDto loginUsuarioDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginUsuarioDto.usuario(), loginUsuarioDto.senha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        return new ObterTokenJwtDto(
                jwtTokenService.generateToken(userDetailsImpl));
    }

    public boolean criarUsuario(CriarUsuarioDto criarUsuarioDto) {
        try {
            UsuarioEntity usuario = UsuarioEntity.builder()
                    .user(criarUsuarioDto.usuario())
                    .senha(securityConfiguration.passwordEncoder().encode(criarUsuarioDto.senha()))
                    .tipoPermissao(criarUsuarioDto.tipoPermissao())
                    .build();

            usuarioRepository.save(usuario);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UsuarioEntity editar(int id, UsuarioEntity usuario) {
        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            UsuarioEntity usuarioAtualizado = usuarioExistente.get();

            usuarioAtualizado.setUser(usuario.getUser());
            usuarioAtualizado.setTipoPermissao(usuario.getTipoPermissao());
            usuarioAtualizado.setSenha(usuario.getSenha());

            return usuarioRepository.save(usuarioAtualizado);
        } else {
            return null;
        }

    }

    public List<ObterUsuarioDto> listarTodos() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuario -> new ObterUsuarioDto(
                        usuario.getId(),
                        usuario.getUser(),
                        usuario.getTipoPermissao()))
                .collect(Collectors.toList());
    }

    public void excluir(PessoaFisicaEntity id) {
        usuarioRepository.deleteById(id);
    }
}