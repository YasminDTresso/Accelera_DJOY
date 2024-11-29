package model;

public class UsuarioModel {
    private int pessoaFisicaId;  // Em vez de herdar id, criamos uma referência para pessoa_fisica_id
    private String senha;
    private String usuario;
    private String permissao;

    // Construtores
    public UsuarioModel() {
        this.pessoaFisicaId = 0;
        this.senha = "";
        this.usuario = "";
        this.permissao = "";
    }

    public UsuarioModel(int pessoaFisicaId, String senha, String usuario, String permissao) {
        this.pessoaFisicaId = pessoaFisicaId;
        this.senha = senha;
        this.usuario = usuario;
        this.permissao = permissao;
    }

    // Getters e Setters
    public int getPessoaFisicaId() {
        return pessoaFisicaId;
    }

    public void setPessoaFisicaId(int pessoaFisicaId) {
        this.pessoaFisicaId = pessoaFisicaId;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
}





