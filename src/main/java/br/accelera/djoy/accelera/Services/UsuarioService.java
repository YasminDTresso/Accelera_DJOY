package br.accelera.djoy.accelera.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.accelera.djoy.accelera.Entities.UsuarioEntity;
import br.accelera.djoy.accelera.Entities.Dtos.CriarUsuarioDto;
import br.accelera.djoy.accelera.Entities.Dtos.LoginUsuarioDto;
import br.accelera.djoy.accelera.Entities.Dtos.ObterTokenJwtDto;
import br.accelera.djoy.accelera.Entities.Dtos.ObterUsuarioDto;
import br.accelera.djoy.accelera.Infra.SecurityConfiguration;
import br.accelera.djoy.accelera.Infra.UserDetailsImpl;
import br.accelera.djoy.accelera.Repositories.UsuarioRepository;
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
                    .nome(criarUsuarioDto.nome())
                    .usuario(criarUsuarioDto.usuario())
                    .cpf(criarUsuarioDto.cpf())
                    .senha(securityConfiguration.passwordEncoder().encode(criarUsuarioDto.senha()))
                    .permissao(criarUsuarioDto.tipoPermissao())
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

            usuarioAtualizado.setCpf(usuario.getCpf());
            usuarioAtualizado.setNome(usuario.getNome());
            usuarioAtualizado.setPermissao(usuario.getPermissao());
            usuarioAtualizado.setSenha(usuario.getSenha());
            usuarioAtualizado.setUsuario(usuario.getUsuario());

            return usuarioRepository.save(usuarioAtualizado);
        } else {
            return null;
        }

    }

    public List<ObterUsuarioDto> listarTodos() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuario -> new ObterUsuarioDto(
                        (long) usuario.getId(),
                        usuario.getNome(),
                        usuario.getUsuario(),
                        usuario.getCpf(),
                        usuario.getPermissao()))
                .collect(Collectors.toList());
    }

    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
