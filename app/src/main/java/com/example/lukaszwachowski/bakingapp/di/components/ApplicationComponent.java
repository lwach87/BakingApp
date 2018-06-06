package com.example.lukaszwachowski.bakingapp.di.components;

import com.example.lukaszwachowski.bakingapp.di.ApplicationScope;
import com.example.lukaszwachowski.bakingapp.di.modules.ContextModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class})
public interface ApplicationComponent {
    Picasso getPicasso();
}