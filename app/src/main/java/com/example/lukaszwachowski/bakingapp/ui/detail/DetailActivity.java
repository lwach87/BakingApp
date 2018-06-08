package com.example.lukaszwachowski.bakingapp.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import static com.example.lukaszwachowski.bakingapp.configuration.NetworkUtils.RECIPE_OBJECT;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Recipe recipe = getIntent().getExtras().getParcelable(RECIPE_OBJECT);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, RecipeStepFragment.newInstance(recipe)).commit();
    }
}
