package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.PessoaFisicaEntity;
import com.djoy.accelera.Entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, PessoaFisicaEntity>{

}
