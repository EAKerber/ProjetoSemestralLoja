package com.example.projetosemestralloja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class activity_criar_conta extends AppCompatActivity {
    private EditText nomeText;
    private EditText senhaText;
    private EditText cpfText;
    private EditText dataNascText;
    private EditText emailText;
    private EditText usuarioText;
    private Button criarContaButton;
    private Button voltarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nomeText = (EditText)findViewById(R.id.Nome);
        senhaText = (EditText)findViewById(R.id.Senha1);
        cpfText = (EditText)findViewById(R.id.Cpf);
        dataNascText = (EditText)findViewById(R.id.DataNasc);
        emailText = (EditText)findViewById(R.id.Email);
        usuarioText = (EditText)findViewById(R.id.Usuario1);
        criarContaButton = (Button)findViewById(R.id.Criarconta);
        voltarButton = (Button)findViewById(R.id.Voltar);
        /*Alterar para a tela correta com os devidos alertas.
        criarContaButton.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View v){
                String usuario = usuarioText.getText.toString(),
                       senha = senhaText.getText.toString(),
                       cpf = cpfText.getText().toString(),
                       dataNasc = dataNascText.getText().toString()
                       email = emailText.getText().toString(),
                       nome = nomeText.getText().toString();
                startActivity(new Intent(getBaseContext(), 'PROXIMA TELA'));
            }
        };*/
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(),
                        TelaLogin.class));
            }
        });
    }
}