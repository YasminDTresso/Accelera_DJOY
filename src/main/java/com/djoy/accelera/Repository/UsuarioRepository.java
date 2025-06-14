package com.djoy.accelera.Repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.Enum.tipoPermissao;
import com.djoy.accelera.Entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

    // Consultar usuário pelo Login
    UserDetails findByLogin(String login);

    @Procedure(name = "sp_criarUsuario")
    void criarUsuario(
        @Param("v_cpf") String cpf,
        @Param("v_login") String login,
        @Param("v_permissao") tipoPermissao tipoPermissao,
        @Param("v_senha") String senha,
        @Param("v_nome") String nome,
        @Param("v_dtaNascimento") Date dataNascimento,
        @Param("v_email") String email,
        @Param("v_telefone") String telefone
    );

}
