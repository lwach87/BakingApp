package com.example.lukaszwachowski.bakingapp.ui.movie;

import android.os.Bundle;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Step;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

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

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        AndroidInjection.inject(this);

        Step[] steps = MovieActivityArgs.fromBundle(getIntent().getExtras()).getSteps();
        int position = MovieActivityArgs.fromBundle(getIntent().getExtras()).getPosition();

        pagerAdapter.swapData(steps);
        moviePager.setAdapter(pagerAdapter);
        moviePager.setCurrentItem(position);
        tabLayout.setupWithViewPager(moviePager);
    }
}
