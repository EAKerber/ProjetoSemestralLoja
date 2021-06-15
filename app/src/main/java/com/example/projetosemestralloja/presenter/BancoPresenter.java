package com.example.projetosemestralloja.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.model.Cliente;
import com.example.projetosemestralloja.model.ItemDoCarrinho;
import com.example.projetosemestralloja.model.Produto;
import com.example.projetosemestralloja.model.ProdutoCarrinho;
import com.example.projetosemestralloja.ui.CriarConta;
import com.example.projetosemestralloja.ui.DetalheProduto;
import com.example.projetosemestralloja.ui.LoginScreen;
import com.example.projetosemestralloja.ui.MainActivity2;
import com.example.projetosemestralloja.ui.PaginaCarrinho;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BancoPresenter extends AppCompatActivity {

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
    private String cpfLogado;
    private String emailLogado;
    private String cpf;
    private String cep;
    private String endereco;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();
    DetalheProduto detalheProduto;
    CriarConta criarConta;
    ItemDoCarrinho ic;
    PaginaCarrinho pc;

    private List<Cliente> listCliente = new ArrayList<Cliente>();
    private ArrayAdapter<Cliente> arrayAdapterCliente;
    static List<ItemDoCarrinho> produtos = new ArrayList<>();
    private List<ProdutoCarrinho> listProduto = new ArrayList<ProdutoCarrinho>();
    public PaginaCarrinho pg = new PaginaCarrinho();
    public static Produto produtoDetalhe;


    public void inicializarBanco(View v) {
        FirebaseApp.initializeApp(detalheProduto);
        firebaseDatabase = MyFirebaseApp.getFirebaseDatabaseInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
        pg.createItemDoCarrinho(produtoDetalhe, v);
    }

    public void inicializarBancoConta() {
        FirebaseApp.initializeApp(criarConta);
        firebaseDatabase = MyFirebaseApp.getFirebaseDatabaseInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
    }

    public void limparDados() {
        emailText.setText("");
        senhaText.setText("");
        nomeText.setText("");
        dataNascText.setText("");
        cpfText.setText("");
        cepText.setText("");
        cidadeText.setText("");
        bairroText.setText("");
        endeText.setText("");
        numendeText.setText("");
    }

    public void eventoDatabase() {
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

    public void eventoDatabase1(View v) {
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

    public void eventoDatabase2(View v) {
        databaseReference.child("Carrinho").addValueEventListener(new ValueEventListener() {
            @Override
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
                            System.out.println("VALIDAR DADOS ");
                            System.out.println("valor " + pr.getValor());
                            System.out.println("url " + pr.getUrl());
                            System.out.println("descricao " + pr.getDescricao());
                            System.out.println("id " + pr.getId());
                            System.out.println("Titulo " + pr.getTitle());
                            System.out.println("FIM VALIDACAO DADOS ");
                            System.out.println("Tamanho lista produtos " + produtos.size());
                            pc.createItemDoCarrinho(pr, v);
                            System.out.println("Fim teste ");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child("Cliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Cliente p = objSnapshot.getValue(Cliente.class);
                    listCliente.add(p);

                    cpf = LoginScreen.retornaCpf();
                    if (cpf != null) {

                        if (cpf.equals(p.getCpfText())) {
                            cep = p.getCepText();
                            endereco = p.getCidadeText() + " " + p.getBairroText() + " " + p.getNumendText();

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void pesquisarBanco(String usuario, String senha, View v){
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

    public void pesquisarBanco2(String nome,  String cpf, String email, String senha){
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

}
