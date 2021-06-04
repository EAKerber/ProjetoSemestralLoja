package com.example.projetosemestralloja;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.adapter.ItensDoCarinnhoAdapter;

import java.util.ArrayList;
import java.util.List;

public class PaginaCarrinho extends AppCompatActivity {

    static List<ItemDoCarrinho> produtos = new ArrayList<>();
    static boolean jaadicionado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_carrinho);


        addAllOnList();

        RecyclerView rvProduto = findViewById(R.id.recyclercarrinho);

        LinearLayoutManager llhm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProduto.setLayoutManager(llhm);
        ItensDoCarinnhoAdapter adapter = new ItensDoCarinnhoAdapter(produtos, R.layout.layout_itens_carrinho) {
        };
        rvProduto.setAdapter(adapter);
    }
    public void addAllOnList(){
        if(!jaadicionado){
            //Array só para teste. Validar lista q não está funcionando
            Produto calca = new Produto(01, "calça jean", "calça", null, "10", "https://images-ext-2.discordapp.net/external/yFG6nihLOVq8TW3lmQtO4beyydOC-EXw7odwQsSomGE/https/static.dafiti.com.br/p/LEVOK-Cal%25C3%25A7a-Masculina-Jeans-Lavado-1085-7956-5604006-1-zoom.jpg?width=326&height=472");


            ItemDoCarrinho item1 = new ItemDoCarrinho(01, calca);
            ItemDoCarrinho item2 = new ItemDoCarrinho(02, calca);
            ItemDoCarrinho item3 = new ItemDoCarrinho(03, calca);
            addonlist(produtos,item1);
            addonlist(produtos,item2);
            addonlist(produtos,item3);
        }
        jaadicionado = true;
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