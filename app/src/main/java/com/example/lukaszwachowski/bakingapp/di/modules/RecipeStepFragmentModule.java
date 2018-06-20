package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.di.RecipeStepFragmentScope;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment.IngredientsAdapter;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment.StepsAdapter;
import com.example.lukaszwachowski.bakingapp.ui.detail.DetailActivity;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class RecipeStepFragmentModule {

    private DetailActivity detailActivity;

    public RecipeStepFragmentModule(DetailActivity detailActivity) {
        this.detailActivity = detailActivity;
    }

    @Provides
    @RecipeStepFragmentScope
    public DetailActivity activity() {
        return detailActivity;
    }

    @Provides
    @RecipeStepFragmentScope
    public IngredientsAdapter ingredientsAdapter() {
        return new IngredientsAdapter();
    }

    @Provides
    @RecipeStepFragmentScope
    public StepsAdapter stepsAdapter(Picasso picasso) {
        return new StepsAdapter(detailActivity, picasso);
    }
}
