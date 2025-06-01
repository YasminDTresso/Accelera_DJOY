package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.AgendaEntity;

@Repository
public interface  AgendaRepository extends JpaRepository<AgendaEntity, Integer>{

}
