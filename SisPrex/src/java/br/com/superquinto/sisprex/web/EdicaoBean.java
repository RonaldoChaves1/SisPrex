/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.superquinto.sisprex.web;
import java.sql.Timestamp;
import javax.annotation.ManagedBean;

@ManagedBean
/**
 *
 * @author Ronaldo Chaves
 */
public class EdicaoBean {
    private int id;
    private int idProduto;
    private String campo;
    private String valorAnterior;
    private String valorAtual;
    private int sugeridoPor;
    private Timestamp timeStampSugestao;
    private boolean aceito;
    private int avaliadoPor;
    private Timestamp timestampAlteracao;

    public EdicaoBean() {
    }

    public EdicaoBean(int id,int idProduto, String campo, String valorAnterior, String valorAtual, int sugeridoPor, Timestamp timeStampSugestao, boolean aceito, int avaliadoPor, Timestamp timestampAlteracao) {
        this.id = id;
        this.idProduto = idProduto;
        this.campo = campo;
        this.valorAnterior = valorAnterior;
        this.valorAtual = valorAtual;
        this.sugeridoPor = sugeridoPor;
        this.timeStampSugestao = timeStampSugestao;
        this.aceito = aceito;
        this.avaliadoPor = avaliadoPor;
        this.timestampAlteracao = timestampAlteracao;
    }

    public Timestamp getTimestampAlteracao() {
        return timestampAlteracao;
    }

    public void setTimestampAlteracao(Timestamp timestampAlteracao) {
        this.timestampAlteracao = timestampAlteracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getidProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }
    
    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(String valorAtual) {
        this.valorAtual = valorAtual;
    }
    
    public int getSugeridoPor() {
        return sugeridoPor;
    }

    public void setSugeridoPor(int sugeridoPor) {
        this.sugeridoPor = sugeridoPor;
    }

    public Timestamp getTimeStampSugestao() {
        return timeStampSugestao;
    }

    public void setTimeStampSugestao(Timestamp timeStampSugestao) {
        this.timeStampSugestao = timeStampSugestao;
    }

    public int getAvaliadoPor() {
        return avaliadoPor;
    }

    public void setAvaliadoPor(int avaliadoPor) {
        this.avaliadoPor = avaliadoPor;
    }
    
}
