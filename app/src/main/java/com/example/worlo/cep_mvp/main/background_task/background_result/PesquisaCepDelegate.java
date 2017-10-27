package com.example.worlo.cep_mvp.main.background_task.background_result;

import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

/**
 * Created by worlo on 19/05/2017.
 */

public interface PesquisaCepDelegate {
    public void taskSuccessPesquisaCep(ResultadoPesquisa resultadoPesquisa);
}
