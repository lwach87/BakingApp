package com.example.lukaszwachowski.bakingapp.ui.detail;

import static com.example.lukaszwachowski.bakingapp.configuration.Constants.RECIPE_OBJECT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.fragments.RecipeStepFragment.RecipeStepFragment;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity implements HasSupportFragmentInjector {

  //DaggerAppCompatActivity

  @Inject
  DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

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
    setSupportActionBar(toolbar);

    AndroidInjection.inject(this);

    toolbar.setTitle(getTitle());
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Recipe recipe = getIntent().getExtras().getParcelable(RECIPE_OBJECT);

    getSupportFragmentManager().beginTransaction()
        .add(R.id.fragment_container, RecipeStepFragment.newInstance(recipe)).commit();
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentDispatchingAndroidInjector;
  }
}
