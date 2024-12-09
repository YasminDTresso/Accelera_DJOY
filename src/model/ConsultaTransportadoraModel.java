package model;

import java.time.LocalDate;

public class ConsultaTransportadoraModel {
    private int id;
    private TransportadoraModel transportadora;
    private String objetoDaConsulta;
    private String vinculo;
    private String status;
    private LocalDate validade;
    private String observacao;
    private UsuarioModel usuario;

    // Construtor vazio
    public ConsultaTransportadoraModel() {
        id = 0;
        transportadora = new TransportadoraModel();
        objetoDaConsulta = new String();
        vinculo = new String();
        status = new String();
        validade = null;
        observacao = new String();
        usuario = new UsuarioModel();
    }

    // Construtor com parâmetros
    public ConsultaTransportadoraModel(int id, TransportadoraModel transportadora, String objetoDaConsulta,
            String vinculo, String status, LocalDate validade, String observacao, UsuarioModel usuario) {
        this.id = id;
        this.transportadora = transportadora;
        this.objetoDaConsulta = objetoDaConsulta;
        this.vinculo = vinculo;
        this.status = status;
        this.validade = validade;
        this.observacao = observacao;
        this.usuario = usuario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransportadoraModel getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(TransportadoraModel transportadora) {
        this.transportadora = transportadora;
    }

    public String getObjetoDaConsulta() {
        return objetoDaConsulta;
    }

    public void setObjetoDaConsulta(String objetoDaConsulta) {
        this.objetoDaConsulta = objetoDaConsulta;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}