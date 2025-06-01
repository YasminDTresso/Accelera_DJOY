package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.CondutorTransportadoraEntity;
import com.djoy.accelera.Entity.Key.CondutorTransportadoraKey;

@Repository
public interface  CondutorTransportadoraRepository extends JpaRepository<CondutorTransportadoraEntity, CondutorTransportadoraKey>{

}
