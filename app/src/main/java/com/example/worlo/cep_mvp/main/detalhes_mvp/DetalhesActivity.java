package com.example.worlo.cep_mvp.main.detalhes_mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.adapter.DetalhesAdapter;
import com.example.worlo.cep_mvp.main.model.ResultadoPesquisa;
import com.example.worlo.cep_mvp.main.util.StateMaintainer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesActivity extends AppCompatActivity implements DetalhesMVP.RequiredViewOps {

    protected final String TAG = getClass().getSimpleName();
    private DetalhesMVP.PresenterOps detalhesPresenter;

    // Responsável por manter estado dos objetos inscritos
    // durante mudanças de configuração
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer( this.getFragmentManager(), TAG );

    @BindView(R.id.recycler_view_detalhes)
    RecyclerView recyclerViewDetalhes;

    private DetalhesAdapter detalhesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        startMVPOps();

        detalhesPresenter.inicializarActivity(getIntent().getExtras());
    }

    /**
     * Inicia e reinicia o Presenter. Este método precisa ser chamado
     * após {@link android.app.Activity#onCreate(Bundle)}
     */
    public void startMVPOps() {
        try {
            if ( mStateMaintainer.firstTimeIn() ) {
                Log.d(TAG, "onCreate() chamado pela primera vez");
                initialize(this);
            } else {
                Log.d(TAG, "onCreate() chamado mais de uma vez");
                reinitialize(this);
            }
        } catch ( InstantiationException e) {
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
    private void initialize( DetalhesMVP.RequiredViewOps view )
            throws InstantiationException, IllegalAccessException{
        detalhesPresenter = new DetalhesPresenter(view);
        mStateMaintainer.put(DetalhesMVP.PresenterOps.class.getSimpleName(), detalhesPresenter);
    }

    /**
     * Recupera o presenter e informa à instância que houve uma mudança
     * de configuração no View.
     * Caso o presenter tenha sido perdido, uma nova instância é criada
     */
    private void reinitialize( DetalhesMVP.RequiredViewOps view)
            throws InstantiationException, IllegalAccessException {
        detalhesPresenter = mStateMaintainer.get( DetalhesMVP.PresenterOps.class.getSimpleName() );

        if ( detalhesPresenter == null ) {
            Log.w(TAG, "recriando o Presenter");
            initialize( view );
        } else {
            detalhesPresenter.onConfigurationChanged(view);
        }
    }

    @Override
    public void setupRecycler(ArrayList<ResultadoPesquisa> resultadoPesquisaList){
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDetalhes.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        detalhesAdapter = new DetalhesAdapter(resultadoPesquisaList);
        recyclerViewDetalhes.setAdapter(detalhesAdapter);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        recyclerViewDetalhes.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void showToast(int resourceMsg) {
        Toast.makeText(this, getString(resourceMsg), Toast.LENGTH_LONG).show();
    }
}
