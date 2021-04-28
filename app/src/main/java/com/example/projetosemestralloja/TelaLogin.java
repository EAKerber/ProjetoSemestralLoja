package com.example.projetosemestralloja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class TelaLogin extends AppCompatActivity {
    private EditText loginText;
    private EditText senhaText;
    private Button loginButton;
    private Button altSenhaButton;
    private Button convidadoButton;
    private Button criarContaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginText = (EditText)findViewById(R.id.Usuario);
        senhaText = (EditText)findViewById(R.id.Senha);
        loginButton = (Button)findViewById(R.id.Acessar);
        altSenhaButton = (Button)findViewById(R.id.Alt_senha);
        convidadoButton = (Button)findViewById(R.id.Convidado);
        criarContaButton = (Button)findViewById(R.id.CriarConta);
        //Alterar quando for realizado o link com o banco de dados para realizar acesso
        /*
        loginButton.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View v){
                String login = logintText.getText.toString(),
                        senha = senhaText.getText.toString();
                startActivity(new Intent(getBaseContext(), 'PROXIMA TELA'));
            }
        };*/
        //Alterar para a próxima tela
        /*convidadoButton.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(), 'PROXIMA TELA'));
            }
        };*/
        //Alterar para a próxima tela
        /*altSenhaButton.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(), 'PROXIMA TELA'));
            }
        };*/
        //Alterar para a próxima tela
        criarContaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(),
                        activity_criar_conta.class));
            }
        });

    }
}