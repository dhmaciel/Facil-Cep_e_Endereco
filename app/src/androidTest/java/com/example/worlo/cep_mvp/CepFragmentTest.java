package com.example.worlo.cep_mvp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.worlo.cep_mvp.main.detalhes_mvp.DetalhesActivity;
import com.example.worlo.cep_mvp.main.main_mvp.MainActivity;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.web_api.ApiInterface;
import com.example.worlo.cep_mvp.mocks.Mocks;
import com.example.worlo.cep_mvp.web_api_test.ApiClientTest;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;

/**
 * Created by worlo on 02/07/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CepFragmentTest {

    private okhttp3.mockwebserver.MockWebServer server;
    private ApiInterface apiInterface;
    private String TAG = CepFragmentTest.class.getName();

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class, false, true);

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

    @Test
    public void executarTesteEstadoInicialTelaCep(){
        Espresso.onView(ViewMatchers.withId(R.id.viewpager)).perform(ViewActions.swipeRight());
        Espresso.onView(ViewMatchers.withId(R.id.edt_num_cep))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.txt_cep_limpar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btn_cep_pesquisar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void executarTesteClickCepVazio(){
        testPesquisarComToast();
    }

    //Important in documentation: Note: the destination activity will not be launched.
    @Test
    public void executarPesquisarCepValido(){
        Intents.init();

        testPreencheCep("32673136");

        Matcher<Intent> matcher = IntentMatchers.hasComponent(DetalhesActivity.class.getName());

        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);
        Intents.intending(matcher).respondWith(result);

        Espresso.onView(ViewMatchers.withId(R.id.btn_cep_pesquisar))
                .perform(ViewActions.click());

        Intents.intended(matcher);

        Intents.release();
    }

    @Test
    public void executarPesquisarCepInvalido(){
        testPreencheCep("38458458");
        testPesquisarComToast();
    }

    private void testPreencheCep(String numCep){
        Espresso.onView(ViewMatchers.withId(R.id.edt_num_cep))
                .perform(ViewActions.typeText(numCep), ViewActions.closeSoftKeyboard());
    }

    private void testPesquisarComToast(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_cep_pesquisar))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void executarTesteSucessoMockado() {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(Mocks.SUCCESS_SINGLE_ITEM));
        detalhesActivityTestRule.launchActivity(getIntentDetalhesActivityCep("32587412"));
    }

    @Test
    public void executarTesteErroMockado() {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(Mocks.ERROR));
        detalhesActivityTestRule.launchActivity(getIntentDetalhesActivityCep("000000"));
    }

    @Test
    public void executarTesteSemRespostaoMockado() {
        server.enqueue(new MockResponse().setResponseCode(400));
        detalhesActivityTestRule.launchActivity(getIntentDetalhesActivityCep(""));
    }

    private Intent getIntentDetalhesActivityCep(String cep){
        Intent intent = new Intent(getContext(), DetalhesActivity.class);
        intent.putExtra(ResultadoPesquisa.NAME_RESULTADO_PESQUISA, executarChamadaMockCep(cep));
        return intent;
    }

    private ArrayList<ResultadoPesquisa>  executarChamadaMockCep(String cep){
        try{
            Call<ResultadoPesquisa> resultadoPesquisaCall = apiInterface.realizarPesquisaPorCep(cep);
            ArrayList<ResultadoPesquisa> resultadoPesquisaArrayList = new ArrayList<>();
            resultadoPesquisaArrayList.add(resultadoPesquisaCall.execute().body());
            return resultadoPesquisaArrayList;

        } catch (IOException io){
            Log.e(TAG, "Erro em executarChamadaMockCep", io);
        }

        return null;
    }
}
