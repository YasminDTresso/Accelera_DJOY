package com.djoy.accelera.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djoy.accelera.Entity.InserirConsultaDTO;
import com.djoy.accelera.Service.ConsultaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //colocando isso não precisa colocar @Autowired no atributo
@RequestMapping(value = "/consulta")
public class ConsultaController {

    @Autowired
    private final ConsultaService consultaService;

    //Método criar nova consulta
    @PostMapping("/nova-consulta")
    public ResponseEntity register(@RequestBody @Valid InserirConsultaDTO data){

    consultaService.incluir(data.usuarioInclusao(), data.transportadora(), data.condutor(), data.veiculo(), data.validade(), data.observacao(), data.status(), data.vinculo());

    return ResponseEntity.ok().build();
}
}
