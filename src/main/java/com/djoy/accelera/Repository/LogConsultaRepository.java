package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.LogConsultaEntity;

@Repository
public interface LogConsultaRepository extends JpaRepository<LogConsultaEntity, Integer>{

}
