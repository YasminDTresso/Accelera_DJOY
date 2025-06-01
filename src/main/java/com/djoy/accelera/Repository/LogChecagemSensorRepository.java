package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.LogChecagemSensorEntity;

@Repository
public interface LogChecagemSensorRepository extends JpaRepository<LogChecagemSensorEntity, Integer>{

}
