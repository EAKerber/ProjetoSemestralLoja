package com.example.projetosemestralloja.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

public class Produto implements Parcelable {
    public int id;
    public String title;
    public String descricao;
    public JSONArray categorias;
    public String valor;
    public String url;

    public Produto(int id, String title, String descricao, JSONArray categorias, String valor, String url){
        this.id = id;
        this.title = title;
        this.descricao = descricao;
        this.categorias = categorias;
        this.valor = valor;
        this.url = url;
    }

    protected Produto(Parcel in) {
        id = in.readInt();
        title = in.readString();
        descricao = in.readString();
        valor = in.readString();
        url = in.readString();
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

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

    public JSONArray getCategorias() {
        return categorias;
    }

    public void setCategorias(JSONArray categorias) { this.categorias = categorias; }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) { this.valor = valor; }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) { this.url = url; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(descricao);
        dest.writeString(valor);
        dest.writeString(url);
    }
}
