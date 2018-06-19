package com.example.lukaszwachowski.bakingapp.ui.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.example.lukaszwachowski.bakingapp.BakingApp;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.di.components.DaggerMainActivityComponent;
import com.example.lukaszwachowski.bakingapp.di.modules.MainActivityModule;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;
import com.example.lukaszwachowski.bakingapp.ui.detail.DetailActivity;
import com.example.lukaszwachowski.bakingapp.widget.WidgetService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View, RecipeAdapter.OnItemClickListener {

    @Inject
    MainActivityMVP.Presenter presenter;

    @Inject
    RecipeAdapter recipeAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.main_activity)
    ViewGroup layout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .applicationComponent(BakingApp.get(this).component())
                .build().inject(this);

        toolbar.setTitle(getTitle());
        presenter.attachView(this);
        presenter.loadData();
        recipeAdapter.setListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, presenter.numberOfColumns(650)));
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
