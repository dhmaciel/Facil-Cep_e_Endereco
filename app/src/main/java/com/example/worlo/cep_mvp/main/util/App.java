package com.example.worlo.cep_mvp.main.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by worlo on 07/05/2017.
 */

public class App extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}