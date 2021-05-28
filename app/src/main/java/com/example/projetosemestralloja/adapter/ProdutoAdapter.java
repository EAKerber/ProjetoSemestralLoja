package com.example.projetosemestralloja.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.Produto;
import com.example.projetosemestralloja.databinding.ProdutoLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>{

    private List<Produto> ProdutoList;
    private int layout;


    public ProdutoAdapter(List<Produto> Produtos, int layout){
        this.ProdutoList = Produtos;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProdutoLayoutBinding v = ProdutoLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProdutoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {

        Produto produto = (Produto)this.ProdutoList.get(position);
        holder.view.setProduto(produto);
        holder.view.setAdapterProduto(this);

        /*
        TextView tv = holder.view.findViewById(R.id.tituloProduto);
        tv.setText(produto.getTitle());
        tv = holder.view.findViewById(R.id.descricaoProduto);
        tv.setText(produto.getDescricao());
        ImageLoader iml = new ImageLoader();
        iml.loadImg(produto.getUrl(), holder.view.);
        */

    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @Override
    public int getItemCount() {
        return this.ProdutoList.size();
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder {
        public ProdutoLayoutBinding view;
        public ProdutoViewHolder(@NonNull ProdutoLayoutBinding itemView) {
            super(itemView.getRoot());
            this.view = itemView;
        }
    }
}
