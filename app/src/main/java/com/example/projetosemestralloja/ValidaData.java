package com.example.projetosemestralloja;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidaData {
    public static boolean isData(String Data){
        Date data = null;
        String dataTexto = new String(Data);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.setLenient(false);
            data = format.parse(dataTexto);
            return(true);
        }catch(ParseException e){
            return(false);
        }
    }
}
