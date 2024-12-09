package model;

import java.util.Scanner;

public class UsuarioModel {
    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String usuario;
    private String permissao;

    // Construtores
    public UsuarioModel() {
        id = 0;
        nome = new String();
        cpf = new String();
        senha = new String();
        usuario = new String();
        permissao = new String();
    }

    public UsuarioModel(int id, String nome, String cpf, String senha, String usuario, String permissao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.usuario = usuario;
        this.permissao = permissao;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
