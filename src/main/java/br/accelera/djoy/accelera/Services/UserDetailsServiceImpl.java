package br.accelera.djoy.accelera.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.accelera.djoy.accelera.Entities.UsuarioEntity;
import br.accelera.djoy.accelera.Infra.UserDetailsImpl;
import br.accelera.djoy.accelera.Repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Tentando carregar usuário: [" + username + "]");

        UsuarioEntity usuario = usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return new UserDetailsImpl(usuario);
    }
}
