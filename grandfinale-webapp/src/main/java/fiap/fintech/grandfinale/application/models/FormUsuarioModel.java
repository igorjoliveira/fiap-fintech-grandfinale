package fiap.fintech.grandfinale.application.models;

import fiap.fintech.grandfinale.domain.enums.Autenticador;
import fiap.fintech.grandfinale.domain.enums.Sexo;

import java.time.LocalDate;

public class FormUsuarioModel {
    private int codigo;
    private Sexo sexo;
    private String nome;
    private String email;
    private String senha;
    private String sobreNome;
    private LocalDate dataNascimento;
    private Boolean ativo;
    private Autenticador autenticador;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Autenticador getAutenticador() {
        return autenticador;
    }

    public void setAutenticador(Autenticador autenticador) {
        this.autenticador = autenticador;
    }
}