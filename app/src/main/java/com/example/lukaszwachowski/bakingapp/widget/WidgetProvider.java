package com.example.lukaszwachowski.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.lukaszwachowski.bakingapp.R;
import com.example.lukaszwachowski.bakingapp.network.model.Ingredient;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;
import com.example.lukaszwachowski.bakingapp.ui.detail.DetailActivity;

public class WidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                Recipe recipe, int appWidgetId) {

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                DetailActivity.myIntent(context, recipe), PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        views.removeAllViews(R.id.widget_ingredient_list);
        views.setTextViewText(R.id.recipe_widget_title, recipe.getName());
        views.setOnClickPendingIntent(R.id.recipe_widget_holder, pendingIntent);

        for (Ingredient ingredient : recipe.ingredients) {
            RemoteViews mIngredient = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);
            mIngredient.setTextViewText(R.id.text_widget_ingredient_item,
                    String.valueOf(ingredient.quantity) + " " +
                            String.valueOf(ingredient.measure) + " " +
                            ingredient.ingredient);
            views.addView(R.id.widget_ingredient_list, mIngredient);
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
                                           Recipe recipe, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

