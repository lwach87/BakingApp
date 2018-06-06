package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.di.MainActivityScope;
import com.example.lukaszwachowski.bakingapp.ui.ListAdapter;
import com.example.lukaszwachowski.bakingapp.ui.MainActivity;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public ListAdapter listAdapter(Picasso picasso) {
        return new ListAdapter(mainActivity, picasso);
    }
}
