package com.djoy.accelera.Service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.UsuarioEntity;

import com.djoy.accelera.Repository.UsuarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.djoy.accelera.Entity.Enum.tipoPermissao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class UsuarioService {
    @SuppressWarnings("unused")

    @PersistenceContext
    private EntityManager entityManager;
    
    private final UsuarioRepository usuarioRepository;

    private Authentication authentication;

    /*====Incluir===*/
    @Transactional
     public void incluir(
        String cpf, String login, tipoPermissao permissao, String senha,
        String nome, Date dataNascimento, String email, String telefone) {

        // Tratando a data de nascimento recebida
        LocalDate dataNascimentoLocal = dataNascimento.toInstant()
        .atZone(ZoneId.of("UTC")) //Garante que não há erro de fuso horário
        .toLocalDate();
        java.sql.Date dataSQL = java.sql.Date.valueOf(dataNascimentoLocal);  

        Query query = entityManager.createNativeQuery("EXEC sp_criarUsuario ?, ?, ?, ?, ?, ?, ?, ?")
            .setParameter(1, cpf)
            .setParameter(2, login)
            .setParameter(3, permissao.toString())
            .setParameter(4, senha)
            .setParameter(5, nome)
            .setParameter(6, dataSQL)
            .setParameter(7, email)
            .setParameter(8, telefone);

        try {
            query.executeUpdate();
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
        }
        //    System.out.println("Executando procedure com: CPF=" + cpf + ", Login=" + login + ", Permissao =" + permissao + ", Senha =" + senha + ", Nome ="  + nome + ", dataNascimento =" + new java.sql.Date(dataNascimento.getTime()) + ", email=" + email + ", telefone =" +telefone);
    }

    // Captar id do usuario logado
    public Integer getUsuarioLogadoId() {
     authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getPrincipal() instanceof UsuarioEntity) {
        return ((UsuarioEntity) authentication.getPrincipal()).getId();
    }

        return null; // Retorna null caso não esteja autenticado
    }

}