package com.example.lukaszwachowski.bakingapp.ui.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.example.lukaszwachowski.bakingapp.BakingApp;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.di.components.DaggerMainActivityComponent;
import com.example.lukaszwachowski.bakingapp.di.modules.MainActivityModule;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

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

        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .applicationComponent(BakingApp.get(this).component())
                .build().inject(this);

        presenter.attachView(this);
        presenter.loadData();

        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns()));
        recyclerView.setAdapter(recipeAdapter);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 650;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 1) return 1;
        return nColumns;
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
}
