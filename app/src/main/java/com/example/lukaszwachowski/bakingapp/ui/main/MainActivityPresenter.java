package com.example.lukaszwachowski.bakingapp.ui.main;

import android.util.DisplayMetrics;
import com.example.lukaszwachowski.bakingapp.network.RecipeService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityMVP.Presenter {

  private MainActivityMVP.View view;
  private RecipeService recipeService;
  private CompositeDisposable disposable;
  private MainActivity activity;

  public MainActivityPresenter(RecipeService recipeService, MainActivity activity) {
    this.recipeService = recipeService;
    this.activity = activity;
    disposable = new CompositeDisposable();
  }

  @Override
  public void loadData() {

    disposable.add(recipeService.getRecipes()
        .concatMap(Observable::fromIterable)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(recipe -> {
          if (view != null) {
            view.updateData(recipe);
          }
        }, error -> {
          if (view != null) {
            view.showSnackBar(error.getLocalizedMessage());
          }
        }));
  }

  @Override
  public int numberOfColumns(int scalingFactor) {
    DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
    float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
    int columnCount = (int) (dpWidth / scalingFactor);
    return (columnCount >= 1 ? columnCount : 1);
  }

  @Override
  public void attachView(MainActivityMVP.View view) {
    this.view = view;
  }

  @Override
  public void detachView() {
    disposable.clear();
  }
}
