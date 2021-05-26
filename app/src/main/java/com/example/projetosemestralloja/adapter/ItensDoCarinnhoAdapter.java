package com.example.projetosemestralloja.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.Produto;
import com.example.projetosemestralloja.R;

public class ItensDoCarinnhoAdapter extends RecyclerView.Adapter<ItensDoCarinnhoAdapter.ItensViewHolder> {

    private Produto[] produtos;
    private int layout;

    public ItensDoCarinnhoAdapter(Produto[] produtos, int layout) {
        this.produtos = produtos;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ItensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ItensViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItensViewHolder holder, int position) {

        Produto itemDoCarrinho = (Produto) this.produtos[position];

        TextView tv = holder.view.findViewById(R.id.nomeproduto);
        tv.setText(itemDoCarrinho.title);
        tv = holder.view.findViewById(R.id.tvPrecoProduto);
        tv.setText(itemDoCarrinho.valor);

        // ImageLoader iml = new ImageLoader();
        // iml.loadImg(Produto.getUrl(), holder.view.findViewById(R.id.produto_IM));

    }

    @Override
    public int getItemCount() {
        return this.produtos.length;
    }

    public class ItensViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ItensViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }

    }
}
