package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.databinding.ActivityDetalheProdutoBinding;
import com.example.projetosemestralloja.interfaces.BancoContrato;
import com.example.projetosemestralloja.model.Produto;
import com.example.projetosemestralloja.model.ProdutoCarrinho;
import com.example.projetosemestralloja.presenter.BancoPresenter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.UUID;


public class DetalheProduto extends MenuDrawerActivity {

    public static Produto produtoDetalhe;
    public PaginaCarrinho pg = new PaginaCarrinho();
    private String cpf;

    BancoContrato.presenter bp;
    DatabaseReference databaseReference;
    MyFirebaseApp m = new MyFirebaseApp();

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        produtoDetalhe = intent.getParcelableExtra("produtoProdutoAdapter");
        ActivityDetalheProdutoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe_produto);
        binding.setProdutoDetalhe(produtoDetalhe);

        setActivityTitle("Detalhes");
        checkStartingItem();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v5 = layoutInflater.inflate(R.layout.activity_detalhe_produto, null, false);
        drawer.addView(v5, 0);

        Log.d("menuDetalhe", "3");

    }

    public void addAoCarrinho(View v) {
        cpf = LoginScreen.retornaCpf();
        if (cpf != null) {
            System.out.println(cpf);
            bp.inicializarBanco(v);
            ProdutoCarrinho p = new ProdutoCarrinho();
            p.setId(produtoDetalhe.getId());
            p.setValor(produtoDetalhe.getValor());
            p.setCpf(cpf);
            p.setDescricao(produtoDetalhe.getDescricao());
            p.setUrl(produtoDetalhe.getUrl());
            p.setTitle(produtoDetalhe.getTitle());
            p.setUuid(UUID.randomUUID().toString());

            databaseReference.child("Carrinho").child(p.getUuid()).setValue(p);
        }else{
            pg.createItemDoCarrinho(produtoDetalhe, v);
        }
    }

    public void compartilhar(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String sub = "http://www.example.com/gizmos";
        intent.putExtra(Intent.EXTRA_TEXT, sub);
        startActivity(Intent.createChooser(intent, "produto"));
    }

    @BindingAdapter({"imageUrl2"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }
}