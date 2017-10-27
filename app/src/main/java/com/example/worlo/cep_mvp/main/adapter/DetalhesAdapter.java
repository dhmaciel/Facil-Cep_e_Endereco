package com.example.worlo.cep_mvp.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.adapter.viewholder.LineHolderDetalhes;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by worlo on 23/05/2017.
 */

public class DetalhesAdapter extends RecyclerView.Adapter<LineHolderDetalhes> {

    public final List<ResultadoPesquisa> resultadoPesquisaList;

    public DetalhesAdapter(ArrayList<ResultadoPesquisa> resultadoPesquisaList) {
        this.resultadoPesquisaList = resultadoPesquisaList;
    }

    @Override
    public LineHolderDetalhes onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolderDetalhes(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detalhes_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolderDetalhes holder, int position) {
        setTextViewValue(holder.textViewCep, resultadoPesquisaList.get(position).getCep());
        setTextViewValue(holder.textViewLogradouro, resultadoPesquisaList.get(position).getLogradouro());
        setTextViewValue(holder.textViewComplemento, resultadoPesquisaList.get(position).getComplemento());
        setTextViewValue(holder.textViewBairro, resultadoPesquisaList.get(position).getBairro());
        setTextViewValue(holder.textViewCidade, resultadoPesquisaList.get(position).getLocalidade());
        setTextViewValue(holder.textViewUf, resultadoPesquisaList.get(position).getUf());
    }

    private void setTextViewValue(TextView field, String value){
        if(value != null && !value.equals("")){
            field.setText(value);
            field.setVisibility(View.VISIBLE);
        } else {
            field.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return resultadoPesquisaList != null ? resultadoPesquisaList.size() : 0;
    }
}
