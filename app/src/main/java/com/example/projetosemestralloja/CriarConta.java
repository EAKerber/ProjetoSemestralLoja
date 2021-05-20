package com.example.projetosemestralloja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                    if (usuarioText.getText().toString().equals("") || senhaText.getText().toString().equals("") || cpfText.getText().toString().equals("")
                     || dataNascText.getText().toString().equals("") || emailText.getText().toString().equals("") || nomeText.getText().toString().equals(""))
                    {
                        alert("Todos os dados devem ser preenchidos.");
                    }else if (usuarioText.getText().toString().indexOf(" ") >= 0){
                        alert("Favor informar 1 usuario sem espaços em branco.");
                    }else if (ValidaCpf.isCPF(cpfText.getText().toString()) == false) {
                        alert("Favor informar 1 CPF válido.");
                    }else if (ValidaData.isData(dataNascText.getText().toString()) == false) {
                        alert("Favor informar 1 data de nascimento válida.");
                    }else if (ValidaEmail.isEmail(emailText.getText().toString())== false){
                        alert("Favor informar 1 email válido.");
                    }
                    else{
                        Cliente c = new Cliente();
                        c.setUsuarioText(usuarioText.getText().toString());
                        c.setSenhaText(senhaText.getText().toString());
                        c.setCpfText(cpfText.getText().toString());
                        c.setDataNascText(dataNascText.getText().toString());
                        c.setEmailText(emailText.getText().toString());
                        c.setNomeText(nomeText.getText().toString());
                        databaseReference.child("Cliente").child(c.getCpfText()).setValue(c);
                        limparDados();
                        alert("Conta criada com sucesso.");
                    }

            }
        });
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(),
                        LoginScreen.class));
            }
        });
    }

    private void inicializarBanco() {
        FirebaseApp.initializeApp(CriarConta.this);
        firebaseDatabase = MyFirebaseApp.getFirebaseDatabaseInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
    }

    private void limparDados() {
        usuarioText.setText("");
        senhaText.setText("");
        cpfText.setText("");
        dataNascText.setText("");
        emailText.setText("");
        nomeText.setText("");
    }
    private void alert(String s){
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
    }
}