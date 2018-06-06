package com.example.lukaszwachowski.bakingapp.di.components;

import com.example.lukaszwachowski.bakingapp.data.database.Repository;
import com.example.lukaszwachowski.bakingapp.di.ApplicationScope;
import com.example.lukaszwachowski.bakingapp.di.modules.ContextModule;
import com.example.lukaszwachowski.bakingapp.di.modules.DatabaseModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, DatabaseModule.class})
public interface ApplicationComponent {
    Picasso getPicasso();

    Repository getRepository();
}