package com.example.projetosemestralloja;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItensDoCarinnhoAdapter extends RecyclerView.Adapter<ItensDoCarinnhoAdapter.ViewHolder> {

    private Produto[] produtos;
    private int layout;

    public ItensDoCarinnhoAdapter(Produto[] produtos, int layout) {
        this.produtos = produtos;
        this.layout = layout;
    }

    @NonNull
    @Override
    public  ItensDoCarinnhoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItensDoCarinnhoAdapter.ViewHolder holder, int position) {

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }

    }
}
