package com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment;

import com.example.lukaszwachowski.bakingapp.fragments.RecipeListFragment.RecipeMVP;
import com.example.lukaszwachowski.bakingapp.network.RecipeService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RecipePresenter implements RecipeMVP.Presenter {

    private RecipeMVP.View view;
    private RecipeService recipeService;
    private CompositeDisposable disposable;

    @Inject
    public RecipePresenter(RecipeService recipeService) {
        this.recipeService = recipeService;
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
    public void attachView(RecipeMVP.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        disposable.clear();
    }
}
