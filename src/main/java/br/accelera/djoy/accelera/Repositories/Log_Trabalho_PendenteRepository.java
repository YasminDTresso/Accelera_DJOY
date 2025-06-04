package br.accelera.djoy.accelera.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.accelera.djoy.accelera.Entities.Log_Trabalho_PendenteEntity;

@Repository
public interface Log_Trabalho_PendenteRepository extends JpaRepository<Log_Trabalho_PendenteEntity, Integer>{

}
