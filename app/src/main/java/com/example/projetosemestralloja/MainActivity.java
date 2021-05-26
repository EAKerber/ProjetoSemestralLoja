package com.example.projetosemestralloja;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.adapter.IMButtonAdapter;

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