package com.udacity.gradle.builditbigger.IdlingResource;

import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

import android.support.test.espresso.IdlingResource;

public class SimpleIdlingResource implements IdlingResource {

    @Nullable
    private volatile ResourceCallback mCallBack;
    private final AtomicBoolean mIsIdleNow = new AtomicBoolean(false);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallBack = callback;
    }

    public void setIdleState(boolean state) {
        mIsIdleNow.set(state);
        if (isIdleNow() && mCallBack != null) {
            mCallBack.onTransitionToIdle();
        }
    }
}
