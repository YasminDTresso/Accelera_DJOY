package model;


public class TransportadoraModel {
    private int id;
    private String nome;
    private Integer fixo;
    private Integer agregado;
    private Integer terceiro;
    private Integer consultaFixo;
    private Integer consultaAgregado;
    private Integer consultaTerceiro;

    // Construtores
    public TransportadoraModel() {
        id = 0;
        nome = new String();
        fixo = null;
        agregado = null;
        terceiro = null;
        consultaFixo = null;
        consultaAgregado = null;
        consultaTerceiro = null;
    }

    public TransportadoraModel(int id, String nome, Integer fixo, Integer agregado, Integer terceiro,
            Integer consultaFixo, Integer consultaAgregado, Integer consultaTerceiro) {
        this.id = id;
        this.nome = nome;
        this.fixo = fixo;
        this.agregado = agregado;
        this.terceiro = terceiro;
        this.consultaFixo = consultaFixo;
        this.consultaAgregado = consultaAgregado;
        this.consultaTerceiro = consultaTerceiro;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFixo() {
        return fixo;
    }

    public void setFixo(Integer fixo) {
        this.fixo = fixo;
    }

    public Integer getAgregado() {
        return agregado;
    }

    public void setAgregado(Integer agregado) {
        this.agregado = agregado;
    }

    public Integer getTerceiro() {
        return terceiro;
    }

    public void setTerceiro(Integer terceiro) {
        this.terceiro = terceiro;
    }

    public Integer getConsultaFixo() {
        return consultaFixo;
    }

    public void setConsultaFixo(Integer consultaFixo) {
        this.consultaFixo = consultaFixo;
    }

    public Integer getConsultaAgregado() {
        return consultaAgregado;
    }

    public void setConsultaAgregado(Integer consultaAgregado) {
        this.consultaAgregado = consultaAgregado;
    }

    public Integer getConsultaTerceiro() {
        return consultaTerceiro;
    }

    public void setConsultaTerceiro(Integer consultaTerceiro) {
        this.consultaTerceiro = consultaTerceiro;
    }

}

