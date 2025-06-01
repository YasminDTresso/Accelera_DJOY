package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.PessoaFisicaEntity;

@Repository
public interface CondutorRepository extends JpaRepository<CondutorEntity, PessoaFisicaEntity>{

}
