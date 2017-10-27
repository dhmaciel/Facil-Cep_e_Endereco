package com.example.worlo.cep_mvp.main.endereco_mvp;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.model.PesquisaEndereco;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by worlo on 02/10/2017.
 */

public class EnderecoPresenter implements EnderecoMVP.PresenterOps, EnderecoMVP.RequiredPresenterOps {

    // Referência para layer View
    private WeakReference<EnderecoMVP.RequiredViewOps> enderecoView;
    // Referência para o layer Model
    private EnderecoMVP.ModelOps enderecoModel;

    private final int QTD_MINIMO_CARACTERE_ENDERECO = 3;

    public EnderecoPresenter(EnderecoMVP.RequiredViewOps mView) {
        this.enderecoView = new WeakReference<>(mView);
        this.enderecoModel = new EnderecoModel(this);
    }

    @Override
    public void onConfigurationChanged(EnderecoMVP.RequiredViewOps view) {
        enderecoView = new WeakReference<>(view);
    }

    @Override
    public void realizarPesquisaEndereco(String uf, String cidade, String logradouro) {
        if(uf.equals("") || cidade.equals("") || logradouro.equals("")){
            enderecoView.get().showToast(R.string.toast_campos_obrigatorios);
        }
        else if(cidade.length() < QTD_MINIMO_CARACTERE_ENDERECO || logradouro.length() < QTD_MINIMO_CARACTERE_ENDERECO ){
            enderecoView.get().showToast(R.string.toast_erro_qtd_digitos_invalido_endereco);
        }else{
            PesquisaEndereco pesquisaEndereco = new PesquisaEndereco(uf, cidade, logradouro);
            enderecoModel.iniciarPesquisaPorEndereco(pesquisaEndereco);
        }
    }

    @Override
    public void onResultRealizarPesquisa(ArrayList<ResultadoPesquisa> resultadoPesquisaList) {
        if(resultadoPesquisaList != null && resultadoPesquisaList.size() > 0 &&
                (resultadoPesquisaList.get(0) != null &&
                        resultadoPesquisaList.get(0).isResultadoPesquisaValido())){

            enderecoView.get().chamarDetalhesActivity(resultadoPesquisaList);
        }else{
            enderecoView.get().showToast(R.string.label_main_erro_pesquisa);
        }
    }

    @Override
    public void onError(String msg) {
        enderecoView.get().showAlert(msg);
    }
}
