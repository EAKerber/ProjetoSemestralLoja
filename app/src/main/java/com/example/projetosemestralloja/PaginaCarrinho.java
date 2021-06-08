package com.example.projetosemestralloja;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.adapter.ItensDoCarinnhoAdapter;

import java.util.ArrayList;
import java.util.List;

public class PaginaCarrinho extends MenuDrawerActivity {

    static List<ItemDoCarrinho> produtos = new ArrayList<>();
    static boolean jaadicionado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v2 = layoutInflater.inflate(R.layout.activity_pagina_carrinho, null, false);
        drawer.addView(v2, 0);

        RecyclerView rvProduto = findViewById(R.id.recyclercarrinho);

        LinearLayoutManager llhm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProduto.setLayoutManager(llhm);
        ItensDoCarinnhoAdapter adapter = new ItensDoCarinnhoAdapter(produtos, R.layout.layout_itens_carrinho) {
        };
        rvProduto.setAdapter(adapter);
    }

    public void createItemDoCarrinho(Produto produto) {
        int i = 0;
        boolean isInList = false;
        ItemDoCarrinho item = null;
        if (produtos.size() > 0) {
            Log.d("carrinhoAddItem", "04");
            for (ItemDoCarrinho obj : produtos) {
                Log.d("carrinhoAddItem", "05");
                if (obj.produto.equals(produto)) {
                    Log.d("carrinhoAddItem", "01");
                    isInList = true;
                }
                i++;
            }
            if(!isInList){
                Log.d("carrinhoAddItem", "06");
                item = new ItemDoCarrinho(i, produto);
            }
        }else {
            item = new ItemDoCarrinho(i, produto);
            Log.d("carrinhoAddItem", "02");
        }

        if (item != null){
            addonlist(produtos, item);
            Log.d("carrinhoAddItem", "03");
        }
    }


    public void addonlist(List<ItemDoCarrinho> lista, ItemDoCarrinho item) {
        boolean taNalista = false;
        int i = 0;
        for (ItemDoCarrinho itemDoCarrinho:lista) {
            if (lista.get(i).equals(item)) {
                taNalista = true;
            }
        }
        if (!taNalista) {
            lista.add(item);
        }
    }

    public void removeoflist(List<ItemDoCarrinho> lista, ItemDoCarrinho item) {
        lista.remove(item);
    }
}