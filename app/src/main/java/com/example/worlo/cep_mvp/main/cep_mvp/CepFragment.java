package com.example.worlo.cep_mvp.main.cep_mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.detalhes_mvp.DetalhesActivity;
import com.example.worlo.cep_mvp.main.main_mvp.MainMVP;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.util.Mask;
import com.example.worlo.cep_mvp.main.util.StateMaintainer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CepFragment extends Fragment implements CepMVP.RequiredViewOps {

    @BindView(R.id.edt_num_cep)
    public EditText edtNumCep;

    // Operações no Presenter
    private CepMVP.PresenterOps presenterOps;

    protected final String TAG = getClass().getSimpleName();

    // Responsável por manter estado dos objetos inscritos
    // durante mudanças de configuração
    private StateMaintainer stateMaintainer = null;

    public CepFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cep, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtNumCep.addTextChangedListener(Mask.insert("#####-###", edtNumCep));
    }

    /**
     * Inicia e reinicia o Presenter. Este método precisa ser chamado
     * após {@link android.app.Activity#onCreate(Bundle)}
     */
    public void startMVPOps() {
        try {
            if ( stateMaintainer.firstTimeIn() ) {
                Log.d(TAG, "onCreate() chamado pela primera vez");
                initialize(this);
            } else {
                Log.d(TAG, "onCreate() chamado mais de uma vez");
                reinitialize(this);
            }
        } catch ( java.lang.InstantiationException e) {
            Log.d(TAG, "onCreate() " + e );
            throw new RuntimeException( e );
        } catch (IllegalAccessException e){
            Log.d(TAG, "onCreate() " + e );
            throw new RuntimeException( e );
        }
    }

    /**
     * Inicializa os objetos relevantes para o MVP.
     * Cria uma instância do Presenter, salva o presenter
     * no {@link StateMaintainer} e informa à instância do
     * presenter que objeto foi criado.
     * @param view      Operações no View exigidas pelo Presenter
     */
    private void initialize( CepMVP.RequiredViewOps view )
            throws java.lang.InstantiationException, IllegalAccessException{
        presenterOps = new CepPresenter(view);
        stateMaintainer.put(MainMVP.PresenterOps.class.getSimpleName(), presenterOps);
    }

    /**
     * Recupera o presenter e informa à instância que houve uma mudança
     * de configuração no View.
     * Caso o presenter tenha sido perdido, uma nova instância é criada
     */
    private void reinitialize( CepMVP.RequiredViewOps view)
            throws java.lang.InstantiationException, IllegalAccessException {
        presenterOps = stateMaintainer.get( MainMVP.PresenterOps.class.getSimpleName() );

        if ( presenterOps == null ) {
            Log.w(TAG, "recriando o Presenter");
            initialize(view);
        } else {
            presenterOps.onConfigurationChanged( view );
        }
    }

    @OnClick(R.id.btn_cep_pesquisar)
    public void onClickPesquisar(){
        presenterOps.realizarPesquisaCep(edtNumCep.getText().toString());
    }

    @OnClick(R.id.txt_cep_limpar)
    public void onClickLimpar(){
        edtNumCep.setText("");
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        stateMaintainer =
                new StateMaintainer(getActivity().getFragmentManager(), TAG );
    }
}
