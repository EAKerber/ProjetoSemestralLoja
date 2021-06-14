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
import com.example.projetosemestralloja.model.Produto;
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
        pg.createItemDoCarrinho(produtoDetalhe, v);
    }

    public void compartilhar(View v){/*
        ImageView imageView = findViewById(R.id.imageView6);
        Intent intent = new  Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Log.d("compratilhar", "01");


        String packageName = getPackageName();
        getApplicationContext().grantUriPermission(packageName,  Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Produto", null)),
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Produto", null));



        Log.d("compratilhar", "02");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent,  "produto"));*/

        Intent intent = new  Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String sub = "http://www.example.com/gizmos";
        intent.putExtra(Intent.EXTRA_TEXT, sub);
        startActivity(Intent.createChooser(intent,  "produto"));


    }

    @BindingAdapter({"imageUrl2"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

}