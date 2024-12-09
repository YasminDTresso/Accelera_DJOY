package model;

public class UsuarioTransportadoraModel {
    private UsuarioModel usuario;
    private TransportadoraModel transportadora;

    // Construtor vazio
    public UsuarioTransportadoraModel() {
        usuario = new UsuarioModel();
        transportadora = new TransportadoraModel();
    }

    // Construtor com parâmetros
    public UsuarioTransportadoraModel(UsuarioModel usuario, TransportadoraModel transportadora) {
        this.usuario = usuario;
        this.transportadora = transportadora;
    }

    // Getters e Setters
    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public TransportadoraModel getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(TransportadoraModel transportadora) {
        this.transportadora = transportadora;
    }
}