package com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment;

import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

public class RecipeMVP {

  public interface View {

    void updateData(Recipe recipe);

    void showSnackBar(String text);
  }

  public interface Presenter {

    void loadData();

    void attachView(RecipeMVP.View view);

    void detachView();
  }
}
