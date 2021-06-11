package com.example.projetosemestralloja.model;

public class ItemDoCarrinho {

    public long id;
    public Produto produto;
    public int qteselecionada;
    public double precototal;
    public String precototalString;
    public String qteselecionadaString;
    public double valor;


    public ItemDoCarrinho(long id, Produto produto) {
        this.id = id;
        this.produto = produto;
        this.qteselecionada = 1;
        this.valor = Double.parseDouble(produto.valor.replace(produto.valor.substring(0,2), "").replace(",","."));
        this.precototal = (valor * qteselecionada);
        this.precototalString = "";
        this.qteselecionadaString = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQteselecionada() {
        return qteselecionada;
    }

    public void setQteselecionada(int qteselecionada) {
        this.qteselecionada = qteselecionada;
    }

    public double getPrecototal() {return valor *qteselecionada; }

    public void setPrecototal(int precototal) {
        this.precototal = precototal;
    }

    public String getPrecototalString() {return precototalString + "R$ "+ String.format("%.2f", getPrecototal()); }

    public void setPrecototalString(String precototalString) {this.precototalString = precototalString; }

    public String getQteselecionadaString() {return qteselecionadaString + (int)getQteselecionada(); }

    public void setQteselecionadaString(String qteselecionadaString) {this.qteselecionadaString = qteselecionadaString; }

}



