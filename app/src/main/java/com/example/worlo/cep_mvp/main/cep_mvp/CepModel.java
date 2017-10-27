package com.example.worlo.cep_mvp.main.cep_mvp;

import android.content.Context;

import com.example.worlo.cep_mvp.main.background_task.PesquisaCepTask;
import com.example.worlo.cep_mvp.main.background_task.background_result.PesquisaCepDelegate;
import com.example.worlo.cep_mvp.main.injection.ProvidesComponent;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.web_api.ApiClient;
import com.example.worlo.cep_mvp.main.web_api.ApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by worlo on 30/09/2017.
 */

public class CepModel implements CepMVP.ModelOps, PesquisaCepDelegate {

    @Inject
    Context context;

    // Referência para layer Presenter
    private CepMVP.RequiredPresenterOps presenterOps;

    public CepModel(CepMVP.RequiredPresenterOps presenterOps) {
        this.presenterOps = presenterOps;
        ProvidesComponent.getContextComponent().inject(this);
    }

    @Override
    public void iniciarPesquisaPorCep(String cep) {
        if(context != null){
            ApiInterface apiService =
                    ApiClient.getClient(context).create(ApiInterface.class);

            new PesquisaCepTask(apiService, context, this).execute(cep);
        }
    }

    /**
     * Delegate para retorno da asyncTask cep.
     * @param resultadoPesquisa
     */
    @Override
    public void taskSuccessPesquisaCep(ResultadoPesquisa resultadoPesquisa) {
        ArrayList<ResultadoPesquisa> resultadoPesquisaList = new ArrayList<>();
        resultadoPesquisaList.add(resultadoPesquisa);

        presenterOps.onResultRealizarPesquisa(resultadoPesquisaList);
    }
}
