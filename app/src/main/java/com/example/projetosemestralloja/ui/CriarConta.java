package com.example.projetosemestralloja.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetosemestralloja.model.Cliente;
import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.presenter.BancoPresenter;
import com.example.projetosemestralloja.validacao.ValidaCep;
import com.example.projetosemestralloja.validacao.ValidaCpf;
import com.example.projetosemestralloja.validacao.ValidaData;
import com.example.projetosemestralloja.validacao.ValidaEmail;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CriarConta extends AppCompatActivity {
    private ImageView iconVoltar;
    private EditText emailText;
    private EditText senhaText;
    private EditText nomeText;
    private EditText dataNascText;
    private EditText cpfText;
    private EditText cepText;
    private EditText cidadeText;
    private EditText bairroText;
    private EditText endeText;
    private EditText numendeText;
    private Button criarContaButton;

    BancoPresenter bp;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        iconVoltar = (ImageView) findViewById(R.id.iconVoltar);
        emailText = (EditText)findViewById(R.id.Email);
        senhaText = (EditText)findViewById(R.id.Senha1);
        nomeText = (EditText)findViewById(R.id.Nome);
        dataNascText = (EditText)findViewById(R.id.DataNasc);
        cpfText = (EditText)findViewById(R.id.Cpf);
        cepText = (EditText)findViewById(R.id.CEP);
        cidadeText = (EditText)findViewById(R.id.City);
        bairroText = (EditText)findViewById(R.id.Bairro);
        endeText = (EditText)findViewById(R.id.Ende);
        numendeText = (EditText)findViewById(R.id.numEnde);
        criarContaButton = (Button)findViewById(R.id.Criarconta);


        bp.inicializarBancoConta();

        //Alterar para a tela correta com os devidos alertas.
        criarContaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (emailText.getText().toString().equals("") || senhaText.getText().toString().equals("") || nomeText.getText().toString().equals("")
                        || dataNascText.getText().toString().equals("") || cpfText.getText().toString().equals("") || cepText.getText().toString().equals("")
                        || cidadeText.getText().toString().equals("") || bairroText.getText().toString().equals("") || endeText.getText().toString().equals("")
                        || numendeText.getText().toString().equals(""))
                {
                    alert("Todos os dados devem ser preenchidos.");
                }else if (ValidaEmail.isEmail(emailText.getText().toString())== false){
                    alert("Favor informar 1 email válido.");
                    findViewById(R.id.Email).requestFocus();//"#FF4500"
                }else if (ValidaData.isData(dataNascText.getText().toString()) == false) {
                    alert("Favor informar uma data de nascimento válida.");
                    findViewById(R.id.DataNasc).requestFocus();
                }else if (ValidaCpf.isCPF(cpfText.getText().toString()) == false) {
                    alert("Favor informar 1 CPF válido.");
                    findViewById(R.id.Cpf).requestFocus();
                }else if (ValidaCep.isCep(cepText.getText().toString())== false){
                    alert("Favor informar 1 CEP válido.");
                    findViewById(R.id.CEP).requestFocus();
                }
                else{
                    Cliente c = new Cliente();
                    c.setEmailText(emailText.getText().toString());
                    c.setSenhaText(senhaText.getText().toString());
                    c.setNomeText(nomeText.getText().toString());
                    c.setDataNascText(dataNascText.getText().toString());
                    c.setCpfText(cpfText.getText().toString());
                    c.setCepText(cepText.getText().toString());
                    c.setCidadeText(cidadeText.getText().toString());
                    c.setBairroText(bairroText.getText().toString());
                    c.setEndeText(endeText.getText().toString());
                    c.setNumendText(numendeText.getText().toString());
                    databaseReference.child("Cliente").child(c.getCpfText()).setValue(c);
                    bp.limparDados();
                    alert("Conta criada com sucesso.");
                }

            }
        });

        //INICIO DA CRIAÇÃO DE MASCARAS

        //mascara CPF (Problema com validação do CPF)
        //SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        //MaskTextWatcher mskcpf = new MaskTextWatcher(cpfText, smf);
        //cpfText.addTextChangedListener(mskcpf);

        //mascara data nascimento
        SimpleMaskFormatter smf1 = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher msktel = new MaskTextWatcher(dataNascText, smf1);
        dataNascText.addTextChangedListener(msktel);
        //mascara data nascimento
        SimpleMaskFormatter smf2 = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mskcep = new MaskTextWatcher(cepText, smf2);
        cepText.addTextChangedListener(mskcep);

        //FIM DA CRIAÇÃO DE MASCARAS

        iconVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getBaseContext(),
                        LoginScreen.class));
            }
        });
    }

//    private void inicializarBanco() {
//        FirebaseApp.initializeApp(CriarConta.this);
//        firebaseDatabase = MyFirebaseApp.getFirebaseDatabaseInstance();
//        databaseReference = firebaseDatabase.getInstance().getReference();
//    }

//    private void limparDados() {
//        emailText.setText("");
//        senhaText.setText("");
//        nomeText.setText("");
//        dataNascText.setText("");
//        cpfText.setText("");
//        cepText.setText("");
//        cidadeText.setText("");
//        bairroText.setText("");
//        endeText.setText("");
//        numendeText.setText("");
//    }
    private void alert(String s){
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
    }
}