package com.example.worlo.cep_mvp.Utils;

import android.content.Context;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.view.View;
import android.widget.Toast;

/**
 * Created by worlo on 03/09/2017.
 * https://stackoverflow.com/questions/31998205/is-it-possible-to-disable-toasts-or-wait-until-toast-disappears-while-testing
 */

public class ToastManagerIdlingResource {
    private static final CountingIdlingResource idlingResource = new CountingIdlingResource("toast");
    private static final View.OnAttachStateChangeListener listener = new View.OnAttachStateChangeListener() {
        @Override
        public void onViewAttachedToWindow(final View v) {
            idlingResource.increment();
        }

        @Override
        public void onViewDetachedFromWindow(final View v) {
            idlingResource.decrement();
        }
    };

    private ToastManagerIdlingResource() { }

    public static Toast makeText(final Context context, final CharSequence text, final int duration) {
        Toast t = Toast.makeText(context, text, duration);
        t.getView().addOnAttachStateChangeListener(listener);
        return t;
    }

    // For testing
    public static IdlingResource getIdlingResource() {
        return idlingResource;
    }
}
