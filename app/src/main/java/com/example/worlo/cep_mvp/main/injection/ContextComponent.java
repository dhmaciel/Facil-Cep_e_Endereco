package com.example.worlo.cep_mvp.main.injection;

import android.content.Context;

import com.example.worlo.cep_mvp.main.cep_mvp.CepModel;
import com.example.worlo.cep_mvp.main.endereco_mvp.EnderecoModel;
import com.example.worlo.cep_mvp.main.main_mvp.MainModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by worlo on 16/05/2017.
 */

@Component(modules = {ContextModule.class})
@Singleton
public interface ContextComponent{
    Context context();

    void inject(MainModel mainModel);
    void inject(CepModel cepModel);
    void inject(EnderecoModel enderecoModel);
}