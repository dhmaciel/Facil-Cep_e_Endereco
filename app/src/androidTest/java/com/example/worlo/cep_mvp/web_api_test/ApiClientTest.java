package com.example.worlo.cep_mvp.web_api_test;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by worlo on 19/07/2017.
 */

public class ApiClientTest {

    private static Retrofit retrofit = null;

    public static Retrofit getClientMock(String mockUrl, OkHttpClient client) {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(mockUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
        return retrofit;
    }
}
