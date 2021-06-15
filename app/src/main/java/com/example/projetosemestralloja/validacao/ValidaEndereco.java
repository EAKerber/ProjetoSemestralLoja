package com.example.projetosemestralloja.validacao;

public class ValidaEndereco {
    public static boolean isEndereco(String Endereco){
        if (Endereco.length() > 7) {
            return true;
        }{
            return false;
        }
    }
}
