package com.example.lukaszwachowski.bakingapp.ui.main;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;
import com.example.lukaszwachowski.bakingapp.ui.detail.DetailActivity;
import com.example.lukaszwachowski.bakingapp.widget.WidgetService;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View,
    RecipeAdapter.OnItemClickListener {

  @Inject
  MainActivityMVP.Presenter presenter;

  @Inject
  RecipeAdapter recipeAdapter;

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  @BindView(R.id.main_activity)
  ViewGroup layout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    AndroidInjection.inject(this);

    presenter.attachView(this);
    presenter.loadData();
    recipeAdapter.setListener(this);

    recyclerView.setLayoutManager(new GridLayoutManager(this, presenter.numberOfColumns(250)));
    recyclerView.setAdapter(recipeAdapter);
  }

  @Override
  public void updateData(Recipe recipe) {
    recipeAdapter.swapData(recipe);
  }

  @Override
  public void showSnackBar(String text) {
    Snackbar.make(layout, text, Snackbar.LENGTH_SHORT).show();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.detachView();
  }

  @Override
  public void onItemClick(Recipe recipe) {
    WidgetService.startActionUpdateRecipeWidgets(this, recipe);
    startActivity(DetailActivity.myIntent(this, recipe));
  }
}
