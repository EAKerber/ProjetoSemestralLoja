package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.adapter.ItensDoCarinnhoAdapter;

import static com.example.projetosemestralloja.ui.PaginaCarrinho.produtos;

public class FinalizarCompra extends MenuDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityTitle("Finalizar Compra");
        checkStartingItem();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v1 = layoutInflater.inflate(R.layout.activity_finalizar_compra, null, false);
        drawer.addView(v1, 0);

        bindFinal();

        RecyclerView rvProduto = findViewById(R.id.recyclerViewFinalizar);

        LinearLayoutManager llhm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvProduto.setLayoutManager(llhm);
        ItensDoCarinnhoAdapter adapter = new ItensDoCarinnhoAdapter(produtos, R.layout.layout_itens_carrinho){
        };
        rvProduto.setAdapter(adapter);
    }

    public void goToCarrinho(View v){
        startActivity(new Intent(v.getContext(), PaginaCarrinho.class) );
    }


}