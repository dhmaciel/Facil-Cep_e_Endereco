package com.example.worlo.cep_mvp.main.web_api;

import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by worlo on 07/05/2017.
 */

public interface ApiInterface {

    @GET("{cep}/json")
    Call<ResultadoPesquisa> realizarPesquisaPorCep(@Path("cep") String cep);

    @GET("{uf}/{cidade}/{logradouro}/json")
    Call<ArrayList<ResultadoPesquisa>> realizarPesquisaPorEndereco(@Path("uf") String uf,
                                                                   @Path("cidade") String cidade,
                                                                   @Path("logradouro") String logradouro);
}
