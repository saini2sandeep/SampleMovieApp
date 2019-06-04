package com.example.samplemovieapp.utils.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

/**
 * Created by sandeepsaini on 28,May,2019
 * <p>
 * A SingleLiveEvent used for Toast messages. Like a {@link SingleLiveEvent} but also prevents
 * null messages and uses a custom observer.
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
public class ToastMessageLiveEvent extends SingleLiveEvent<String> {

    public void observe(LifecycleOwner owner, final ToastMessageObserver toastMessageObserver) {
        super.observe(owner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s == null) {
                    return;
                }
                toastMessageObserver.onNewMessage(s);
            }
        });
    }

    public interface ToastMessageObserver {

        /**
         * Called when there is a new message to be shown.
         *
         * @param toastMessage The new message, non-null.
         */

        void onNewMessage(String toastMessage);
    }
}
