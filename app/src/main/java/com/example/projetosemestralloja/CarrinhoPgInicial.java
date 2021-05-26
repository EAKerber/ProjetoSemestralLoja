package com.example.projetosemestralloja;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.adapter.ItensDoCarinnhoAdapter;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoPgInicial extends AppCompatActivity {

    List<Produto> produtos = new ArrayList<>();

    //Array só para teste. Validar lista q não está funcionando

    Produto calca = new Produto (01,"calça jean", "calça",null,"10",null);
    Produto calca1 = new Produto (01,"calça jean", "calça",null,"10",null);
    Produto calca2 = new Produto (01,"calça jean", "calça",null,"10",null);
    Produto calca3 = new Produto (01,"calça jean", "calça",null,"10",null);
    Produto calca4 = new Produto (01,"calça jean", "calça",null,"10",null);
    Produto calca5 = new Produto (01,"calça jean", "calça",null,"10",null);
    Produto[] produto2 = {calca,calca1,calca2,calca3,calca4,calca5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho_pg_inicial);

        RecyclerView rvProduto = findViewById(R.id.recyclercarrinho);

        LinearLayoutManager llhm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rvProduto.setLayoutManager(llhm);
        ItensDoCarinnhoAdapter adapter = new ItensDoCarinnhoAdapter(produto2, R.layout.layout_itens_carrinho){
        };
        rvProduto.setAdapter(adapter);
    }



}