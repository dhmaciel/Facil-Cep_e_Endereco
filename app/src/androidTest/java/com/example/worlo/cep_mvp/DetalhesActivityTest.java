package com.example.worlo.cep_mvp;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.worlo.cep_mvp.main.detalhes_mvp.DetalhesActivity;
import com.example.worlo.cep_mvp.main.model.PesquisaEndereco;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.web_api.ApiInterface;
import com.example.worlo.cep_mvp.mocks.Mocks;
import com.example.worlo.cep_mvp.web_api_test.ApiClientTest;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;

/**
 * Created by worlo on 25/07/2017.
 */

@RunWith(AndroidJUnit4.class)
public class DetalhesActivityTest {

    private okhttp3.mockwebserver.MockWebServer server;
    private ApiInterface apiInterface;
    private String TAG = getClass().getName();

    @Rule
    public ActivityTestRule<DetalhesActivity> detalhesActivityTestRule =
            new ActivityTestRule<>(DetalhesActivity.class, false,false);

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        setupServerUrl();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    private void setupServerUrl() {
        String url = server.url("/").toString();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiInterface = ApiClientTest.getClientMock(url, client).create(ApiInterface.class);
    }

    private Context getContext(){
        return InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
    }

    private void buscarEnderecoPorCepMockado() throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(Mocks.SUCCESS_SINGLE_ITEM));
        detalhesActivityTestRule.launchActivity(getIntentDetalhesActivityCep("32587412"));
    }

    private void buscarEnderecoMockado(PesquisaEndereco pesquisaEndereco) throws IOException {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(Mocks.SUCCESS_MULTIPLE_ITEMS));
        detalhesActivityTestRule.launchActivity(getIntentDetalhesActivityEndereco(pesquisaEndereco));
    }

    private Intent getIntentDetalhesActivityCep(String cep) throws IOException {
        Intent intent = new Intent(getContext(), DetalhesActivity.class);
        intent.putExtra(ResultadoPesquisa.NAME_RESULTADO_PESQUISA, executarChamadaMockCep(cep));
        return intent;
    }

    private Intent getIntentDetalhesActivityEndereco(PesquisaEndereco pesquisaEndereco) throws IOException {
        Intent intent = new Intent(getContext(), DetalhesActivity.class);
        intent.putExtra(ResultadoPesquisa.NAME_RESULTADO_PESQUISA, executarChamadaMockEndereco(pesquisaEndereco));
        return intent;
    }

    private ArrayList<ResultadoPesquisa> executarChamadaMockCep(String cep) throws IOException {
            Call<ResultadoPesquisa> resultadoPesquisaCall = apiInterface.realizarPesquisaPorCep(cep);
            ArrayList<ResultadoPesquisa> resultadoPesquisaArrayList = new ArrayList<>();
            resultadoPesquisaArrayList.add(resultadoPesquisaCall.execute().body());
            return resultadoPesquisaArrayList;
    }

    private ArrayList<ResultadoPesquisa> executarChamadaMockEndereco(PesquisaEndereco pesquisaEndereco) throws IOException {
        Call<ArrayList<ResultadoPesquisa>> resultadoPesquisaCall =
                apiInterface.realizarPesquisaPorEndereco(
                        pesquisaEndereco.getUf(),
                        pesquisaEndereco.getCidade(),
                        pesquisaEndereco.getLogradouro());

        return resultadoPesquisaCall.execute().body();
    }

    //*Testes

    @Test
    public void executarTesteEnderecoEExibidoPorCep() throws IOException {
        buscarEnderecoPorCepMockado();
        Espresso.onView(ViewMatchers.withId(R.id.txt_cep_detalhes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.txt_logradouro_detalhes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.txt_bairro_detalhes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.txt_cidade_detalhes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.txt_uf_detalhes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Realiza o teste em multiplas células para verificar exibição.
     * Deve respeitar a estrutura de views no layout.
     * @throws IOException
     */
    @Test
    public void executarTesteEnderecosExibidosPorEndereco() throws IOException {
        buscarEnderecoMockado(new PesquisaEndereco("MG", "Betim", "Palma"));
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.txt_logradouro_detalhes), ViewMatchers.hasSibling(ViewMatchers.withText("91040-000")))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.txt_cep_detalhes), ViewMatchers.hasSibling(ViewMatchers.withText("Sarandi")))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.txt_logradouro_detalhes), ViewMatchers.withText("Rua Domingos José Poli"))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
