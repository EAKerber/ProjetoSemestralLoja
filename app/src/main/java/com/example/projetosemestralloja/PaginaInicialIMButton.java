package com.example.projetosemestralloja;

public class PaginaInicialIMButton {
    public int image;
    public  Class intent;
    public String titulo;

    public PaginaInicialIMButton(int image, Class intent, String titulo) {
        this.image = image;
        this.titulo = titulo;
        this.intent = intent;
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

}
