package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.TransportadoraEntity;

@Repository
public interface TransportadoraRepository extends JpaRepository<TransportadoraEntity, Integer>{

}
