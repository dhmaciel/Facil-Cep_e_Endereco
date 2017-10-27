package com.example.worlo.cep_mvp.main.detalhes_mvp;

import android.os.Bundle;

import com.example.worlo.cep_mvp.main.main_mvp.MainMVP;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.util.ArrayList;

/**
 * Created by worlo on 20/05/2017.
 */

public interface DetalhesMVP {

    /**
     * Métodos obrigatórios em View, disponíveis para Presenter
     *      Presenter -> View
     */
    interface RequiredViewOps{
        void showToast(int resourceMsg);
        void setupRecycler(ArrayList<ResultadoPesquisa> resultadoPesquisaList);
    }

    /**
     * operações oferecidas ao layer View para comunicação com Presenter
     *      View -> Presenter
     */
    interface PresenterOps{
        void onConfigurationChanged(DetalhesMVP.RequiredViewOps view);
        void onDestroy(boolean isChangingConfig);
        void inicializarActivity(Bundle bundle);
    }

    /**
     * operações oferecidas pelo layer Presenter para comunicações com Model
     *      Model -> Presenter
     */
    interface RequiredPresenterOps{
    }

    /**
     * operações oferecidos pelo layer Model para comunicações com Presenter
     *      Presenter -> Model
     */
    interface ModelOps{
    }
}
