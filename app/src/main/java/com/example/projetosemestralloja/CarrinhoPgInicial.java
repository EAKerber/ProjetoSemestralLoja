package com.example.projetosemestralloja;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoPgInicial extends AppCompatActivity {

    List<ItemDoCarrinho> produtos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_pg_inicial);

        //Array só para teste. Validar lista q não está funcionando
        Produto calca = new Produto(01, "calça jean", "calça", null, "10", null);


        ItemDoCarrinho item1 = new ItemDoCarrinho(01, calca);

        produtos.add(item1);

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


}