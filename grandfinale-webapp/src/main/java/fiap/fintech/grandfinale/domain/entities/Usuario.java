package fiap.fintech.grandfinale.domain.entities;

import fiap.fintech.grandfinale.domain.enums.Autenticador;
import fiap.fintech.grandfinale.domain.enums.Sexo;
import fiap.fintech.grandfinale.domain.exceptions.EntradaDadoInvalidaException;

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
    private Autenticador autenticador;
    private List<ControleFinanceiro> controleFinanceiroLista;

    private Usuario(){
        super();
        this.controleFinanceiroLista = new ArrayList<>();
    }
    public Usuario(String nome, String sobreNome, Sexo sexo, LocalDate dataNascimento, String email){
        this();
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.setDataHoraCadastro(LocalDateTime.now());
    }

    public Sexo getSexo() { return sexo; }
    public String getNome() {
        return nome;
    }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getSobreNome() { return sobreNome; }
    public Autenticador getAutenticador() { return autenticador; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public List<ControleFinanceiro> getControleFinanceiroLista() { return controleFinanceiroLista; }

    public Usuario definirAutenticador(Autenticador autenticador, String senha){
        this.autenticador = autenticador;
        this.senha = senha;
        return this;
    }
    public Boolean validarCredencial(String email, String senha){
        return this.email.equals(email)
                && (this.autenticador == Autenticador.Interno && this.senha.equals(senha)
                || this.autenticador == Autenticador.Externo && senha == null);
    }
    public Usuario criarControleFinanceiro(String descricao) throws EntradaDadoInvalidaException {
        for (var controleFinanceiro : this.controleFinanceiroLista) {
            if(controleFinanceiro.getDescricao().equalsIgnoreCase(descricao)){
                throw new EntradaDadoInvalidaException("Já existe um controle financeiro com essa descrição!");
            }
        }

        var controleFinanceiro = new ControleFinanceiro(this.getCodigo(), descricao);
        controleFinanceiro.adicionarParticipante(this.getCodigo());

        controleFinanceiroLista.add(controleFinanceiro);
        return this;
    }
    public Boolean validarControleFinanceiro(int codigo) throws EntradaDadoInvalidaException {
        return obterControleFinanceiro(codigo) != null;
    }
    public ControleFinanceiro obterControleFinanceiro(int codigo) throws EntradaDadoInvalidaException {
        for (var item : this.controleFinanceiroLista){
            if(item.getCodigo() == codigo)
                return item;
        }

        throw new EntradaDadoInvalidaException(String.format("Não foi encontrado nenhum controle para o código %d", codigo));
    }
    public void removerFinanceiro(int codigo) throws EntradaDadoInvalidaException {
        if(!validarControleFinanceiro(codigo))
            throw new EntradaDadoInvalidaException(String.format("Não foi encontrado nenhum controle para o código %d", codigo));

        this.controleFinanceiroLista.removeIf(item -> item.getCodigo() == codigo);
    }
}
