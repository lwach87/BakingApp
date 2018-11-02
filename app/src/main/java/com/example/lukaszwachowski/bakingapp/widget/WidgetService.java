package com.example.lukaszwachowski.bakingapp.widget;

import static com.example.lukaszwachowski.bakingapp.configuration.Constants.RECIPE_WIDGET_ACTION_UPDATE;
import static com.example.lukaszwachowski.bakingapp.configuration.Constants.RECIPE_WIDGET_DATA;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.example.lukaszwachowski.bakingapp.network.model.Recipe;

public class WidgetService extends IntentService {

  public WidgetService() {
    super("WidgetService");
  }

  public static void startActionUpdateRecipeWidgets(Context context, Recipe recipe) {
    Intent intent = new Intent(context, WidgetService.class);
    intent.setAction(RECIPE_WIDGET_ACTION_UPDATE);
    intent.putExtra(RECIPE_WIDGET_DATA, recipe);
    context.startService(intent);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    if (intent != null) {
      final String action = intent.getAction();
      if (RECIPE_WIDGET_ACTION_UPDATE.equals(action) &&
          intent.getParcelableExtra(RECIPE_WIDGET_DATA) != null) {
        handleActionUpdateWidgets(intent.getParcelableExtra(RECIPE_WIDGET_DATA));
      }
    }
  }

  private void handleActionUpdateWidgets(Recipe recipe) {
    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
    int[] appWidgetIds = appWidgetManager
        .getAppWidgetIds(new ComponentName(this, WidgetProvider.class));
    WidgetProvider.updateRecipeWidgets(this, appWidgetManager, recipe, appWidgetIds);
  }
}
