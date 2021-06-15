package com.example.projetosemestralloja.interfaces;


import android.content.Context;

import com.example.projetosemestralloja.model.PaginaInicialIMButton;

import java.util.List;

public interface MainActivityContrato {
    interface View{
        public Context getContexto();

        public void createButtons(List<PaginaInicialIMButton> IMButtonList);
    }
    interface presenter{
        public void createIMBList();
    }
}
