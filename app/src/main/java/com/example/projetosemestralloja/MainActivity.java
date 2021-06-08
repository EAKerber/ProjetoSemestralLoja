package com.example.projetosemestralloja;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.adapter.IMButtonAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MenuDrawerActivity {
    List<PaginaInicialIMButton> IMButtonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("mainactivity", "01");
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        Log.d("mainactivity", "02");
        View v3 = layoutInflater.inflate(R.layout.activity_main, null, false);
        Log.d("mainactivity", "03");
        drawer.addView(v3, 3);
        Log.d("mainactivity", "04");


        createIMBList();
        createButtons();

    }


    public void addOnIMBList(int imagem, String titulo, Class intent){
        PaginaInicialIMButton imb = new PaginaInicialIMButton(imagem, intent, titulo, this);
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

        addOnIMBList(R.drawable.masculino, "Masculino", ProdutoRecycler.class);
        addOnIMBList(R.drawable.feminino, "Feminino", ProdutoRecycler.class);
        addOnIMBList(R.drawable.infantil, "Infantil", ProdutoRecycler.class);
        addOnIMBList(R.drawable.calca, "Calças", ProdutoRecycler.class);
        addOnIMBList(R.drawable.pijama, "Pijama", ProdutoRecycler.class);
        addOnIMBList(R.drawable.jaqueta, "Jaqueta", ProdutoRecycler.class);
        addOnIMBList(R.drawable.bermuda, "Bermuda", ProdutoRecycler.class);
        addOnIMBList(R.drawable.camisa, "Camisa", ProdutoRecycler.class);

    }

}