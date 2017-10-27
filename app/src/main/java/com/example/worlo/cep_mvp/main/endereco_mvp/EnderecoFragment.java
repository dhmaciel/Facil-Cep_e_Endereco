package com.example.worlo.cep_mvp.main.endereco_mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.detalhes_mvp.DetalhesActivity;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class EnderecoFragment extends Fragment implements EnderecoMVP.RequiredViewOps{

    @BindView(R.id.spinner_estados)
    public Spinner spinnerEstados;

    @BindView(R.id.edt_cidade)
    public EditText edtCidade;

    @BindView(R.id.edt_logradouro)
    public EditText edtLogradouro;

    public String ufSelecionado;

    // Operações no Presenter
    private EnderecoMVP.PresenterOps presenterOps;

    public EnderecoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMVPOps();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_endereco, container, false);
        ButterKnife.bind(this, view);

        loadSpinnerEstados();
        // Inflate the layout for this fragment
        return view;
    }

    public void startMVPOps() {
        presenterOps = new EnderecoPresenter(this);
    }

    private void loadSpinnerEstados() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.estados, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerEstados.setAdapter(adapter);
    }

    @OnItemSelected(R.id.spinner_estados)
    public void spinnerItemSelected(Spinner spinner, int position) {
        ufSelecionado = getResources().getStringArray(R.array.estados_sigla)[position];
    }

    @OnClick(R.id.txt_endereco_limpar)
    public void onClickLimpar(){
        spinnerEstados.setSelection(0);
        edtCidade.setText("");
        edtLogradouro.setText("");
        ufSelecionado = "";
    }

    @OnClick(R.id.btn_endereco_pesquisar)
    public void onClickPesquisar(){
        presenterOps.realizarPesquisaEndereco(ufSelecionado,
                edtCidade.getText().toString(),
                edtLogradouro.getText().toString());
    }

    @Override
    public void showToast(int resourceMsg) {
        Toast.makeText(getContext(), getString(resourceMsg), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAlert(String msg) {

    }

    @Override
    public void chamarDetalhesActivity(ArrayList<ResultadoPesquisa> resultadoPesquisaList) {
        Intent intent = new Intent(getActivity(), DetalhesActivity.class);
        intent.putExtra(ResultadoPesquisa.NAME_RESULTADO_PESQUISA, resultadoPesquisaList);
        startActivity(intent);
    }
}
