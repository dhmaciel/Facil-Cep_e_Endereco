package com.example.worlo.cep_mvp.main.background_task.background_result;

import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.util.ArrayList;

/**
 * Created by worlo on 03/06/2017.
 */

public interface PesquisaEnderecoDelegate {
    public void taskSuccessPesquisaEndereco(ArrayList<ResultadoPesquisa> resultadoPesquisa);
}
