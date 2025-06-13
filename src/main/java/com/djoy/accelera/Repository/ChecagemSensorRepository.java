package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.ChecagemSensorEntity;

@Repository
public interface  ChecagemSensorRepository extends JpaRepository<ChecagemSensorEntity, Integer>{

}
