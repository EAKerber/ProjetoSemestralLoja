package com.example.projetosemestralloja;


import org.json.JSONArray;
import org.w3c.dom.ls.LSInput;

import java.lang.reflect.Array;
import java.util.List;

public class Produto {
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
}
