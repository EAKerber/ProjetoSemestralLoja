package com.example.projetosemestralloja.presenter;

import com.example.projetosemestralloja.interfaces.MainActivityContrato;
import com.example.projetosemestralloja.model.PaginaInicialIMButton;
import com.example.projetosemestralloja.ui.ProdutoRecycler;
import com.example.projetosemestralloja.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter implements MainActivityContrato.presenter {

   List<PaginaInicialIMButton> IMButtonList = new ArrayList<>();

   private MainActivityContrato.View view;

   public MainActivityPresenter(MainActivityContrato.View view){
       this.view = view;
   }

   public void createIMBList(){

        addOnIMBList(R.drawable.masculino, "Masculino", ProdutoRecycler.class);
        addOnIMBList(R.drawable.feminino, "Feminino", ProdutoRecycler.class);
        addOnIMBList(R.drawable.infantil, "Infantil", ProdutoRecycler.class);
        addOnIMBList(R.drawable.calca, "Calças", ProdutoRecycler.class);
        addOnIMBList(R.drawable.pijama, "Pijama", ProdutoRecycler.class);
        addOnIMBList(R.drawable.jaqueta, "Jaqueta", ProdutoRecycler.class);
        addOnIMBList(R.drawable.bermuda, "Bermuda", ProdutoRecycler.class);
        addOnIMBList(R.drawable.camisa, "Camisa", ProdutoRecycler.class);
        view.createButtons(IMButtonList);
    }

   public void addOnIMBList(int imagem, String titulo, Class intent){
        PaginaInicialIMButton imb = new PaginaInicialIMButton(imagem, intent, titulo, view.getContexto());
        IMButtonList.add(imb);
    }

}
