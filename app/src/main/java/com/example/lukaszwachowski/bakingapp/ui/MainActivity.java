package com.example.lukaszwachowski.bakingapp.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    ListAdapter listAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.main_layout)
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

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void updateData(Recipe recipe) {
        listAdapter.swapData(recipe);
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
