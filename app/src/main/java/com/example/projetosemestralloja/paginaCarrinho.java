package com.example.projetosemestralloja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class paginaCarrinho extends AppCompatActivity {

    List<ItemDoCarrinho> produtos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_carrinho);

        //Array só para teste. Validar lista q não está funcionando
        Produto calca = new Produto(01, "calça jean", "calça", null, "10", null);


        ItemDoCarrinho item1 = new ItemDoCarrinho(01, calca);
        ItemDoCarrinho item2 = new ItemDoCarrinho(02, calca);
        ItemDoCarrinho item3 = new ItemDoCarrinho(03, calca);


        addonlist(produtos,item1);
        addonlist(produtos,item2);
        addonlist(produtos,item3);


        Log.d("testCarrinho","02");
        //criarlista();
        Log.d("testCarrinho","03");
        RecyclerView rvProduto = findViewById(R.id.recyclercarrinho);

        LinearLayoutManager llhm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProduto.setLayoutManager(llhm);
        ItensDoCarinnhoAdapter adapter = new ItensDoCarinnhoAdapter(produtos, R.layout.layout_itens_carrinho) {
        };
        rvProduto.setAdapter(adapter);
    }

    public void addonlist(List<ItemDoCarrinho> lista, ItemDoCarrinho item){
        boolean taNalista = false;
        for(int i = 0; i < lista.size(); i++ ){
            if(lista.get(i).equals(item)){
                taNalista = true;
            }
        }
        if(!taNalista){
            lista.add(item);
        }
    }

    public void removeoflist(List<ItemDoCarrinho> lista, ItemDoCarrinho item){
        lista.remove(item);
    }
}