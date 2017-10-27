package com.example.worlo.cep_mvp.main.main_mvp;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.model.PesquisaEndereco;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by worlo on 01/04/2017.
 */

public class MainPresenter implements MainMVP.RequiredPresenterOps, MainMVP.PresenterOps {

    // Referência para layer View
    private WeakReference<MainMVP.RequiredViewOps> mView;
    // Referência para o layer Model
    private MainMVP.ModelOps mModel;

    // Estado da mudança de configuração
    private boolean mIsChangingConfig;

    private final int QTD_CARACTERES_CEP = 8;
    private final int QTD_MINIMO_CARACTERE_ENDERECO = 3;

    public MainPresenter(MainMVP.RequiredViewOps mView) {
        this.mView = new WeakReference<>(mView);
        this.mModel = new MainModel(this);
    }

    /**
     * Disparado por Activity após mudança de configuração
     * @param view  Referência para View
     */
    @Override
    public void onConfigurationChanged(MainMVP.RequiredViewOps view) {
        mView = new WeakReference<>(view);
    }

    /**
     * Recebe evento {@link MainActivity#onDestroy()}
     * @param isChangingConfig  Se está mudando de config
     */
    @Override
    public void onDestroy(boolean isChangingConfig) {
        mView = null;
        mIsChangingConfig = isChangingConfig;
        if(!isChangingConfig){
            mModel.onDestroy();
        }
    }

    @Override
    public void realizarPesquisaCep(String cep) {
        if(cep.length() < QTD_CARACTERES_CEP){
            mView.get().showToast(R.string.toast_erro_qtd_digitos_invalido_cep);
        }else{
            mModel.iniciarPesquisaPorCep(cep);
        }
    }

    @Override
    public void realizarPesquisaEndereco(String uf, String cidade, String logradouro) {
        if(uf.equals("") || cidade.equals("") || logradouro.equals("")){
            mView.get().showToast(R.string.toast_campos_obrigatorios);
        }
        else if(cidade.length() < QTD_MINIMO_CARACTERE_ENDERECO || logradouro.length() < QTD_MINIMO_CARACTERE_ENDERECO ){
            mView.get().showToast(R.string.toast_erro_qtd_digitos_invalido_endereco);
        }else{
            PesquisaEndereco pesquisaEndereco = new PesquisaEndereco(uf, cidade, logradouro);
            mModel.iniciarPesquisaPorEndereco(pesquisaEndereco);
        }
    }

    @Override
    public void onResultRealizarPesquisa(ArrayList<ResultadoPesquisa> resultadoPesquisaList) {
        if(resultadoPesquisaList != null && resultadoPesquisaList.size() > 0 &&
                (resultadoPesquisaList.get(0) != null &&
                resultadoPesquisaList.get(0).isResultadoPesquisaValido())){

            mView.get().chamarDetalhesActivity(resultadoPesquisaList);
        }else{
            mView.get().showToast(R.string.label_main_erro_pesquisa);
        }
    }

    /**
     * Recebe eventuais error de modelo,
     * e repassa mensagem para usuário
     */
    @Override
    public void onError(String msg) {
        mView.get().showAlert(msg);
    }
}
