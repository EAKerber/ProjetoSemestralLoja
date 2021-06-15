package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.adapter.ItensDoCarinnhoAdapter;
import com.example.projetosemestralloja.interfaces.BancoContrato;
import com.example.projetosemestralloja.model.Cliente;
import com.example.projetosemestralloja.model.ItemDoCarrinho;
import com.example.projetosemestralloja.model.Produto;
import com.example.projetosemestralloja.model.ProdutoCarrinho;
import com.example.projetosemestralloja.presenter.BancoPresenter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PaginaCarrinho extends MenuDrawerActivity implements BancoContrato{

    static List<ItemDoCarrinho> produtos = new ArrayList<>();
    static boolean vaiFinalizarCompra = false;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();
    BancoContrato.presenter bp;
    int size;
    private List<ProdutoCarrinho> listProduto = new ArrayList<ProdutoCarrinho>();
    private List<Cliente> listCliente = new ArrayList<Cliente>();
    String cpf;
    String cep;
    String endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        bp.inicializarBancoConta();
        System.out.println("Começo evento database");
        bp.eventoDatabase2(null);
        System.out.println("Fim evento database");
        setActivityTitle("Carrinho");
        checkStartingItem();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v2 = layoutInflater.inflate(R.layout.activity_pagina_carrinho, null, false);
        drawer.addView(v2, 0);

        bindCarrinho();

        RecyclerView rvProduto = findViewById(R.id.recyclercarrinho);

        LinearLayoutManager llhm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProduto.setLayoutManager(llhm);

        System.out.println("Tamanho lista produtos " + produtos.size());
        System.out.println("Começo itens do carrinho");
        ItensDoCarinnhoAdapter adapter = new ItensDoCarinnhoAdapter(produtos, R.layout.layout_itens_carrinho) {
        };
        System.out.println("Fim itens do carrinho");
        rvProduto.setAdapter(adapter);

    }

    public void createItemDoCarrinho(Produto produto, View v) {
        int i = 0;
        boolean isInList = false;
        ItemDoCarrinho item = null;

        if (!(produtos.isEmpty())) {
            Log.d("carrinhoAddItem", "04");

            for (ItemDoCarrinho obj : produtos) {
                Log.d("carrinhoAddItem", "05");
                i++;
                obj.setId(i);
                if (obj.produto.id == produto.id) {
                    Log.d("carrinhoAddItem", "01");
                    isInList = true;
                }
            }
            i++;
            if (!isInList) {
                Log.d("carrinhoAddItem", "06");
                item = new ItemDoCarrinho(i, produto);
            }
        } else {
            item = new ItemDoCarrinho(i, produto);
            Log.d("carrinhoAddItem", "02 " + produto.getValor() + " id " + produto.getId());
        }

        if (item != null) {
            produtos.add(item);

            Log.d("carrinhoAddItem", "03");
            if (v != null) {
                Toast.makeText(v.getContext(), "Adicionado ao Carrinho", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (v != null) {
                Toast.makeText(v.getContext(), "Produto já está no carrinho", Toast.LENGTH_SHORT).show();
            }
        }
        Log.d("carrinhoAddItem", "32");

    }


    public void addonlist(List<ItemDoCarrinho> lista, ItemDoCarrinho item) {
        boolean taNalista = false;
        int i = 0;
        for (ItemDoCarrinho itemDoCarrinho : lista) {
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


    private void alert(String s) {
        Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
    }


    public void goToFinalizarCompra(View v) {
        if (produtos.size() > 0) {
            if (cpf != null) {
                Intent intent = new Intent(v.getContext(), FinalizarCompra.class);
                intent.putExtra("CEP", cep);
                intent.putExtra("Endereco", endereco);
                startActivity(intent);
            } else {
                Toast.makeText(v.getContext(), "Faça login para poder Finalizar Compra", Toast.LENGTH_SHORT).show();
                vaiFinalizarCompra = true;
            }
        }else{
            Toast.makeText(v.getContext(), "Não há itens para finalizar compra", Toast.LENGTH_SHORT).show();
        }
    }

}

