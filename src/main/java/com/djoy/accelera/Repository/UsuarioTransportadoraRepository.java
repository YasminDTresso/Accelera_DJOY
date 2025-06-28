package com.djoy.accelera.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djoy.accelera.Entity.UsuarioTransportadoraEntity;
import com.djoy.accelera.Entity.Key.UsuarioTransportadoraKey;

@Repository
public interface UsuarioTransportadoraRepository extends JpaRepository<UsuarioTransportadoraEntity, UsuarioTransportadoraKey>{

}
