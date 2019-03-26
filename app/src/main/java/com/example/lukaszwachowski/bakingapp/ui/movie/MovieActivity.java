package com.example.lukaszwachowski.bakingapp.ui.movie;

import static com.example.lukaszwachowski.bakingapp.configuration.Constants.POSITION;
import static com.example.lukaszwachowski.bakingapp.configuration.Constants.STEPS_LIST;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Step;
import dagger.android.AndroidInjection;
import java.util.ArrayList;
import javax.inject.Inject;

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
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    AndroidInjection.inject(this);

    ArrayList<Step> steps = getIntent().getExtras().getParcelableArrayList(STEPS_LIST);
    int position = getIntent().getExtras().getInt(POSITION);

    pagerAdapter.swapData(steps);
    moviePager.setAdapter(pagerAdapter);
    moviePager.setCurrentItem(position);
    tabLayout.setupWithViewPager(moviePager);
  }
}
