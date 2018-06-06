package com.example.lukaszwachowski.bakingapp;

import android.app.Activity;
import android.app.Application;

import com.example.lukaszwachowski.bakingapp.di.components.ApplicationComponent;
import com.example.lukaszwachowski.bakingapp.di.components.DaggerApplicationComponent;
import com.example.lukaszwachowski.bakingapp.di.modules.ContextModule;

public class BakingApp extends Application {

    private ApplicationComponent component;

    public static BakingApp get(Activity activity) {
        return (BakingApp) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public ApplicationComponent component() {
        return component;
    }
}
