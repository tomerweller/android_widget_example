package com.example.widget_example;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

    private static final String TAG = "WidgetExample:MyWidgetProvider";

    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        Log.d(TAG, "onReceive with action:" + action);
        super.onReceive(context, intent);

        AppWidgetManager appWidgetManaer = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(
                this.getClass().getPackage().getName(),
                MyWidgetProvider.class.getName());
        int[] appWidgetIds = appWidgetManaer.getAppWidgetIds(componentName);

        if (Consts.UPDATE_WITH_MESSAGE_ACTION.equals(action)){
            for (int appWidgetId : appWidgetIds){
                updateWidgetMessage(
                        context,
                        appWidgetManaer,
                        appWidgetId,
                        intent.getStringExtra(Consts.MESSAGE_EXTRA));
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateWidgetMessage(context, appWidgetManager, appWidgetId, "onUpdate()");
        }
    }

    public void updateWidgetMessage(Context context, AppWidgetManager appWidgetManager, int widgetId, String message){
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        views.setTextViewText(R.id.messageView, message);
        appWidgetManager.updateAppWidget(widgetId, views);
    }

}