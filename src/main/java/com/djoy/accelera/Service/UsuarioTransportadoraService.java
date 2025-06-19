package com.djoy.accelera.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.djoy.accelera.Entity.UsuarioTransportadoraEntity;
import com.djoy.accelera.Entity.Key.UsuarioTransportadoraKey;
import com.djoy.accelera.Repository.UsuarioTransportadoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class UsuarioTransportadoraService {

    @SuppressWarnings("unused")

    private final UsuarioTransportadoraRepository usuarioTransportadoraRepository;

    /*====Incluir===*/
    public UsuarioTransportadoraEntity incluir(UsuarioTransportadoraEntity usuarioTransportadora){

        return usuarioTransportadoraRepository.save(usuarioTransportadora);

    }

    /*====Listar Todos===*/
    public List<UsuarioTransportadoraEntity> listarTodos(){
        return usuarioTransportadoraRepository.findAll();
    }

        /*====Deletar===*/
    public void excluir(UsuarioTransportadoraKey id){
        usuarioTransportadoraRepository.deleteById(id);
    } 

}
