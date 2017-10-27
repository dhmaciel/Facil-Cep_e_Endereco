package com.example.worlo.cep_mvp.main.background_task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.background_task.background_result.PesquisaCepDelegate;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.web_api.ApiInterface;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by worlo on 07/05/2017.
 */

public class PesquisaCepTask extends AsyncTask<String, Void, ResultadoPesquisa> {

    private final String TAG = PesquisaCepTask.class.getName();
    private ProgressDialog mProgressDialog;
    private ApiInterface apiService;
    private Context context;
    private PesquisaCepDelegate delegate;

    public PesquisaCepTask(ApiInterface apiService, Context ctx, PesquisaCepDelegate delegate) {
        this.apiService = apiService;
        this.context = ctx;
        this.delegate = delegate;
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
    protected ResultadoPesquisa doInBackground(String... strings) {

        String cep = strings[0];

        ResultadoPesquisa resultadoPesquisa = null;
        Call<ResultadoPesquisa> call = apiService.realizarPesquisaPorCep(cep);
        try {
            resultadoPesquisa = call.execute().body();
        } catch (IOException e) {
            Log.e(TAG, "Erro ResultadoPesquisa ", e);
        }

        return resultadoPesquisa;
    }

    @Override
    protected void onPostExecute(ResultadoPesquisa resultadoPesquisa) {
        super.onPostExecute(resultadoPesquisa);
        mProgressDialog.dismiss();
        delegate.taskSuccessPesquisaCep(resultadoPesquisa);
    }
}
