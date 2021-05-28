package com.example.projetosemestralloja.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetosemestralloja.PaginaInicialIMButton;
import com.example.projetosemestralloja.databinding.ButtonlayoutBinding;

import java.util.List;

public class IMButtonAdapter extends RecyclerView.Adapter<IMButtonAdapter.IMBViewHolder>{

    private List<PaginaInicialIMButton> IMButtonList;
    private int layout;


    public IMButtonAdapter(List<PaginaInicialIMButton> IMButtons, int layout){
        this.IMButtonList = IMButtons;
        this.layout = layout;
    }

    @NonNull
    @Override
    public IMBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ButtonlayoutBinding v = ButtonlayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IMBViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IMBViewHolder holder, int position) {

        PaginaInicialIMButton IMB = (PaginaInicialIMButton)this.IMButtonList.get(position);
        holder.view.setIMButton(IMB);
        holder.view.setAdapterIMButton(this);
        /*
        TextView tv = holder.view.findViewById(R.id.textViewPlaceHolder);
        tv.setText(IMB.getTitulo());
        ImageView im = holder.view.findViewById(R.id.imageViewPlaceHolder);
        im.setImageResource(IMB.getImage());

        CardView cv = holder.view.findViewById(R.id.ButtonCardView);
        cv.setTag(IMB.getTitulo());
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CardView btn = (CardView) v;
                //PaginaInicialIMButton IMBu = (PaginaInicialIMButton) btn.getTag();
                Intent intent = new Intent(holder.view.getContext(), IMB.intent);
                intent.putExtra("tag", IMB.getTitulo());
                holder.view.getContext().startActivity(intent);
            }
        });*/

    }
    public void onClick(View v) {
        PaginaInicialIMButton IMB = null;
        for(PaginaInicialIMButton obj:IMButtonList){
            if(obj.titulo.equals(v.getTag())){
                IMB = obj;
            }
        }
        try {
            if(IMB != null){
                Intent intent = new Intent(v.getContext(), IMB.intent);
                intent.putExtra("tag", IMB.titulo);
                Log.d("buttonadapter", "enviei " +  IMB.titulo);
                v.getContext().startActivity(intent);
            }    else {
                Toast.makeText(v.getContext(),"Não foi possível carregar Categoria", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(v.getContext(),"Não foi possível carregar Categoria", Toast.LENGTH_LONG).show();
            Log.e("erroIMButtonAdapteer", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.IMButtonList.size();
    }

    public class IMBViewHolder extends RecyclerView.ViewHolder {
        public ButtonlayoutBinding view;
        public IMBViewHolder(@NonNull ButtonlayoutBinding itemView) {
            super(itemView.getRoot());
            this.view = itemView;
        }
    }
}
