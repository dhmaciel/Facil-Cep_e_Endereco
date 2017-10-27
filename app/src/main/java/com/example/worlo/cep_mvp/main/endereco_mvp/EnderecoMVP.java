package com.example.worlo.cep_mvp.main.endereco_mvp;

import com.example.worlo.cep_mvp.main.cep_mvp.CepMVP;
import com.example.worlo.cep_mvp.main.model.PesquisaEndereco;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.util.ArrayList;

/**
 * Created by worlo on 02/10/2017.
 */

public interface EnderecoMVP {

    interface RequiredViewOps{
        void showToast(int resourceMsg);
        void showAlert(String msg);
        void chamarDetalhesActivity(ArrayList<ResultadoPesquisa> resultadoPesquisaList);
    }

    interface PresenterOps{
        void onConfigurationChanged(EnderecoMVP.RequiredViewOps view);
        void realizarPesquisaEndereco(String uf, String cidade, String logradouro);
    }

    interface RequiredPresenterOps{
        void onResultRealizarPesquisa(ArrayList<ResultadoPesquisa> resultadoPesquisaList);
        void onError(String msg);
    }

    interface ModelOps{
        void iniciarPesquisaPorEndereco(PesquisaEndereco pesquisaEndereco);
    }
}
