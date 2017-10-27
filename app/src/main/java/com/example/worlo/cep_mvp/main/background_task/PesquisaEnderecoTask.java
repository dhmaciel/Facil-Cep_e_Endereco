package com.example.worlo.cep_mvp.main.background_task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.background_task.background_result.PesquisaEnderecoDelegate;
import com.example.worlo.cep_mvp.main.model.PesquisaEndereco;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.web_api.ApiInterface;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by worlo on 03/06/2017.
 */

public class PesquisaEnderecoTask extends AsyncTask<Void, Void, ArrayList<ResultadoPesquisa>> {

    private final String TAG = PesquisaCepTask.class.getName();
    private ProgressDialog mProgressDialog;
    private ApiInterface apiService;
    private Context context;
    private PesquisaEndereco pesquisaEndereco;
    private PesquisaEnderecoDelegate pesquisaEnderecoDelegate;

    public PesquisaEnderecoTask(ApiInterface apiService, Context context,
                                PesquisaEndereco pesquisaEndereco,
                                PesquisaEnderecoDelegate pesquisaEnderecoDelegate) {

        this.apiService = apiService;
        this.context = context;
        this.pesquisaEndereco = pesquisaEndereco;
        this.pesquisaEnderecoDelegate = pesquisaEnderecoDelegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(context.getResources().getString(R.string.carregando));
        mProgressDialog.show();
    }

    @Override
    protected ArrayList<ResultadoPesquisa> doInBackground(Void... voids) {

        ArrayList<ResultadoPesquisa> resultadoPesquisa = null;
        Call<ArrayList<ResultadoPesquisa>> call = apiService.realizarPesquisaPorEndereco(pesquisaEndereco.getUf(),
                pesquisaEndereco.getCidade(), pesquisaEndereco.getLogradouro());
        try {
            resultadoPesquisa = call.execute().body();
        } catch (IOException e) {
            Log.e(TAG, "Erro ResultadoPesquisa ", e);
        }

        return resultadoPesquisa;
    }

    @Override
    protected void onPostExecute(ArrayList<ResultadoPesquisa> resultadoPesquisaList) {
        super.onPostExecute(resultadoPesquisaList);

        mProgressDialog.dismiss();
        pesquisaEnderecoDelegate.taskSuccessPesquisaEndereco(resultadoPesquisaList);
    }
}
