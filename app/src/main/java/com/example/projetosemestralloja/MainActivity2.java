package com.example.projetosemestralloja;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.adapter.IMButtonAdapter;
import com.example.projetosemestralloja.presenter.MainActivityPresenter;

import java.util.List;

public class MainActivity2 extends MenuDrawerActivity implements MainActivityContrato.View{

    private MainActivityContrato.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v = layoutInflater.inflate(R.layout.activity_main, null, false);
        drawer.addView(v, 2);

        presenter = new MainActivityPresenter(this);
        presenter.createIMBList();
    }

    @Override
    public Context getContexto(){
        return this;
    }

    public void createButtons(List<PaginaInicialIMButton> IMButtonList){
        RecyclerView rvIMB = findViewById(R.id.IMB_Recycler);

        LinearLayoutManager llhm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvIMB.setLayoutManager(llhm);
        IMButtonAdapter adapter = new IMButtonAdapter(IMButtonList, R.layout.buttonlayout){
        };
        rvIMB.setAdapter(adapter);
    }

}