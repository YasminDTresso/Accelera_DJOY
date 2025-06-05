package com.djoy.accelera.Infra;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.djoy.accelera.Entity.UsuarioEntity;

import lombok.Getter;

/**
 * A classe UserDetailsImpl formata o usuário autenticado antes dele entrar na sessão  
 **/
@Getter
public class UserDetailsImpl implements UserDetails {

    private UsuarioEntity usuario;

    public UserDetailsImpl(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (usuario.getTipoPermissao() == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new SimpleGrantedAuthority(usuario.getTipoPermissao().name()));
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getUser();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}