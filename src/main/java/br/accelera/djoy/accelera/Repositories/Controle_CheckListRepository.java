package br.accelera.djoy.accelera.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.accelera.djoy.accelera.Entities.Controle_CheckListEntity;

@Repository
public interface Controle_CheckListRepository extends JpaRepository<Controle_CheckListEntity, Integer> {

}
