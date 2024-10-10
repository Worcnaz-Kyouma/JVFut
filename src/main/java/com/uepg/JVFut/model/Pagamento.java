package com.uepg.JVFut.model;

import jakarta.persistence.*;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codPagamento;

    @Column(nullable = false)
    private Short ano;
    @Column(nullable = false)
    private Byte mes;
    @Column(nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "cod_jogador", nullable = false)
    private Jogador jogador;

    public Integer getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(Integer codPagamento) {
        this.codPagamento = codPagamento;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public Byte getMes() {
        return mes;
    }

    public void setMes(Byte mes) {
        this.mes = mes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}