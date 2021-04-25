package com.example.projetosemestralloja;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<PaginaInicialIMButton> IMButtonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w("addONIMBList","tentando registro");

        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");
        addOnIMBList(R.drawable.button_place_holder, "titulo");



        Log.w("addONIMBList","registro adicionado");

        createButtons();

    }

    public void createButtons(){

        LinearLayout ll = findViewById(R.id.LinearLayoutMainSV);
        for (PaginaInicialIMButton obj1 : IMButtonList) {
            ImageButton ib = new ImageButton(this);
            ib.setImageResource(obj1.getImage());
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                    startActivity(intent);
                }
            });
            ll.addView(ib);;
        }

    }

    public void addOnIMBList(int imagem, String titulo){
        PaginaInicialIMButton imb = new PaginaInicialIMButton(imagem, titulo);
        IMButtonList.add(imb);
    }

}