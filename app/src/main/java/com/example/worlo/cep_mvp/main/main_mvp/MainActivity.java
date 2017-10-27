package com.example.worlo.cep_mvp.main.main_mvp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.worlo.cep_mvp.R;
import com.example.worlo.cep_mvp.main.adapter.ViewPagerAdapter;
import com.example.worlo.cep_mvp.main.cep_mvp.CepFragment;
import com.example.worlo.cep_mvp.main.endereco_mvp.EnderecoFragment;
import com.example.worlo.cep_mvp.main.injection.ProvidesComponent;
import com.example.worlo.cep_mvp.main.util.StateMaintainer;

import butterknife.BindView;
import butterknife.ButterKnife;

//http://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
public class MainActivity extends AppCompatActivity {// implements MainMVP.RequiredViewOps{

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.tabs)
    public TabLayout tabLayout;

    @BindView(R.id.viewpager)
    public ViewPager viewPager;

    protected final String TAG = getClass().getSimpleName();

    private ViewPagerAdapter adapter;

    // Responsável por manter estado dos objetos inscritos
    // durante mudanças de configuração
    private final StateMaintainer mStateMaintainer =
            new StateMaintainer( this.getFragmentManager(), TAG );

    //http://stackoverflow.com/questions/39100105/need-context-in-model-in-mvp
    // De acordo com esse artigo quem deve criar o Model juntamente com o context é a activity

    // Operações no Presenter
    private MainMVP.PresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProvidesComponent.initDagger(this);
        //startMVPOps();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Inicia e reinicia o Presenter. Este método precisa ser chamado
     * após {@link android.app.Activity#onCreate(Bundle)}
     */
/*    public void startMVPOps() {
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
    }*/

    /**
     * Inicializa os objetos relevantes para o MVP.
     * Cria uma instância do Presenter, salva o presenter
     * no {@link StateMaintainer} e informa à instância do
     * presenter que objeto foi criado.
     * @param view      Operações no View exigidas pelo Presenter
     */
    private void initialize( MainMVP.RequiredViewOps view )
            throws InstantiationException, IllegalAccessException{
        mPresenter = new MainPresenter(view);
        mStateMaintainer.put(MainMVP.PresenterOps.class.getSimpleName(), mPresenter);
    }

    /**
     * Recupera o presenter e informa à instância que houve uma mudança
     * de configuração no View.
     * Caso o presenter tenha sido perdido, uma nova instância é criada
     */
    private void reinitialize( MainMVP.RequiredViewOps view)
            throws InstantiationException, IllegalAccessException {
        mPresenter = mStateMaintainer.get( MainMVP.PresenterOps.class.getSimpleName() );

        if ( mPresenter == null ) {
            Log.w(TAG, "recriando o Presenter");
            initialize( view );
        } else {
            mPresenter.onConfigurationChanged( view );
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CepFragment(), getString(R.string.fragment_title_cep));
        adapter.addFragment(new EnderecoFragment(), getString(R.string.fragment_title_endereco));
        viewPager.setAdapter(adapter);
    }


   /* @OnClick(R.id.float_btn_pesquisar)
    public void onFloatBtnPesquisarClicked() {
        int currentItem = viewPager.getCurrentItem();
        Fragment visibleFragment = adapter.getItem(currentItem);

        if (visibleFragment instanceof CepFragment) {
            CepFragment cepFragment = (CepFragment) visibleFragment;
            mPresenter.realizarPesquisaCep(cepFragment.edtNumCep.getText().toString());
        } else if (visibleFragment instanceof EnderecoFragment) {
            EnderecoFragment enderecoFragment = (EnderecoFragment) visibleFragment;
            mPresenter.realizarPesquisaEndereco(enderecoFragment.ufSelecionado,
                    enderecoFragment.edtCidade.getText().toString(),
                    enderecoFragment.edtLogradouro.getText().toString());
        }
    }*/

       /* if(radioCep.isChecked()){
            mPresenter.realizarPesquisaCep(edtNumCep.getText().toString());
        }else{
            mPresenter.realizarPesquisaEndereco(ufSelecionado, edtCidade.getText().toString(),
                    edtLogradouro.getText().toString());
        }*/

/*    public void chamarDetalhesActivity(ArrayList<ResultadoPesquisa> resultadoPesquisaList){
        Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
        intent.putExtra(ResultadoPesquisa.NAME_RESULTADO_PESQUISA, resultadoPesquisaList);
        startActivity(intent);
    }

    @Override
    public void showToast(int resourceMsg) {
        Toast.makeText(this, getString(resourceMsg), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAlert(String msg) {

    }*/
}
