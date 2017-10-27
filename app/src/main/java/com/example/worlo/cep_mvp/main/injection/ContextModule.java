package com.example.worlo.cep_mvp.main.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by worlo on 15/05/2017.
 */

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context providesContext(){
        return context;
    }

}
