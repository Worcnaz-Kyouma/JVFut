package com.uepg.JVFut.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codJogador;

    @Column(length = 60)
    private String nome;
    @Column(length = 60)
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dataNasc;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("jogador")
    private List<Pagamento> pagamentos;

    public Integer getCodJogador() {
        return codJogador;
    }

    public void setCodJogador(Integer codJogador) {
        this.codJogador = codJogador;
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

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDatanasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }
}
