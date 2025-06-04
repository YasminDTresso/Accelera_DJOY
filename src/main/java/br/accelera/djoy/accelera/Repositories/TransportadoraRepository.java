package br.accelera.djoy.accelera.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.accelera.djoy.accelera.Entities.TransportadoraEntity;

@Repository
public interface TransportadoraRepository extends JpaRepository<TransportadoraEntity, Integer>{

}
