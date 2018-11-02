package com.example.lukaszwachowski.bakingapp.di.modules;

import com.example.lukaszwachowski.bakingapp.network.RecipeService;
import com.example.lukaszwachowski.bakingapp.ui.main.MainActivity;
import com.example.lukaszwachowski.bakingapp.ui.main.MainActivityMVP;
import com.example.lukaszwachowski.bakingapp.ui.main.MainActivityPresenter;
import com.example.lukaszwachowski.bakingapp.ui.main.RecipeAdapter;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MainActivityModule {

  @Provides
  public RecipeService recipeService(Retrofit retrofit) {
    return retrofit.create(RecipeService.class);
  }

  @Provides
  public Retrofit retrofit() {
    return new Retrofit.Builder()
        .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Provides
  public MainActivityMVP.Presenter providePresenter(RecipeService service,
      MainActivity mainActivity) {
    return new MainActivityPresenter(service, mainActivity);
  }

  @Provides
  public RecipeAdapter recipeAdapter(MainActivity mainActivity) {
    return new RecipeAdapter(mainActivity);
  }
}
