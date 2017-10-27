package com.example.worlo.cep_mvp.main.model;

/**
 * Created by worlo on 03/06/2017.
 */

public class PesquisaEndereco {

    private String uf;
    private String cidade;
    private String logradouro;

    public PesquisaEndereco(String uf, String cidade, String logradouro) {
        this.uf = uf;
        this.cidade = cidade;
        this.logradouro = logradouro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
}
