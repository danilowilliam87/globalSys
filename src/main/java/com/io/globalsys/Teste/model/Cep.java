package com.io.globalsys.Teste.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "cep")
public class Cep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_loja")
    private String codigoLoja;

    @Column(name = "faixa_inicio", unique = true)
    private int faixaInicio;

    @Column(name = "faixa_fim", unique = true)
    private int faixaFim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoLoja() {
        return codigoLoja;
    }

    public void setCodigoLoja(String codigoLoja) {
        this.codigoLoja = codigoLoja;
    }

    public int getFaixaInicio() {
        return faixaInicio;
    }

    public void setFaixaInicio(int faixaInicio) {
        this.faixaInicio = faixaInicio;
    }

    public int getFaixaFim() {
        return faixaFim;
    }

    public void setFaixaFim(int faixaFim) {
        this.faixaFim = faixaFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cep cep = (Cep) o;
        return faixaInicio == cep.faixaInicio &&
                faixaFim == cep.faixaFim &&
                Objects.equals(id, cep.id) &&
                Objects.equals(codigoLoja, cep.codigoLoja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigoLoja, faixaInicio, faixaFim);
    }

    @Override
    public String toString() {
        return "Cep{" +
                "id=" + id +
                ", codigoLoja=" + codigoLoja +
                ", faixaInicio=" + faixaInicio +
                ", faixaFim=" + faixaFim +
                '}';
    }
}
