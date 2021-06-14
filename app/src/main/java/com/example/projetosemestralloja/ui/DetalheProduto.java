package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.databinding.ActivityDetalheProdutoBinding;
import com.example.projetosemestralloja.model.Produto;
import com.squareup.picasso.Picasso;


public class DetalheProduto extends MenuDrawerActivity {

    public static Produto produtoDetalhe;
    public PaginaCarrinho pg = new PaginaCarrinho();

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

        drawer.openDrawer(GravityCompat.START);
    }

    public void addAoCarrinho(View v){
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