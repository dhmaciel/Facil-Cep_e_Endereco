package com.example.worlo.cep_mvp.main.web_api;

import android.content.Context;

import com.example.worlo.cep_mvp.R;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by worlo on 07/05/2017.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    /**
     * Singleton que obtem a instância do Retrofit.
     *
     * @param ctx
     * @return
     */
    public static Retrofit getClient(Context ctx) {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(ctx.getString(R.string.config_base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
