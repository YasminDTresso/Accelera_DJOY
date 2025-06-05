package com.djoy.accelera.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, PessoaFisicaEntity>{
    Optional<UsuarioEntity> findById(int id);
    Optional<UsuarioEntity> findByUser(String usuario); 
}
