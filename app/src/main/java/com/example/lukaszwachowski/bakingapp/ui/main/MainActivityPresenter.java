package com.example.lukaszwachowski.bakingapp.ui.main;

import com.example.lukaszwachowski.bakingapp.network.RecipeService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityMVP.Presenter {

    private MainActivityMVP.View view;
    private RecipeService recipeService;
    private CompositeDisposable disposable;

    @Inject
    public MainActivityPresenter(RecipeService recipeService) {
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
    public void attachView(MainActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        disposable.clear();
    }
}
