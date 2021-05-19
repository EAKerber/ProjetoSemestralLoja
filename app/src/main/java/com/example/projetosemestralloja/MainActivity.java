package com.example.projetosemestralloja;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText loginText;
    private EditText senhaText;
    private Button loginButton;
    private Button altSenhaButton;
    private Button convidadoButton;
    private Button criarContaButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();

    private List<Cliente> listCliente = new ArrayList<Cliente>();
    private ArrayAdapter<Cliente> arrayAdapterCliente;

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

        addOnIMBList(R.drawable.masculino, "Masculino", SplashScreen.class);
        addOnIMBList(R.drawable.feminino, "Feminino", SplashScreen.class);
        addOnIMBList(R.drawable.infantil, "Infantil", SplashScreen.class);
        addOnIMBList(R.drawable.calca, "Calças", SplashScreen.class);
        addOnIMBList(R.drawable.pijama, "Pijama", SplashScreen.class);
        addOnIMBList(R.drawable.jaqueta, "Jaqueta", SplashScreen.class);
        addOnIMBList(R.drawable.bermuda, "Bermuda", SplashScreen.class);
        addOnIMBList(R.drawable.camisa, "Camisa", SplashScreen.class);

    }

}