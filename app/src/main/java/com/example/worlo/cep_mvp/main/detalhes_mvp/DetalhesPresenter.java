package com.example.worlo.cep_mvp.main.detalhes_mvp;

import android.os.Bundle;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.main_mvp.MainMVP;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by worlo on 20/05/2017.
 */

public class DetalhesPresenter implements DetalhesMVP.RequiredPresenterOps, DetalhesMVP.PresenterOps {

    // Referência para layer View
    private WeakReference<DetalhesMVP.RequiredViewOps> mView;
    // Referência para o layer Model
    private DetalhesMVP.ModelOps mModel;

    // Estado da mudança de configuração
    private boolean mIsChangingConfig;
    private ArrayList<ResultadoPesquisa> resultadoPesquisaList;

    public DetalhesPresenter(DetalhesMVP.RequiredViewOps mView) {
        this.mView = new WeakReference<>(mView);
        this.mModel = new DetalhesModel(this);
    }

    @Override
    public void onDestroy(boolean isChangingConfig) {

    }

    @Override
    public void onConfigurationChanged(DetalhesMVP.RequiredViewOps view) {

    }

    @Override
    public void inicializarActivity(Bundle bundle) {
        if(bundle != null){
            resultadoPesquisaList = (ArrayList<ResultadoPesquisa>) bundle.get(ResultadoPesquisa.NAME_RESULTADO_PESQUISA);
            if(isListValida()){
                mView.get().setupRecycler(resultadoPesquisaList);
            }else{
                mView.get().showToast(R.string.detalhes_sem_dados);
            }
        }
    }

    private boolean isListValida(){
        if(resultadoPesquisaList != null && !resultadoPesquisaList.isEmpty()){
            if(resultadoPesquisaList.size() == 1 && resultadoPesquisaList.iterator().next() == null ){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
}
