package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.Key.SocioTransportadoraKey;
import com.djoy.accelera.Entity.SocioTransportadoraEntity;

@Repository
public interface SocioTransportadoraRepository extends JpaRepository<SocioTransportadoraEntity, SocioTransportadoraKey>{

}
