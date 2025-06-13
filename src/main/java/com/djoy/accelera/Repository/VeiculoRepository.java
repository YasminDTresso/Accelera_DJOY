package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.VeiculoEntity;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Integer>{

}
