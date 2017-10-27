package com.example.worlo.cep_mvp.main.main_mvp;

import android.content.Context;

import com.example.worlo.cep_mvp.main.background_task.PesquisaCepTask;
import com.example.worlo.cep_mvp.main.background_task.PesquisaEnderecoTask;
import com.example.worlo.cep_mvp.main.background_task.background_result.PesquisaCepDelegate;
import com.example.worlo.cep_mvp.main.background_task.background_result.PesquisaEnderecoDelegate;
import com.example.worlo.cep_mvp.main.injection.ProvidesComponent;
import com.example.worlo.cep_mvp.main.model.PesquisaEndereco;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.web_api.ApiClient;
import com.example.worlo.cep_mvp.main.web_api.ApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by worlo on 01/04/2017.
 */

public class MainModel implements MainMVP.ModelOps, PesquisaCepDelegate, PesquisaEnderecoDelegate {

    // Referência para layer Presenter
    private MainMVP.RequiredPresenterOps mPresenter;
    @Inject
    Context context;

    public MainModel(MainMVP.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
        ProvidesComponent.getContextComponent().inject(this);
    }

    /**
     * Disparada por {@link MainPresenter#onDestroy(boolean)}
     * para as operações necessárias que eventualmente
     * estiverem executando no BG
     */
    @Override
    public void onDestroy() {

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

        mPresenter.onResultRealizarPesquisa(resultadoPesquisaList);
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

        mPresenter.onResultRealizarPesquisa(resultadoPesquisa);
    }
}
