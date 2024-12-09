package model;

import java.time.LocalDateTime;

public class TrabalhosPendentesModel {
    private int id;
    private LocalDateTime dataInclusao;
    private TransportadoraModel transportadora;
    private String vinculo;
    private String placaVeiculo;
    private String placaCarreta;
    private String consulta;
    private String condutor;
    private String observacao;
    private String sinalTcell;
    private String sinalBrrisk;
    private String checkList;
    private String rota;
    private String sm;
    private UsuarioModel usuario;

    // Construtor vazio
    public TrabalhosPendentesModel() {
        id = 0;
        dataInclusao = LocalDateTime.now();
        transportadora = new TransportadoraModel();
        vinculo = new String();
        placaVeiculo = new String();
        placaCarreta = new String();
        consulta = new String();
        condutor = new String();
        observacao = new String();
        sinalTcell = new String();
        sinalBrrisk = new String();
        checkList = new String();
        rota = new String();
        sm = new String();
        usuario = new UsuarioModel();
    }

    // Construtor com parâmetros
    public TrabalhosPendentesModel(int id, LocalDateTime dataInclusao, TransportadoraModel transportadora,
            String vinculo, String placaVeiculo, String placaCarreta, String consulta,
            String condutor, String observacao, String sinalTcell, String sinalBrrisk,
            String checkList, String rota, String sm, UsuarioModel usuario) {
        this.id = id;
        this.dataInclusao = dataInclusao;
        this.transportadora = transportadora;
        this.vinculo = vinculo;
        this.placaVeiculo = placaVeiculo;
        this.placaCarreta = placaCarreta;
        this.consulta = consulta;
        this.condutor = condutor;
        this.observacao = observacao;
        this.sinalTcell = sinalTcell;
        this.sinalBrrisk = sinalBrrisk;
        this.checkList = checkList;
        this.rota = rota;
        this.sm = sm;
        this.usuario = usuario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public TransportadoraModel getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(TransportadoraModel transportadora) {
        this.transportadora = transportadora;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getPlacaCarreta() {
        return placaCarreta;
    }

    public void setPlacaCarreta(String placaCarreta) {
        this.placaCarreta = placaCarreta;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getCondutor() {
        return condutor;
    }

    public void setCondutor(String condutor) {
        this.condutor = condutor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSinalTcell() {
        return sinalTcell;
    }

    public void setSinalTcell(String sinalTcell) {
        this.sinalTcell = sinalTcell;
    }

    public String getSinalBrrisk() {
        return sinalBrrisk;
    }

    public void setSinalBrrisk(String sinalBrrisk) {
        this.sinalBrrisk = sinalBrrisk;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
