package com.example.projetosemestralloja.ui;

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

import com.example.projetosemestralloja.interfaces.BancoContrato;
import com.example.projetosemestralloja.model.Cliente;
import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.presenter.BancoPresenter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RedefinirSenha extends AppCompatActivity implements BancoContrato{
    private EditText nomeText;
    private EditText cpfText;
    private EditText emailText;
    private EditText novaSenhaText;
    private Button redefinirSenhaButton;
    private ImageView voltarButton;

    BancoContrato.presenter bp;
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
        bp.inicializarBancoConta();

        bp.eventoDatabase();

        redefinirSenhaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("cliquei no botao");

               bp.pesquisarBanco2(nomeText.getText().toString(), cpfText.getText().toString(),
                                 emailText.getText().toString(), novaSenhaText.getText().toString());

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

    private void limparDados() {
        novaSenhaText.setText("");
        cpfText.setText("");
        emailText.setText("");
        nomeText.setText("");
    }
}