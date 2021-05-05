package com.example.projetosemestralloja;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder>{

    private List<Produto> ProdutoList;
    private  int layout;

    public ProdutoAdapter(List<Produto> Produtos, int layout){
        this.ProdutoList = Produtos;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ProdutoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoAdapter.ViewHolder holder, int position) {

        Produto produto = (Produto)this.ProdutoList.get(position);

        TextView tv = holder.view.findViewById(R.id.tituloProduto);
        tv.setText(produto.getTitle());
        tv = holder.view.findViewById(R.id.descricaoProduto);
        tv.setText(produto.getDescricao());

    }

    @Override
    public int getItemCount() {
        return this.ProdutoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
