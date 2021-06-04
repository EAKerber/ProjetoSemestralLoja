package com.example.projetosemestralloja.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.ItemDoCarrinho;
import com.example.projetosemestralloja.databinding.LayoutItensCarrinhoBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItensDoCarinnhoAdapter extends RecyclerView.Adapter<ItensDoCarinnhoAdapter.ItemCarrinhoViewHolder> {

    private List<ItemDoCarrinho> itemDoCarrinhoList;
    private int layout;

    public ItensDoCarinnhoAdapter(List<ItemDoCarrinho> itemDoCarrinhoList, int layout) {
        this.itemDoCarrinhoList = itemDoCarrinhoList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ItemCarrinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItensCarrinhoBinding v = LayoutItensCarrinhoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemCarrinhoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCarrinhoViewHolder holder, int position) {

        ItemDoCarrinho itemDoCarrinho = (ItemDoCarrinho) this.itemDoCarrinhoList.get(position);
        holder.view.setItemCarrinho(itemDoCarrinho);
        holder.view.setAdapterItemCarrinho(this);

        /*TextView tv = holder.view.findViewById(R.id.nomeprod);
        tv.setText(itemDoCarrinho.produto.title);
        tv = holder.view.findViewById(R.id.precounitario);
        tv.setText("R$ "+ itemDoCarrinho.getPrecototal()+"");
        tv = holder.view.findViewById(R.id.qtde);
        tv.setText(itemDoCarrinho.qteselecionada+"");
        Button buttonPlus = holder.view.findViewById(R.id.buttonsoma);
        Button buttonSub = holder.view.findViewById(R.id.buttonsubtrai);

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDoCarrinho.qteselecionada = itemDoCarrinho.qteselecionada + 1;
                notifyItemChanged(position);
                notifyDataSetChanged();
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemDoCarrinho.qteselecionada > 1) {
                    itemDoCarrinho.qteselecionada = itemDoCarrinho.qteselecionada - 1;
                    notifyItemChanged(position);
                    notifyDataSetChanged();
                }else{
                    itemDoCarrinhoList.remove(itemDoCarrinho);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, itemDoCarrinhoList.size());
                    notifyDataSetChanged();
                }
            }
        });*/
    }

    @BindingAdapter({"imageItemUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    @Override
    public int getItemCount() {
        return this.itemDoCarrinhoList.size();
    }

    public class ItemCarrinhoViewHolder extends RecyclerView.ViewHolder {
        public LayoutItensCarrinhoBinding view;

        public ItemCarrinhoViewHolder(@NonNull LayoutItensCarrinhoBinding itemView) {
            super(itemView.getRoot());
            this.view = itemView;
        }

    }
}
