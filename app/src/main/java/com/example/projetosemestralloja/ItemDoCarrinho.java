package com.example.projetosemestralloja;

public class ItemDoCarrinho {

    public long id;
    public Produto produto;
    public int qteselecionada;
    public double precototal;
    public String precototalString;
    public String qteselecionadaString;


    public ItemDoCarrinho(long id, Produto produto) {
        this.id = id;
        this.produto = produto;
        this.qteselecionada = 1;
        this.precototal = (5 * qteselecionada);
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

    public double getPrecototal() {return 5*qteselecionada; }

    public void setPrecototal(int precototal) {
        this.precototal = precototal;
    }

    public String getPrecototalString() {return precototalString + getPrecototal(); }

    public void setPrecototalString(String precototalString) {this.precototalString = precototalString; }

    public String getQteselecionadaString() {return qteselecionadaString + getQteselecionada(); }

    public void setQteselecionadaString(String qteselecionadaString) {this.qteselecionadaString = qteselecionadaString; }

}



