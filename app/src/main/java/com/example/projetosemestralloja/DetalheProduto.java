package com.example.projetosemestralloja;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.example.projetosemestralloja.databinding.ActivityDetalheProdutoBinding;
import com.squareup.picasso.Picasso;

public class DetalheProduto extends AppCompatActivity {

    public static Produto produtoDetalhe;
    public  PaginaCarrinho pg = new PaginaCarrinho();

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
        pg.createItemDoCarrinho(produtoDetalhe);
    }

    @BindingAdapter({"imageUrl2"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

}