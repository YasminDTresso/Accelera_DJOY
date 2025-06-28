package com.djoy.accelera.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.djoy.accelera.DTO.EditarVeiculoDTO;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Repository.VeiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class VeiculoService {

    @SuppressWarnings("unused")

    private final VeiculoRepository veiculoRepository;

    /*====Incluir===*/
    public VeiculoEntity incluir(VeiculoEntity veiculo){

        return veiculoRepository.save(veiculo);

    }

    /*====Editar===*/
    public VeiculoEntity editar(int id, EditarVeiculoDTO veiculo){
        //Verifica se o registro existe
        Optional<VeiculoEntity> veiculoExistente = veiculoRepository.findById(id);

        if (veiculoExistente.isPresent()) {
            //Atualiza o registro
            VeiculoEntity veiculoAtualizado = veiculoExistente.get();

            // Atualiza os campos necessários
            veiculoAtualizado.setProprietario(veiculo.proprietario());
            veiculoAtualizado.setModelo(veiculo.modelo());
            veiculoAtualizado.setChassi(veiculo.chassi());
            veiculoAtualizado.setCor(veiculo.cor());
            veiculoAtualizado.setPlaca(veiculo.placa());
            veiculoAtualizado.setPlacaCarreta(veiculo.placaCarreta());

            // Salva o registro atualizado
            return veiculoRepository.save(veiculoAtualizado);

        }else{
            // Caso o registro não exista, retorna como nulo.
            return null;
        }

    }

    /*====Listar Todos===*/
    public List<VeiculoEntity> listarTodos(){
        return veiculoRepository.findAll();
    }

    /*====Deletar===*/
    public void excluir(Integer id){
        veiculoRepository.deleteById(id);
    } 
}
