package com.example.worlo.cep_mvp.main.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.worlo.cep_mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by worlo on 23/05/2017.
 */

public class LineHolderDetalhes extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_cep_detalhes)
    public TextView textViewCep;

    @BindView(R.id.txt_logradouro_detalhes)
    public TextView textViewLogradouro;

    @BindView(R.id.txt_complemento_detalhes)
    public TextView textViewComplemento;

    @BindView(R.id.txt_bairro_detalhes)
    public TextView textViewBairro;

    @BindView(R.id.txt_cidade_detalhes)
    public TextView textViewCidade;

    @BindView(R.id.txt_uf_detalhes)
    public TextView textViewUf;

    public LineHolderDetalhes(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
