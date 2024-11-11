package br.com.fiap.fintechgrandfinale.domain.entities;

import java.time.LocalDateTime;
import java.util.Random;

public abstract class BaseModel{
    private int codigo;
    private LocalDateTime dataHoraCadastro;
    private LocalDateTime dataHoraAtualizacao;

    public BaseModel() {
        this.setCodigo((new Random().nextInt(100)) + 1);
    }
    public BaseModel(int codigo) {
        this.codigo = codigo;
    }
    public BaseModel(int codigo, LocalDateTime dataHoraCadastro, LocalDateTime dataHoraAtualizacao) {
        this(codigo);
        this.dataHoraCadastro = dataHoraCadastro;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    public int getCodigo() { return this.codigo; }
    public LocalDateTime getDataHoraCadastro() { return dataHoraCadastro; }
    public LocalDateTime getDataHoraAtualizacao() { return dataHoraAtualizacao; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) { this.dataHoraCadastro = dataHoraCadastro; }
    public void setDataHoraAtualizacao(LocalDateTime dataHoraAtualizacao) { this.dataHoraAtualizacao = dataHoraAtualizacao; }
}