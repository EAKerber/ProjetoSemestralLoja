package com.example.projetosemestralloja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CriarConta extends AppCompatActivity {
    private EditText nomeText;
    private EditText senhaText;
    private EditText cpfText;
    private EditText dataNascText;
    private EditText emailText;
    private EditText usuarioText;
    private Button criarContaButton;
    private Button voltarButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        //getSupportActionBar().hide();

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nomeText = (EditText)findViewById(R.id.Nome);
        senhaText = (EditText)findViewById(R.id.Senha1);
        cpfText = (EditText)findViewById(R.id.Cpf);
        dataNascText = (EditText)findViewById(R.id.DataNasc);
        emailText = (EditText)findViewById(R.id.Email);
        usuarioText = (EditText)findViewById(R.id.Usuario1);
        criarContaButton = (Button)findViewById(R.id.Criarconta);
        voltarButton = (Button)findViewById(R.id.Voltar);

        inicializarBanco();

        //Alterar para a tela correta com os devidos alertas.
        criarContaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    int v_valid = 1;
                    Cliente c = new Cliente();
                    c.setUsuarioText(usuarioText.getText().toString());
                    c.setSenhaText(senhaText.getText().toString());
                    c.setCpfText(cpfText.getText().toString());
                    c.setDataNascText(dataNascText.getText().toString());
                    c.setEmailText(emailText.getText().toString());
                    c.setNomeText(nomeText.getText().toString());
                    databaseReference.child("Cliente").child(c.getCpfText()).setValue(c);
                    limparDados();
                    //fazer validações dos dados de entradas
            }
        });
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(),
                        MainActivity.class));
            }
        });
    }

    private void inicializarBanco() {
        FirebaseApp.initializeApp(CriarConta.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void limparDados() {
        usuarioText.setText("");
        senhaText.setText("");
        cpfText.setText("");
        dataNascText.setText("");
        emailText.setText("");
        nomeText.setText("");
    }
}