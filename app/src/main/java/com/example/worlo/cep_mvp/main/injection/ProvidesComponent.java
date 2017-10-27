package com.example.worlo.cep_mvp.main.injection;

import android.content.Context;

/**
 * Created by worlo on 16/05/2017.
 */

public class ProvidesComponent {
    private static ContextComponent contextComponent;

    public static void initDagger(Context context) {

        contextComponent = DaggerContextComponent.builder()
                .contextModule(new ContextModule(context))
                .build();
     }

    public static ContextComponent getContextComponent(){
        return contextComponent;
    }
}
