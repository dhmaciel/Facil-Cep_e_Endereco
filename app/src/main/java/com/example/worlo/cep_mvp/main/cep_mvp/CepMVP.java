package com.example.worlo.cep_mvp.main.cep_mvp;

import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.util.ArrayList;

/**
 * Created by worlo on 30/09/2017.
 */

public interface CepMVP {

    interface RequiredViewOps{
        void showToast(int resourceMsg);
        void showAlert(String msg);
        void chamarDetalhesActivity(ArrayList<ResultadoPesquisa> resultadoPesquisaList);
    }

    interface PresenterOps{
        void onConfigurationChanged(RequiredViewOps view);
        void realizarPesquisaCep(String cep);

    }

    interface RequiredPresenterOps{
        void onResultRealizarPesquisa(ArrayList<ResultadoPesquisa> resultadoPesquisaList);
        void onError(String msg);
    }

    interface ModelOps{
        void iniciarPesquisaPorCep(String cep);
    }
}
