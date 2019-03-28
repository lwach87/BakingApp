package com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment;


import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;
import com.example.lukaszwachowski.bakingapp.widget.WidgetService;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class RecipeListFragment extends Fragment implements RecipeMVP.View, RecipeAdapter.OnItemClickListener {

    @Inject
    RecipeAdapter recipeAdapter;

    @Inject
    RecipePresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public RecipeListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidSupportInjection.inject(this);

        presenter.attachView(this);
        presenter.loadData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipie_list, container, false);
        ButterKnife.bind(this, view);

        recipeAdapter.setListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns(250)));
        recyclerView.setAdapter(recipeAdapter);

        return view;
    }

    @Override
    public void updateData(Recipe recipe) {
        recipeAdapter.swapData(recipe);
    }

    @Override
    public void showSnackBar(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    private int numberOfColumns(int scalingFactor) {
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int columnCount = (int) (dpWidth / scalingFactor);
        return (columnCount >= 1 ? columnCount : 1);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        WidgetService.startActionUpdateRecipeWidgets(getContext(), recipe);
        NavDirections action = RecipeListFragmentDirections.nextAction(recipe);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
