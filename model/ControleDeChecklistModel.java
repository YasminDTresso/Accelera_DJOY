package model;

import java.time.LocalDate;

public class ControleDeChecklistModel {
    private int id;
    private TransportadoraModel transportadora;
    private String gestor;
    private String placa;
    private String equipamento;
    private String vinculo;
    private String statusCl;
    private LocalDate validade;
    private String programacao;
    private String problemaEquipamento;
    private LocalDate inicioProblema;
    private String observacao;
    private UsuarioModel usuario;

    // Construtor vazio
    public ControleDeChecklistModel() {
        id = 0;
        transportadora = new TransportadoraModel();
        gestor = new String();
        placa = new String();
        equipamento = new String();
        vinculo = new String();
        statusCl = new String();
        validade = null;
        programacao = new String();
        problemaEquipamento = new String();
        inicioProblema = null;
        observacao = new String();
        usuario = new UsuarioModel();
    }

    // Construtor com parâmetros
    public ControleDeChecklistModel(int id, TransportadoraModel transportadora, String gestor,
            String placa, String equipamento, String vinculo, String statusCl,
            LocalDate validade, String programacao, String problemaEquipamento,
            LocalDate inicioProblema, String observacao, UsuarioModel usuario) {
        this.id = id;
        this.transportadora = transportadora;
        this.gestor = gestor;
        this.placa = placa;
        this.equipamento = equipamento;
        this.vinculo = vinculo;
        this.statusCl = statusCl;
        this.validade = validade;
        this.programacao = programacao;
        this.problemaEquipamento = problemaEquipamento;
        this.inicioProblema = inicioProblema;
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

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getStatusCl() {
        return statusCl;
    }

    public void setStatusCl(String statusCl) {
        this.statusCl = statusCl;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getProgramacao() {
        return programacao;
    }

    public void setProgramacao(String programacao) {
        this.programacao = programacao;
    }

    public String getProblemaEquipamento() {
        return problemaEquipamento;
    }

    public void setProblemaEquipamento(String problemaEquipamento) {
        this.problemaEquipamento = problemaEquipamento;
    }

    public LocalDate getInicioProblema() {
        return inicioProblema;
    }

    public void setInicioProblema(LocalDate inicioProblema) {
        this.inicioProblema = inicioProblema;
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