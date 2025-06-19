package com.djoy.accelera.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.djoy.accelera.Entity.Enum.statusUsuario;
import com.djoy.accelera.Entity.Enum.tipoPermissao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name="usuarios")
@Table(name="usuarios")
@EqualsAndHashCode(of = "id", callSuper=false)
@NoArgsConstructor
@Data
public class UsuarioEntity extends PessoaFisicaEntity implements UserDetails{
    
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private int id;

    @Column(nullable = false, unique = true)
    private String login;
    
    @Column(nullable = false)
    private String senha;

    @Column(name = "permissao", nullable = false)   
    @Enumerated(EnumType.STRING)
    private tipoPermissao tipoPermissao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private statusUsuario status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            // Autoridade Administrador
            if(this.tipoPermissao == tipoPermissao.ADMINISTRADOR) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));

            // Autoridade usuário
            else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    // Construtores
    public UsuarioEntity(String login, String senha, tipoPermissao tipoPermissao){
        this.login = login;
        this.senha = senha;
        this.tipoPermissao = tipoPermissao;
    }

}
