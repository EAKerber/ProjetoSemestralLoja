package com.example.projetosemestralloja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.MyFirebaseApp;
import com.example.projetosemestralloja.R;
import com.example.projetosemestralloja.adapter.ItensDoCarinnhoAdapter;
import com.example.projetosemestralloja.model.ProdutoCarrinho;
import com.example.projetosemestralloja.validacao.ValidaCartao;
import com.example.projetosemestralloja.validacao.ValidaCep;
import com.example.projetosemestralloja.validacao.ValidaEndereco;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.projetosemestralloja.ui.PaginaCarrinho.produtos;

public class FinalizarCompra extends MenuDrawerActivity {

    private String cep;
    private String endereco;
    private String cartao;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
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
            String cpf;
            cpf = LoginScreen.retornaCpf();
            inicializarBanco();
            Toast.makeText(v.getContext(), "Pedido Realizado, Agradecemos pela Preferencia!", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < produtos.size(); i++){
                eventoDatabase(cpf, produtos.get(i).getProduto().getId());
            }
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

    private void eventoDatabase(String cpf, int id) {
        databaseReference.child("Carrinho").addValueEventListener(new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    ProdutoCarrinho p = objSnapshot.getValue(ProdutoCarrinho.class);
                    if(cpf.equals(p.getCpf()) && id == p.getId()){
                        databaseReference.child("Carrinho").child(p.getUuid()).removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void inicializarBanco() {
        FirebaseApp.initializeApp(FinalizarCompra.this);
        firebaseDatabase = MyFirebaseApp.getFirebaseDatabaseInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
    }


}