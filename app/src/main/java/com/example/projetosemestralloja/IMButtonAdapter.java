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

public class IMButtonAdapter extends RecyclerView.Adapter<IMButtonAdapter.ViewHolder>{

    private List<PaginaInicialIMButton> IMButtonList;
    private  int layout;

    public IMButtonAdapter(List<PaginaInicialIMButton> IMButtons, int layout){
        this.IMButtonList = IMButtons;
        this.layout = layout;
    }

    @NonNull
    @Override
    public IMButtonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IMButtonAdapter.ViewHolder holder, int position) {

        PaginaInicialIMButton IMB = (PaginaInicialIMButton)this.IMButtonList.get(position);

        TextView tv = holder.view.findViewById(R.id.textViewPlaceHolder);
        tv.setText(IMB.getTitulo());
        ImageView im = holder.view.findViewById(R.id.imageViewPlaceHolder);
        im.setImageResource(IMB.getImage());

        CardView cv = holder.view.findViewById(R.id.ButtonCardView);
        cv.setTag(IMB);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView btn = (CardView) v;
                PaginaInicialIMButton IMBu = (PaginaInicialIMButton) btn.getTag();
                Intent intent = new Intent(holder.view.getContext(), IMB.intent);
                holder.view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.IMButtonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }
}
