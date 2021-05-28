package com.example.projetosemestralloja;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItensDoCarinnhoAdapter extends RecyclerView.Adapter<ItensDoCarinnhoAdapter.ViewHolder> {

    private List<ItemDoCarrinho> itemDoCarrinhoList;
    private int layout;

    public ItensDoCarinnhoAdapter(List<ItemDoCarrinho> itemDoCarrinhoList, int layout) {
        this.itemDoCarrinhoList = itemDoCarrinhoList;
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

        ItemDoCarrinho itemDoCarrinho = (ItemDoCarrinho) this.itemDoCarrinhoList.get(position);

        TextView tv = holder.view.findViewById(R.id.nomeprod);
        tv.setText(itemDoCarrinho.produto.title);
        tv = holder.view.findViewById(R.id.precounitario);
        tv.setText(itemDoCarrinho.produto.valor);
        tv = holder.view.findViewById(R.id.qtde);
        tv.setText(itemDoCarrinho.qteselecionada+"");
        Button buttonPlus = holder.view.findViewById(R.id.buttonsoma);
        Button buttonSub = holder.view.findViewById(R.id.buttonsubtrai);

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDoCarrinho.qteselecionada = itemDoCarrinho.qteselecionada + 1;
                TextView tv = holder.view.findViewById(R.id.qtde);
                tv.setText(itemDoCarrinho.qteselecionada+"");
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemDoCarrinho.qteselecionada > 1) {
                    itemDoCarrinho.qteselecionada = itemDoCarrinho.qteselecionada - 1;
                    notifyItemChanged((int)itemDoCarrinho.getId()-1);
                }else{
                    /*paginaCarrinho pgCarrinho = new paginaCarrinho();
                    pgCarrinho.removeoflist(itemDoCarrinhoList,itemDoCarrinho);*/
                    itemDoCarrinhoList.remove(itemDoCarrinho);
                    notifyItemRemoved((int) itemDoCarrinho.getId()-1);
                    notifyItemRangeChanged((int) itemDoCarrinho.getId()-1, itemDoCarrinhoList.size());
                    notifyDataSetChanged();
                }
            }
        });

        // ImageLoader iml = new ImageLoader();
        // iml.loadImg(Produto.getUrl(), holder.view.findViewById(R.id.produto_IM));

    }

    @Override
    public int getItemCount() {
        return this.itemDoCarrinhoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }

    }
}
