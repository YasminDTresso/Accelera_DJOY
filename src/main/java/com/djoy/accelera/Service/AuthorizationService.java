package com.djoy.accelera.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.djoy.accelera.Repository.UsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    UsuarioRepository usuarioRepository;

    // Método de Consulta de Usuários
    // Por Login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails usuario = usuarioRepository.findByLogin(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario não encontrado" + username);
        }

        return usuarioRepository.findByLogin(username);

    }

}
