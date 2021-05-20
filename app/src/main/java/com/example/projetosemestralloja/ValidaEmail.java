package com.example.projetosemestralloja;

public class ValidaEmail {
    public static int indiceEmail;
    public static boolean isEmail(String email){
        indiceEmail = email.indexOf("@");
        if (indiceEmail > 0)
            return(true);
        else return(false);
    }
}
