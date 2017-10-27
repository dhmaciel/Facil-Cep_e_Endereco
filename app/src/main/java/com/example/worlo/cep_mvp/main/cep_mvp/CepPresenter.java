package com.example.worlo.cep_mvp.main.cep_mvp;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.util.Mask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by worlo on 30/09/2017.
 */

public class CepPresenter implements CepMVP.PresenterOps, CepMVP.RequiredPresenterOps {

    // Referência para layer View
    private WeakReference<CepMVP.RequiredViewOps> cepView;
    // Referência para o layer Model
    private CepMVP.ModelOps cepModel;

    // Estado da mudança de configuração
    private boolean mIsChangingConfig;

    private final int QTD_CARACTERES_CEP = 8;

    public CepPresenter(CepMVP.RequiredViewOps cepView) {
        this.cepView = new WeakReference<>(cepView);
        this.cepModel = new CepModel(this);
    }

    /**
     * Disparado por Activity após mudança de configuração
     * @param view  Referência para View
     */
    @Override
    public void onConfigurationChanged(CepMVP.RequiredViewOps view) {
        cepView = new WeakReference<>(view);
    }

    @Override
    public void realizarPesquisaCep(String cep) {
        if(Mask.unmask(cep).length() < QTD_CARACTERES_CEP){
            cepView.get().showToast(R.string.toast_erro_qtd_digitos_invalido_cep);
        }else{
            cepModel.iniciarPesquisaPorCep(cep);
        }
    }

    @Override
    public void onResultRealizarPesquisa(ArrayList<ResultadoPesquisa> resultadoPesquisaList) {
        if(resultadoPesquisaList != null && resultadoPesquisaList.size() > 0 &&
                (resultadoPesquisaList.get(0) != null &&
                        resultadoPesquisaList.get(0).isResultadoPesquisaValido())){

            cepView.get().chamarDetalhesActivity(resultadoPesquisaList);
        }else{
            cepView.get().showToast(R.string.label_main_erro_pesquisa);
        }
    }

    @Override
    public void onError(String msg) {
        cepView.get().showAlert(msg);
    }
}
