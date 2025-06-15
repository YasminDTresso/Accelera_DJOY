package com.djoy.accelera.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djoy.accelera.Entity.CondutorEntity;
import com.djoy.accelera.Entity.Enum.statusEtapa;
import com.djoy.accelera.Entity.Enum.tipoVinculo;
import com.djoy.accelera.Entity.TransportadoraEntity;
import com.djoy.accelera.Entity.UsuarioEntity;
import com.djoy.accelera.Entity.VeiculoEntity;
import com.djoy.accelera.Repository.ConsultaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //ao usar isso não precisa do @Autowired no objeto
public class ConsultaService {

    @SuppressWarnings("unused")

    @PersistenceContext
    private EntityManager entityManager;

    private final ConsultaRepository consultaRepository;

    /*====Incluir===*/
    @Transactional
    public void incluir(UsuarioEntity usuarioInclusao, TransportadoraEntity transportadora, 
                        CondutorEntity condutor, VeiculoEntity veiculo, LocalDateTime validade, 
                        String observacao, statusEtapa status, tipoVinculo vinculo)
    {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String validadeFormatada = validade.format(formatter);

        Query query = entityManager.createNativeQuery("EXEC sp_criarConsulta ?, ?, ?, ?, ?, ?, ?, ?")
              .setParameter(1, usuarioInclusao.getId())
              .setParameter(2, transportadora.getId())
              .setParameter(3, condutor.getId())
              .setParameter(4, veiculo.getId())  
              .setParameter(5, validadeFormatada)
              .setParameter(6, observacao)
              .setParameter(7, status.toString())
              .setParameter(8, vinculo.toString())                
              ;
        // System.out.println("Executando a procedure com:" + "\nusuarioInclusao " + usuarioInclusao.getId()+ "\ntransportadora " + transportadora.getId()+ "\ncondutor " + condutor.getId()+ "\nveiculo " + veiculo.getId()+ "\nvalidade " +"2025-06-15T12:00:00"+ "\nobservacao " +observacao+ "\nstatus " + status+ "\nvinculo " + vinculo);

        // Query query = entityManager.createNativeQuery("EXEC sp_criarConsulta '1', '2', '3', '1', '"+validadeFormatada+"', 'Teste', '"+ status +"', 'FIXO'");

        try {
            query.executeUpdate();
            entityManager.flush();
            entityManager.clear();
        } catch (Exception e) {
            e.printStackTrace(); // Isso pode revelar possíveis falhas que não aparecem no log
        }

        System.out.println("Executando a procedure com:" + "usuarioInclusao" + usuarioInclusao.getId()+ "transportadora" + transportadora.getId()+ "condutor" + condutor.getId()+ "veiculo" + veiculo.getId()+ "validade" +validadeFormatada+ "observacao" +observacao+ "status" + status+ "vinculo" + vinculo);
    }
}
