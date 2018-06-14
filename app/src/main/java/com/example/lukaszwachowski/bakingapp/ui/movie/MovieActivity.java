package com.example.lukaszwachowski.bakingapp.ui.movie;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lukaszwachowski.bakingapp.BakingApp;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.di.components.DaggerMovieActivityComponent;
import com.example.lukaszwachowski.bakingapp.di.modules.MovieActivityModule;
import com.example.lukaszwachowski.bakingapp.network.model.Step;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lukaszwachowski.bakingapp.configuration.NetworkUtils.POSITION;
import static com.example.lukaszwachowski.bakingapp.configuration.NetworkUtils.STEPS_LIST;

public class MovieActivity extends AppCompatActivity {

    @BindView(R.id.movie_pager)
    ViewPager moviePager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        DaggerMovieActivityComponent.builder()
                .movieActivityModule(new MovieActivityModule(this))
                .applicationComponent(BakingApp.get(this).component())
                .build().inject(this);

        ArrayList<Step> steps = getIntent().getExtras().getParcelableArrayList(STEPS_LIST);
        int position = getIntent().getExtras().getInt(POSITION);

        pagerAdapter.swapData(steps);
        moviePager.setAdapter(pagerAdapter);
        moviePager.setCurrentItem(position);
        tabLayout.setupWithViewPager(moviePager);
    }
}
