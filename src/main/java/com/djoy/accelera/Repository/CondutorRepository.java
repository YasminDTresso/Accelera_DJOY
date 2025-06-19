package com.djoy.accelera.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.CondutorEntity;


@Repository
public interface CondutorRepository extends JpaRepository<CondutorEntity, Integer>{

    Optional<CondutorEntity> findByCnh(String cnh);

    //====================Método para filtrar condutor====================	
    List<CondutorEntity> findByRegistroAtivoTrue();


}
