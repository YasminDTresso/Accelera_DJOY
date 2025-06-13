package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

    // Consultar usuário pelo Logiin
    UserDetails findByLogin(String login);

    /*@Modifying
    @Query(value = "EXEC sp_criarUsuario :nome, :dtaNascimento, :email, :telefone, :cpf, :login, :permissao, :senha", nativeQuery = true)
    void cadastrarUsuario(
        @Param("nome") String nome,
        @Param("dataNascimento") Date dataNascimento,
        @Param("email") String email,
        @Param("telefone") String telefone,
        @Param("cpf") String cpf,
        @Param("login") String login,
        @Param("permissao") String permissao,
        @Param("senha") String senha
    );*/

}
