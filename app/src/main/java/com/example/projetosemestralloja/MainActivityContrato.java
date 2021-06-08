package com.example.projetosemestralloja;


import android.content.Context;

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
