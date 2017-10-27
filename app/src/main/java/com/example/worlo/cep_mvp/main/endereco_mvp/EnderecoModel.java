package com.example.worlo.cep_mvp.main.endereco_mvp;

import android.content.Context;

import com.example.worlo.cep_mvp.main.background_task.PesquisaEnderecoTask;
import com.example.worlo.cep_mvp.main.background_task.background_result.PesquisaEnderecoDelegate;
import com.example.worlo.cep_mvp.main.injection.ProvidesComponent;
import com.example.worlo.cep_mvp.main.model.PesquisaEndereco;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.web_api.ApiClient;
import com.example.worlo.cep_mvp.main.web_api.ApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by worlo on 02/10/2017.
 */

public class EnderecoModel implements EnderecoMVP.ModelOps, PesquisaEnderecoDelegate {

    @Inject
    Context context;

    // Referência para layer Presenter
    private EnderecoMVP.RequiredPresenterOps presenterOps;

    public EnderecoModel(EnderecoMVP.RequiredPresenterOps presenterOps) {
        this.presenterOps = presenterOps;
        ProvidesComponent.getContextComponent().inject(this);
    }

    @Override
    public void iniciarPesquisaPorEndereco(PesquisaEndereco pesquisaEndereco) {
        if(context != null){
            ApiInterface apiService =
                    ApiClient.getClient(context).create(ApiInterface.class);

            new PesquisaEnderecoTask(apiService, context, pesquisaEndereco, this).execute();
        }
    }

    /**
     * Delegate para retorno da asyncTask endereco.
     * @param resultadoPesquisa
     */
    @Override
    public void taskSuccessPesquisaEndereco(ArrayList<ResultadoPesquisa> resultadoPesquisa) {
        presenterOps.onResultRealizarPesquisa(resultadoPesquisa);
    }
}
