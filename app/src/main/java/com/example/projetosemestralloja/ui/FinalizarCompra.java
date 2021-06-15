package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.adapter.ItensDoCarinnhoAdapter;
import com.example.projetosemestralloja.validacao.ValidaCartao;
import com.example.projetosemestralloja.validacao.ValidaCep;
import com.example.projetosemestralloja.validacao.ValidaEndereco;

import static com.example.projetosemestralloja.ui.PaginaCarrinho.produtos;

public class FinalizarCompra extends MenuDrawerActivity {

    private String cep;
    private String endereco;
    private String cartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityTitle("Finalizar Compra");
        checkStartingItem();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v1 = layoutInflater.inflate(R.layout.activity_finalizar_compra, null, false);
        drawer.addView(v1, 0);

        Intent intent = getIntent();
        cep = intent.getStringExtra("CEP");
        endereco = intent.getStringExtra("Endereco");


        bindFinal();
        bindInfo();

        RecyclerView rvProduto = findViewById(R.id.recyclerViewFinalizar);

        LinearLayoutManager llhm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvProduto.setLayoutManager(llhm);
        ItensDoCarinnhoAdapter adapter = new ItensDoCarinnhoAdapter(produtos, R.layout.layout_itens_carrinho) {
        };
        rvProduto.setAdapter(adapter);
    }

    public void goToCarrinho(View v) {
        startActivity(new Intent(v.getContext(), PaginaCarrinho.class));
    }

    public Boolean validar(View v) {
        Boolean validado = false;
        int i = 0;

        setInfo();

        if (cep.equals(null)  || cep.equals("") || endereco.equals(null)  || endereco.equals("")
                || cartao.equals(null)  || cartao.equals("")) {

            Toast.makeText(v.getContext(), "Todos os campos precisam ser preenchidos", Toast.LENGTH_SHORT).show();
        } else {

            if (ValidaCartao.isCartaop(cartao)) {
                 i++;
            } else {
                Toast.makeText(v.getContext(), "Número de Cartão inválido", Toast.LENGTH_SHORT).show();
            }

            if (ValidaCep.isCep(cep)) {
                i++;
            } else {
                Toast.makeText(v.getContext(), "CEP inválido", Toast.LENGTH_SHORT).show();
            }

            if (ValidaEndereco.isEndereco(endereco)) {
               i++;
            } else {
                Toast.makeText(v.getContext(), "Enderço inválido", Toast.LENGTH_SHORT).show();
            }

        }
        if(i == 3){
            validado = true;
        }

        return validado;
    }

    public void finalizarCompra(View v) {
        if (validar(v)) {
            Toast.makeText(v.getContext(), "Pedido Realizado, Agradecemos pela Preferencia!", Toast.LENGTH_SHORT).show();
            produtos.clear();
            goToCarrinho(v);
        }
    }

    public void bindInfo() {
        EditText tx = findViewById(R.id.et_CEP);
        tx.setText(cep);
        tx = findViewById(R.id.et_Endereco);
        tx.setText(endereco);
    }

    public void setInfo() {
        EditText tx = findViewById(R.id.et_CEP);
        cep = tx.getText().toString();
        tx = findViewById(R.id.et_Endereco);
        endereco = tx.getText().toString();
        tx = findViewById(R.id.et_numeroCartao);
        cartao = tx.getText().toString();
    }


}