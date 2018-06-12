package com.example.lukaszwachowski.bakingapp.fragments.MovieDetailsFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.lukaszwachowski.bakingapp.configuration.NetworkUtils.POSITION;
import static com.example.lukaszwachowski.bakingapp.configuration.NetworkUtils.RECIPE_OBJECT;

public class MovieDetailsFragment extends Fragment {

    @BindView(R.id.movie_url)
    TextView movieUrl;

    private Recipe recipe;
    private int position;

    public static MovieDetailsFragment newInstance(Recipe recipe, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_OBJECT, recipe);
        bundle.putInt(POSITION, position);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipe = getArguments().getParcelable(RECIPE_OBJECT);
        position = getArguments().getInt(POSITION);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, view);

        movieUrl.setText(recipe.steps.get(position).description);

        return view;
    }
}
