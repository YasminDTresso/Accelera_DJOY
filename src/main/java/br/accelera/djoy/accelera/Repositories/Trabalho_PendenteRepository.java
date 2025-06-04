package br.accelera.djoy.accelera.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.accelera.djoy.accelera.Entities.Trabalho_PendenteEntity;

@Repository
public interface Trabalho_PendenteRepository extends JpaRepository<Trabalho_PendenteEntity, Integer>{


}
