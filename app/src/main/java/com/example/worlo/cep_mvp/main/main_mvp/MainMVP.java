package com.example.worlo.cep_mvp.main.main_mvp;

import com.example.worlo.cep_mvp.main.model.PesquisaEndereco;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by worlo on 01/04/2017.
 */
/*
 * Interface guarda-chuva do padrão MVP, agrega todas as operações de
 * comunicação entre os diferentes layer do padrão: MODEL, VIEW, PRESENTER
 */

public interface MainMVP {

    /**
     * Métodos obrigatórios em View, disponíveis para Presenter
     *      Presenter -> View
     */
    interface RequiredViewOps{
        void showToast(int resourceMsg);
        void showAlert(String msg);
        void chamarDetalhesActivity(ArrayList<ResultadoPesquisa> resultadoPesquisaList);
    }

    /**
     * operações oferecidas ao layer View para comunicação com Presenter
     *      View -> Presenter
     */
    interface PresenterOps{
        void onConfigurationChanged(RequiredViewOps view);
        void onDestroy(boolean isChangingConfig);
        void realizarPesquisaCep(String cep);
        void realizarPesquisaEndereco(String uf, String cidade, String logradouro);
        // qualquer outra operação a ser chamada pelo View
    }

    /**
     * operações oferecidas pelo layer Presenter para comunicações com Model
     *      Model -> Presenter
     */
    interface RequiredPresenterOps{
        void onResultRealizarPesquisa(ArrayList<ResultadoPesquisa> resultadoPesquisaList);
        void onError(String msg);
        // qualquer operação de retorno Model -> Presenter
    }

    /**
     * operações oferecidos pelo layer Model para comunicações com Presenter
     *      Presenter -> Model
     */
    interface ModelOps{
        void iniciarPesquisaPorCep(String cep);
        void iniciarPesquisaPorEndereco(PesquisaEndereco pesquisaEndereco);
        void onDestroy();
        // Qualquer operação referente à dados a ser chamado pelo Presenter
    }
}
