package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetosemestralloja.model.Produto;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.adapter.ProdutoAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRecycler extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener{

    List<Produto> produtos = new ArrayList<>();
    String tag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_recycler);
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        Log.d("buttonadapter", "recebi " + tag);

        TextView tv = findViewById(R.id.textView);
        tv.setText(tag);

        RequestQueue queue = Volley.newRequestQueue(this);
        Log.d("criando queue", "queue criada");

        String url = "https://babyier.rocks/uniritter/json2.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);
        Log.d("jsonArrayRequest", "jsonArrayRequest adicionado");

        queue.add(jsonArrayRequest);
        Log.d("adicionando queue", "queue adicionada");

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        String msg = error.getMessage();
        msg += error.getCause();
        Log.d("errorResponseA", msg);
        Toast.makeText(this.getApplicationContext(),"deu erro: "+msg,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onResponse(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++){
                JSONObject json = response.getJSONObject(i);
                json.toString().replace("&quot", "'");

                Log.d("erro json", "recebi o json aaaaaaaaaaaa");

                Produto obj = new Produto(json.getInt("id"),
                        json.getString("title"),
                        json.getString("Descricao"),
                        json.getJSONArray("categoria"),
                        json.getString("Valor"),
                        json.getString("url"));

                Log.d("erro json ins", "instanciei o json");

               for (int j = 0; j < obj.categorias.length(); j++){
                    Log.d("errorResponsBA", obj.categorias.getString(j));
                    if(obj.categorias.getString(j).toLowerCase().equals(tag.toLowerCase())){
                        produtos.add(obj);
                        Log.d("erro json add", "adcionei na listaaa5");
                    }else{
                        Log.d("erro json add2", tag.toLowerCase());
                    }
               }
            }

            RecyclerView rvProduto = findViewById(R.id.Produto_Recycler);

            LinearLayoutManager llhm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            rvProduto.setLayoutManager(llhm);
            ProdutoAdapter adapter = new ProdutoAdapter(produtos, R.layout.produto_layout){
            };
            rvProduto.setAdapter(adapter);
        
        } catch (Exception e) {
            Log.e("erro", e.getMessage());
            e.printStackTrace();
        }

    }
}
