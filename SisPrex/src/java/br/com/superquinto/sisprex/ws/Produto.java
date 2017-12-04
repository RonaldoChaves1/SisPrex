/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.superquinto.sisprex.ws;

/**
 *
 * @author Ronaldo Chaves
 */
public class Produto {
    private int id;
    private String codigo;
    private String ean;
    private String descricao;
    private String resumida;
    private double valor;

    public Produto() {
    }

    public Produto(int id, String codigo, String ean, String descricao, String resumida, double valor) {
        this.id = id;
        this.codigo = codigo;
        this.ean = ean;
        this.descricao = descricao;
        this.resumida = resumida;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResumida() {
        return resumida;
    }

    public void setResumida(String resumida) {
        this.resumida = resumida;
    }
    
}
