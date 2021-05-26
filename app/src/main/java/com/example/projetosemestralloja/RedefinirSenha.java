package com.example.projetosemestralloja;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class RedefinirSenha extends AppCompatActivity {
    private EditText nomeText;
    private EditText cpfText;
    private EditText emailText;
    private EditText novaSenhaText;
    private Button redefinirSenhaButton;
    private ImageView voltarButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();

    private List<Cliente> listCliente = new ArrayList<Cliente>();
    private ArrayAdapter<Cliente> arrayAdapterCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nomeText = (EditText)findViewById(R.id.Nome);
        cpfText = (EditText)findViewById(R.id.Cpf);
        emailText = (EditText)findViewById(R.id.Email);
        novaSenhaText = (EditText)findViewById(R.id.senha);
        redefinirSenhaButton = (Button)findViewById(R.id.RedefinirSenha);
        voltarButton = (ImageView)findViewById(R.id.Voltar);
        inicializarBanco();

        eventoDatabase();

        redefinirSenhaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("cliquei no botao");

               pesquisarBanco(nomeText.getText().toString(), cpfText.getText().toString(), emailText.getText().toString(), novaSenhaText.getText().toString());

            }
        });

        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                startActivity(new Intent(getBaseContext(),
                        LoginScreen.class));
                //m.disableDatabase();
            }
        });
    }
    private void inicializarBanco() {
        FirebaseApp.initializeApp(RedefinirSenha.this);
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
    private void pesquisarBanco(String nome,  String cpf, String email, String senha){
        int cpfValid = 0, nomeValid = 0, emailValid = 0, size;
        size = listCliente.size();
        Cliente c = new Cliente();
        if (nome.equals("") || cpf.equals("") || email.equals("")){
            alert("Todos os campos devem ser preenchidos");
            System.out.println("sem dados");

        }else {
            System.out.println("com dados");
            for (int i = 0; i < size; i++){
                cpfValid = 0;
                nomeValid = 0;
                emailValid = 0;
                if (nome.equals(listCliente.get(i).getNomeText())) {
                    nomeValid = 1;
                }
                if (cpf.equals(listCliente.get(i).getCpfText())) {
                    cpfValid = 1;
                }
                if (email.equals(listCliente.get(i).getEmailText())) {
                    emailValid = 1;
                }
                if (nomeValid == 1 && cpfValid == 1 && emailValid == 1) {
                    c.setCpfText(listCliente.get(i).getCpfText().toString());
                    c.setDataNascText(listCliente.get(i).getDataNascText().toString());
                    c.setNomeText(listCliente.get(i).getNomeText().toString());
                    c.setEmailText(listCliente.get(i).getEmailText().toString());
                    c.setUsuarioText(listCliente.get(i).getUsuarioText().toString());
                    c.setSenhaText(senha.toString());
                    databaseReference.child("Cliente").child(c.getCpfText()).setValue(c);
                    limparDados();
                    alert("Senha alterada com sucesso.");
                    break;
                }
            }
            if (cpfValid == 0 || nomeValid == 0 || emailValid == 0){
                alert("Dados não encontrados.");
            }
        }
    }
    private void alert(String s){
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
    }
    private void limparDados() {
        novaSenhaText.setText("");
        cpfText.setText("");
        emailText.setText("");
        nomeText.setText("");
    }
}