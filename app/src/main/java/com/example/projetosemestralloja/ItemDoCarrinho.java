package com.example.projetosemestralloja;

public class ItemDoCarrinho {

    public long id;
    public String nome;
    public int qteselecionada;
    public double precoprd;
    public int precoitem;

    public ItemDoCarrinho(long id, String nome, int qteselecionada, int precoprd, int precoitem) {
        this.id = id;
        this.nome = nome;
        this.qteselecionada = qteselecionada;
        this.precoprd = precoprd;
        this.precoitem = precoitem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQteselecionada() {
        return qteselecionada;
    }

    public void setQteselecionada(int qteselecionada) {
        this.qteselecionada = qteselecionada;
    }

    public double getPrecoprd() {
        return precoprd;
    }

    public void setPrecoprd(int precoprd) {
        this.precoprd = precoprd;
    }

    public double getPrecoitem() {
        return precoitem;
    }

    public void setPrecoitem(int precoitem) {
        this.precoitem = precoitem;
    }
}
