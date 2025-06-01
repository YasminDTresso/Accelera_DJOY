package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.LogAgendaEntity;

@Repository
public interface LogAgendaRepository extends JpaRepository<LogAgendaEntity, Integer>{

}
