package br.com.fiap.fintechgrandfinale.domain.entities;

import br.com.fiap.fintechgrandfinale.domain.enums.Autenticador;
import br.com.fiap.fintechgrandfinale.domain.enums.Sexo;
import br.com.fiap.fintechgrandfinale.domain.exceptions.EntradaDadoInvalidaException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends BaseModel {
    private Sexo sexo;
    private String nome;
    private String email;
    private String senha;
    private String sobreNome;
    private LocalDate dataNascimento;
    private Boolean ativo;
    private Autenticador autenticador;
    private List<ControleFinanceiro> controleFinanceiroLista;

    public Sexo getSexo() {
        return sexo;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public String getSobreNome() {
        return sobreNome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public Autenticador getAutenticador() {
        return autenticador;
    }
    public List<ControleFinanceiro> getControleFinanceiroLista() { return controleFinanceiroLista; }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public void setAutenticador(Autenticador autenticador) {
        this.autenticador = autenticador;
    }
    public void setControleFinanceiroLista(List<ControleFinanceiro> controleFinanceiroLista) {
        this.controleFinanceiroLista = controleFinanceiroLista;
    }

    public Usuario(){
        super();
        this.controleFinanceiroLista = new ArrayList<>();
    }
    public Usuario(String nome, String sobreNome, Sexo sexo, LocalDate dataNascimento, String email, Autenticador autenticador, String senha){
        this();
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.ativo = true;
        this.autenticador = autenticador;
        this.senha = senha;
        this.setDataHoraCadastro(LocalDateTime.now());
    }
    public Usuario(int codigo, String nome, String sobreNome, Sexo sexo, LocalDate dataNascimento, String email, Boolean ativo, Autenticador autenticador, String senha, LocalDateTime dataHoraCadastro, LocalDateTime dataHoraAtualizacao){
        super(codigo, dataHoraCadastro, dataHoraAtualizacao);
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.ativo = ativo;
        this.autenticador = autenticador;
        this.senha = senha;
    }

    public Boolean validarCredencial(String senha){
        return this.ativo
                && (this.autenticador == Autenticador.Interno && this.senha.equals(senha)
                || this.autenticador == Autenticador.Externo && senha == null);
    }
}
