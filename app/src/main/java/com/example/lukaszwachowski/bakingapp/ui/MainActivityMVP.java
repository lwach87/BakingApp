package com.example.lukaszwachowski.bakingapp.ui;

import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

public class MainActivityMVP {

    public interface View {

        void updateData(Recipe recipe);

        void showSnackBar(String text);
    }

    public interface Presenter {

        void loadData();

        void attachView(MainActivityMVP.View view);

        void detachView();
    }
}
