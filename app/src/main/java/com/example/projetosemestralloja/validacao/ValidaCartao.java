package com.example.projetosemestralloja.validacao;

public class ValidaCartao {
    public static boolean isCartaop(String Cartao){
        if (Cartao.length() == 16) {
            return true;
        }{
            return false;
        }
    }
}
