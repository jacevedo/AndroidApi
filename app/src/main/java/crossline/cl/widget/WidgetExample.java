package crossline.cl.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import crossline.cl.portafolio.R;
import crossline.cl.portafolio.TestApp;
import crossline.cl.services.MyWidgetService;

public class WidgetExample extends AppWidgetProvider
{
	 public static final String TOAST_ACTION = "cl.ingc.moic.OPEN_POUND";
	 public static final String EXTRA_ITEM = "cl.ingc.moix.ITEM_CLICK";

	 public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	 { 
		final int N = appWidgetIds.length;

        for (int i=0; i<N; i++) 
        {
        	RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
        	
        	 Intent intent = new Intent(context, MyWidgetService.class);
             intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
             intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));        	
        	
             views.setRemoteAdapter(R.id.stack, intent);
             
            int appWidgetId = appWidgetIds[i];
          
            Intent sendIntentReplace = new Intent(context, WidgetExample.class);
            sendIntentReplace.setAction(WidgetExample.TOAST_ACTION);
            sendIntentReplace.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            

            PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, sendIntentReplace,
                PendingIntent.FLAG_UPDATE_CURRENT);
            
            views.setPendingIntentTemplate(R.id.stack, toastPendingIntent);
                        
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        
        super.onUpdate(context, appWidgetManager, appWidgetIds);
		 	
	 }

	@Override
	public void onReceive(Context context, Intent intent)
	{		
		if (intent.getAction().equals(WidgetExample.TOAST_ACTION))
        {
		    Bundle bundle = intent.getExtras();
            
            Intent i = new Intent(context, TestApp.class);
            i.putExtras(bundle);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
		super.onReceive(context, intent);
	}	
}
