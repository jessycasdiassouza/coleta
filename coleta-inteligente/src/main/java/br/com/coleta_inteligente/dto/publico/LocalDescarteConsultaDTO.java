package br.com.coleta_inteligente.dto.publico;

import java.util.List;

public class LocalDescarteConsultaDTO {

    private Long id;

    private String titulo;

    private String descricao;

    private String logradouro;

    private Long numero;

    private String complemento;

    private String referencia;

    private String bairro;

    private String cidade;

    private String uf;

    private List<String> tiposResiduo;

    public LocalDescarteConsultaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<String> getTiposResiduo() {
        return tiposResiduo;
    }

    public void setTiposResiduo(List<String> tiposResiduo) {
        this.tiposResiduo = tiposResiduo;
    }
}