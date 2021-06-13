package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.databinding.ActivityDetalheProdutoBinding;
import com.example.projetosemestralloja.model.Cliente;
import com.example.projetosemestralloja.model.Produto;
import com.example.projetosemestralloja.model.ProdutoCarrinho;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetalheProduto extends AppCompatActivity {

    public static Produto produtoDetalhe;
    public PaginaCarrinho pg = new PaginaCarrinho();
    private String cpf;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);
        Intent intent = getIntent();
        produtoDetalhe = intent.getParcelableExtra("produtoProdutoAdapter");
        ActivityDetalheProdutoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe_produto);
        binding.setProdutoDetalhe(produtoDetalhe);
    }

    public void addAoCarrinho(View v){
        cpf = LoginScreen.retornaCpf();
        System.out.println(cpf);
        inicializarBanco();
        ProdutoCarrinho p = new ProdutoCarrinho();
        p.setId(produtoDetalhe.getId());
        p.setValor(produtoDetalhe.getValor());
        p.setCpf(cpf);
        p.setDescricao(produtoDetalhe.getDescricao());
        p.setUrl(produtoDetalhe.getUrl());
        p.setTitle(produtoDetalhe.getTitle());

        databaseReference.child("Carrinho").child(cpf).setValue(p);
        //pg.createItemDoCarrinho(produtoDetalhe);
    }

    private void inicializarBanco() {
        FirebaseApp.initializeApp(DetalheProduto.this);
        firebaseDatabase = MyFirebaseApp.getFirebaseDatabaseInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
    }

    @BindingAdapter({"imageUrl2"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

}