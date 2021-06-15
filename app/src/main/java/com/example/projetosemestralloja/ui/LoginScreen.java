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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetosemestralloja.model.Cliente;
import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.model.ItemDoCarrinho;
import com.example.projetosemestralloja.model.Produto;
import com.example.projetosemestralloja.model.ProdutoCarrinho;
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
    private static String cpfLogado;
    private static String emailLogado;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();
    static List<ItemDoCarrinho> produtos = new ArrayList<>();
    private List<Cliente> listCliente = new ArrayList<Cliente>();
    private ArrayAdapter<Cliente> arrayAdapterCliente;
    private List<ProdutoCarrinho> listProduto = new ArrayList<ProdutoCarrinho>();
    public PaginaCarrinho pg = new PaginaCarrinho();
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
        produtos.clear();
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

                pesquisarBanco(loginText.getText().toString(), senhaText.getText().toString(), v);

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
                    System.out.println("cpf " + c.getCpfText());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void eventoDatabase1(View v) {
        databaseReference.child("Carrinho").addValueEventListener(new ValueEventListener() {

            String cpf;
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    ProdutoCarrinho p = objSnapshot.getValue(ProdutoCarrinho.class);
                    listProduto.add(p);

                    cpf = LoginScreen.retornaCpf();
                    if (cpf != null) {

                        if (cpf.equals(p.getCpf())) {
                            System.out.println("Entrou aqui " + p.getCpf() + " cpf 2: " + cpf);
                            Produto pr = new Produto();
                            pr.setValor(p.getValor());
                            pr.setId(p.getId());
                            pr.setUrl(p.getUrl());
                            pr.setTitle(p.getTitle());
                            pr.setDescricao(p.getDescricao());
                            pr.setCategorias(null);
                            pg.createItemDoCarrinho(pr, v);
                            System.out.println("Fim teste ");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void pesquisarBanco(String usuario, String senha, View v){
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
                System.out.println("CPF " + listCliente.get(i).getCpfText());
                System.out.println(usuario);
                System.out.println(senha);
                if (usuario.equals(listCliente.get(i).getEmailText())) {
                    usuarioValid = 1;
                }
                if (senha.equals(listCliente.get(i).getSenhaText())) {
                    senhaValid = 1;
                }
                if (usuarioValid == 1 && senhaValid == 1) {
                    //startActivity(new Intent(getBaseContext(), 'PROXIMA TELA'));
                    limparDados();
                    alert("Login efetuado com sucesso.");
                    cpfLogado = listCliente.get(i).getCpfText();
                    eventoDatabase1(v);
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
    private void alert(String s){
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
    }
}