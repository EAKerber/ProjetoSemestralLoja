package com.example.projetosemestralloja;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class PaginaInicialIMButton {
    public int image;
    public  Class intent;
    public String titulo;
    public Drawable drawable;

    public PaginaInicialIMButton(int image, Class intent, String titulo, Context context) {
        this.image = image;
        this.titulo = titulo;
        this.intent = intent;
        this.drawable  = ContextCompat.getDrawable(context, image);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) { this.image = image; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Drawable getDrawable() {
        return drawable;
    }

}
