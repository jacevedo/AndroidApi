package crossline.cl.services;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import crossline.cl.portafolio.R;
import crossline.cl.widget.WidgetExample;

public class MyWidgetService extends RemoteViewsService
{

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) 
	{
		 return new RemoteView(this.getApplicationContext());
	}
	class RemoteView implements RemoteViewsFactory
	{
		private Context context;
		private ArrayList<Integer> listImages;
		 
		public RemoteView(Context context)
		{
			this.context = context;
		}
		@Override
		public void onCreate() 
		{
			listImages = new ArrayList<Integer>();
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);
			listImages.add(R.drawable.img_home_est01_widget);

		}
		@Override
		public void onDataSetChanged()
		{
			
		}

		@Override
		public void onDestroy() 
		{
			
		}

		@Override
		public int getCount()
		{
			return listImages.size();
		}

		@Override
		public RemoteViews getViewAt(int position) 
		{
			RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.item_widget);
			view.setImageViewResource(R.id.imageWidget, listImages.get(position));

			view.setOnClickFillInIntent(R.id.imageWidget, getIntent(position));
			
			return view;
		}

		private Intent getIntent(int position)
		{
			Bundle extras = new Bundle();

	        extras.putInt("typeOption", position);
	        extras.putInt(WidgetExample.EXTRA_ITEM, position);
	       
	        Intent fillInIntent = new Intent();
	        fillInIntent.putExtras(extras);
			
	        
	        return fillInIntent;
		}

		@Override
		public RemoteViews getLoadingView() 
		{
			return null;
		}

		@Override
		public int getViewTypeCount() 
		{
			return 0;
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		@Override
		public boolean hasStableIds() 
		{
			return false;
		}
		
	}
}
