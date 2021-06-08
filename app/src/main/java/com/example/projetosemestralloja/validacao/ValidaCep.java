package com.example.projetosemestralloja.validacao;

public class ValidaCep {
    public static boolean isCep(String Cep){
        if (Cep.length() == 9) {
            return true;
        }{
            return false;
        }
    }
}
