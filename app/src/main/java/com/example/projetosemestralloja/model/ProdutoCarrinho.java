package com.example.projetosemestralloja.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

public class ProdutoCarrinho{
    public int id;
    public String title;
    public String descricao;
    public String valor;
    public String url;
    public String cpf;
    public String uuid;

    public ProdutoCarrinho(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) { this.descricao = descricao; }


    public String getValor() {
        return valor;
    }

    public void setValor(String valor) { this.valor = valor; }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) { this.url = url; }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
