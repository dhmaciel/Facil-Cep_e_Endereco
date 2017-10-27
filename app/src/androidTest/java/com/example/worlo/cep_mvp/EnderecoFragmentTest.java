package com.example.worlo.cep_mvp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.worlo.cep_mvp.Utils.ToastManagerIdlingResource;
import com.example.worlo.cep_mvp.Utils.ViewPagerIdlingResource;
import com.example.worlo.cep_mvp.main.main_mvp.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.registerIdlingResources;

/**
 * Created by worlo on 09/07/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EnderecoFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Before
    public void setUp() throws Exception {
        //toastWithIdling();
    }

    private void swipeWithIdling(String nameViewPage){
        registerIdlingResources(new ViewPagerIdlingResource((mainActivityRule.getActivity().viewPager), nameViewPage));
        Espresso.onView(ViewMatchers.withId(R.id.viewpager)).perform(ViewActions.swipeLeft());
    }

    private void toastWithIdling(){
        registerIdlingResources(ToastManagerIdlingResource.getIdlingResource());
    }

    @Test
    public void executarTesteEstadoInicialTelaEndereco(){
        swipeWithIdling("viewInicial");

        Espresso.onView(ViewMatchers.withId(R.id.spinner_estados))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.edt_cidade))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.edt_logradouro))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.txt_endereco_limpar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btn_endereco_pesquisar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    //Testes campos vazios
    @Test
    public void executarTesteClickCepVazio(){
        swipeWithIdling("viewCepVazio");
        testPesquisarComToast();
        //testToastMessage(R.string.toast_campos_obrigatorios);
    }

    @Test
    public void executarTestePesquisarEnderecoInvalido(){
        swipeWithIdling("viewEnderecoInvalido");
        preencheCidade("Batata");
        preencheLogradouro("Chia");
        testePesquisarComToast();
        //testToastMessage(R.string.label_main_erro_pesquisa);
    }

    @Test
    public void executarTestePesquisaEnderecoAteTresCaracteres(){
        swipeWithIdling("viewTresChars");
        preencheCidade("Ana");
        preencheLogradouro("Via");
        testePesquisarComToast();
        //testToastMessage(R.string.label_main_erro_pesquisa);
    }

    private void testePesquisarComToast(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_endereco_pesquisar))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    private void preencheCidade(String cidade){
        Espresso.onView(ViewMatchers.withId(R.id.edt_cidade))
                .perform(ViewActions.typeText(cidade), ViewActions.closeSoftKeyboard());
    }

    private void preencheLogradouro(String logradouro){
        Espresso.onView(ViewMatchers.withId(R.id.edt_logradouro))
                .perform(ViewActions.typeText(logradouro), ViewActions.closeSoftKeyboard());
    }

    private void testPesquisarComToast(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_endereco_pesquisar))
                .perform(ViewActions.click())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    private void testToastMessage(int stringResource){
        Espresso.onView(ViewMatchers.withText(stringResource))
                .inRoot(RootMatchers.withDecorView(Matchers.not(mainActivityRule.getActivity().getWindow().getDecorView())))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
