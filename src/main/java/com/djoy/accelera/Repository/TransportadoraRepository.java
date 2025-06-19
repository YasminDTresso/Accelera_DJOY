package com.djoy.accelera.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.TransportadoraEntity;

@Repository
public interface TransportadoraRepository extends JpaRepository<TransportadoraEntity, Integer>{

    Optional<TransportadoraEntity> findByCnpj(String cnpj);

    //====================Método para filtrar transportadora====================	
    List<TransportadoraEntity> findByRegistroAtivoTrue();

}
