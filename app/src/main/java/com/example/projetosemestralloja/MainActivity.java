package com.example.projetosemestralloja;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<PaginaInicialIMButton> IMButtonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        createIMBList();
        createButtons();

    }


    public void addOnIMBList(int imagem, String titulo, Class intent){
        PaginaInicialIMButton imb = new PaginaInicialIMButton(imagem, intent, titulo);
        IMButtonList.add(imb);
    }

    public void createButtons(){
        RecyclerView rvIMB = findViewById(R.id.IMB_Recycler);

        LinearLayoutManager llhm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvIMB.setLayoutManager(llhm);
        IMButtonAdapter adapter = new IMButtonAdapter(IMButtonList, R.layout.buttonlayout){
        };
        rvIMB.setAdapter(adapter);
    }

    public void createIMBList(){

        addOnIMBList(R.drawable.button_place_holder, "Masculino", ProdutoRecycler.class);
        addOnIMBList(R.drawable.button_place_holder, "Feminino", ProdutoRecycler.class);
        addOnIMBList(R.drawable.button_place_holder, "Infantil", ProdutoRecycler.class);
        addOnIMBList(R.drawable.button_place_holder, "Calças", ProdutoRecycler.class);
        addOnIMBList(R.drawable.button_place_holder, "Pijama", ProdutoRecycler.class);
        addOnIMBList(R.drawable.button_place_holder, "Jaqueta", ProdutoRecycler.class);
        addOnIMBList(R.drawable.button_place_holder, "Bermuda", ProdutoRecycler.class);
        addOnIMBList(R.drawable.button_place_holder, "Camisa", ProdutoRecycler.class);

    }

}