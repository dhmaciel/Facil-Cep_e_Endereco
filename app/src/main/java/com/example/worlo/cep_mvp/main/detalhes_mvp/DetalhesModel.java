package com.example.worlo.cep_mvp.main.detalhes_mvp;

/**
 * Created by worlo on 20/05/2017.
 */

public class DetalhesModel implements DetalhesMVP.ModelOps {

    private DetalhesMVP.RequiredPresenterOps mPresenter;

    public DetalhesModel(DetalhesMVP.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
    }
}
