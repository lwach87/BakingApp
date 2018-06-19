package com.example.lukaszwachowski.bakingapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment.RecipeStepFragment;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lukaszwachowski.bakingapp.configuration.NetworkUtils.RECIPE_OBJECT;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent myIntent(Context context, Recipe recipe) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(RECIPE_OBJECT, recipe);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        toolbar.setTitle(getTitle());

        Recipe recipe = getIntent().getExtras().getParcelable(RECIPE_OBJECT);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, RecipeStepFragment.newInstance(recipe)).commit();
    }
}
