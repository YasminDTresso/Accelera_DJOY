package br.accelera.djoy.accelera.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.accelera.djoy.accelera.Entities.Log_ConsultaEntity;

@Repository
public interface Log_ConsultaRepository extends JpaRepository<Log_ConsultaEntity, Integer>{

}
