package br.com.superquinto.sisprex;

import java.sql.Timestamp;

public class Edicao {
	private int id;
    private int idProduto;
    private String campo;
    private String valorAnterior;
    private String valorAtual;
    private int sugeridoPor;
    private Timestamp timeStampSugestao;
    private int aceitoPor;
    private Timestamp timestampAceito;

    public Edicao() {
    }

    public Edicao(int id,int idProduto, String campo, String valorAnterior, String valorAtual, int sugeridoPor, Timestamp timeStampSugestao, int aceitoPor, Timestamp timestampAceito) {
        this.id = id;
        this.idProduto = idProduto;
        this.campo = campo;
        this.valorAnterior = valorAnterior;
        this.valorAtual = valorAtual;
        this.sugeridoPor = sugeridoPor;
        this.timeStampSugestao = timeStampSugestao;
        this.aceitoPor = aceitoPor;
        this.timestampAceito = timestampAceito;
    }

    public Timestamp getTimestampAceito() {
        return timestampAceito;
    }

    public void setTimestampAceito(Timestamp timestampAceito) {
        this.timestampAceito = timestampAceito;
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

    public int getAceitoPor() {
        return aceitoPor;
    }

    public void setAceitoPor(int aceitoPor) {
        this.aceitoPor = aceitoPor;
    }
}
