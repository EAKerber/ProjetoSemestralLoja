package com.example.projetosemestralloja.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.projetosemestralloja.ImageLoader;
import com.example.projetosemestralloja.Produto;
import com.example.projetosemestralloja.R;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>{

    private List<Produto> ProdutoList;
    private  int layout;

    public ProdutoAdapter(List<Produto> Produtos, int layout){
        this.ProdutoList = Produtos;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ProdutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {

        Produto produto = (Produto)this.ProdutoList.get(position);

        TextView tv = holder.view.findViewById(R.id.tituloProduto);
        tv.setText(produto.getTitle());
        tv = holder.view.findViewById(R.id.descricaoProduto);
        tv.setText(produto.getDescricao());

        ImageLoader iml = new ImageLoader();
        iml.loadImg(produto.getUrl(), holder.view.findViewById(R.id.produto_IM));

    }

    @Override
    public int getItemCount() {
        return this.ProdutoList.size();
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
