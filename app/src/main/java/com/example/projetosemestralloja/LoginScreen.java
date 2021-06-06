package com.example.projetosemestralloja;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends AppCompatActivity {
    private EditText loginText;
    private EditText senhaText;
    private Button loginButton;
    private TextView altsenhaTextView;
    private TextView convidadoTextView;
    private TextView criarContaTextView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();

    private List<Cliente> listCliente = new ArrayList<Cliente>();
    private ArrayAdapter<Cliente> arrayAdapterCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        /*if (m.getValid() == 0) {
            m.setValid(1);
            m.onCreate();
        }*/
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginText = (EditText)findViewById(R.id.Usuario);
        senhaText = (EditText)findViewById(R.id.Senha);
        loginButton = (Button)findViewById(R.id.Acessar);
        altsenhaTextView = (TextView)findViewById(R.id.alt_senha);
        convidadoTextView = (TextView)findViewById(R.id.continuar_sem_login);
        criarContaTextView = (TextView) findViewById(R.id.criar_conta_click);
        inicializarBanco();
        eventoDatabase();
        //Alterar quando for realizado o link com o banco de dados para realizar acesso

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pesquisarBanco(loginText.getText().toString(), senhaText.getText().toString());

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
    private void inicializarBanco() {
        FirebaseApp.initializeApp(LoginScreen.this);
        firebaseDatabase = MyFirebaseApp.getFirebaseDatabaseInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
    }
    private void eventoDatabase() {
        databaseReference.child("Cliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCliente.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Cliente c = objSnapshot.getValue(Cliente.class);
                    listCliente.add(c);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void pesquisarBanco(String usuario, String senha){
        int usuarioValid = 0, senhaValid = 0, size;
        size = listCliente.size();
        Cliente c = new Cliente();
        System.out.println(size);
        if (usuario.equals("") || senha.equals("")){
            alert("Usuario/Senha devem ser preenchidos");
            System.out.println("sem dados");

        }else {
            System.out.println("com dados");
            for (int i = 0; i < size; i++){
                usuarioValid = 0;
                senhaValid = 0;
                System.out.println(listCliente.get(i).getUsuarioText());
                System.out.println(listCliente.get(i).getSenhaText());
                System.out.println(usuario);
                System.out.println(senha);
                if (usuario.equals(listCliente.get(i).getUsuarioText())) {
                    usuarioValid = 1;
                }
                if (senha.equals(listCliente.get(i).getSenhaText())) {
                    senhaValid = 1;
                }
                if (usuarioValid == 1 && senhaValid == 1) {
                    //startActivity(new Intent(getBaseContext(), 'PROXIMA TELA'));
                    limparDados();
                    alert("Login efetuado com sucesso.");
                  
                    startActivity(new Intent(getBaseContext(), MainActivity2.class));
                    break;
                }
            }
            if (usuarioValid == 0 || senhaValid == 0){
                alert("Dados não encontrados.");
            }
        }
    }

    private void limparDados() {
        senhaText.setText("");
        loginText.setText("");
    }

    private void alert(String s){
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
    }
}