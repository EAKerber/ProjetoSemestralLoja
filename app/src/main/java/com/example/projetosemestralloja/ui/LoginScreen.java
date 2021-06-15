package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.interfaces.BancoContrato;
import com.example.projetosemestralloja.model.Cliente;
import com.example.projetosemestralloja.model.ProdutoCarrinho;
import com.example.projetosemestralloja.presenter.BancoPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends AppCompatActivity implements BancoContrato{
    private EditText loginText;
    private EditText senhaText;
    private Button loginButton;
    private TextView altsenhaTextView;
    private TextView convidadoTextView;
    private TextView criarContaTextView;
    private static String cpfLogado;
    private static String emailLogado;

    BancoContrato.presenter bp;

    MyFirebaseApp m = new MyFirebaseApp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginText = (EditText)findViewById(R.id.Usuario);
        senhaText = (EditText)findViewById(R.id.Senha);
        loginButton = (Button)findViewById(R.id.Acessar);
        altsenhaTextView = (TextView)findViewById(R.id.alt_senha);
        convidadoTextView = (TextView)findViewById(R.id.continuar_sem_login);
        criarContaTextView = (TextView) findViewById(R.id.criar_conta_click);
        bp.inicializarBancoConta();
        bp.eventoDatabase();
        //Alterar quando for realizado o link com o banco de dados para realizar acesso

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                bp.pesquisarBanco(loginText.getText().toString(), senhaText.getText().toString(), v);

            }
        });
        //Alterar para a próxima tela
        convidadoTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(), MainActivity2.class));
            }
        });


        altsenhaTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(),
                        RedefinirSenha.class));
            }
        });

        criarContaTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(),
                        CriarConta.class));
            }
        });

    }

    public static String retornaCpf(){
        return cpfLogado;
    }
    public static String retornaEmail(){
        return emailLogado;
    }
    public static void setCpfNull() {
        cpfLogado = null;
    }
    public static void setEmailNull() {
        emailLogado = null;
    }

}