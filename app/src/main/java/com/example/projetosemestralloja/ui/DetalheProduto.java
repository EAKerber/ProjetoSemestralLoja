package com.example.projetosemestralloja.ui;

import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import android.os.Build;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.annotation.RequiresApi;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.databinding.ActivityDetalheProdutoBinding;
import com.example.projetosemestralloja.model.Produto;
import com.example.projetosemestralloja.model.ProdutoCarrinho;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;


import java.util.UUID;



public class DetalheProduto extends MenuDrawerActivity {

    public static Produto produtoDetalhe;
    public PaginaCarrinho pg = new PaginaCarrinho();
    private String cpf;

    FirebaseDatabase firebaseDatabase;
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

        //drawer.openDrawer(GravityCompat.START);
    }

    public void addAoCarrinho(View v) {
        cpf = LoginScreen.retornaCpf();
        if (cpf != null) {
            System.out.println(cpf);
            inicializarBanco(v);
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

    private void inicializarBanco(View v) {
        FirebaseApp.initializeApp(DetalheProduto.this);
        firebaseDatabase = MyFirebaseApp.getFirebaseDatabaseInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
        pg.createItemDoCarrinho(produtoDetalhe, v);
    }


    public void compartilhar() {

        ImageView imageView = findViewById(R.id.imageView6);
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Log.d("compratilhar", "01");


        String path = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"Produto",null);

        Uri uri = Uri.parse(path);

        Log.d("compratilhar", "02");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "produto"));
    }
        private static final int SOLICITAR_PERMISSAO =1;
        public void checarPermissao(View v){
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, SOLICITAR_PERMISSAO);
            } else {
                compartilhar();
            }

        }


       /* Intent intent = new  Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String sub = "http://www.example.com/gizmos";
        intent.putExtra(Intent.EXTRA_TEXT, sub);
        startActivity(Intent.createChooser(intent,  "produto"));*/




    @BindingAdapter({"imageUrl2"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

}