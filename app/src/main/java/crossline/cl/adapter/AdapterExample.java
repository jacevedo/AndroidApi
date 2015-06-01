package crossline.cl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import crossline.cl.object.ObjectListView;
import crossline.cl.portafolio.R;


/**
 * Created by Jaime on 26/08/2014.
 */
public class AdapterExample extends BaseAdapter
{
    private Context context;
    private ArrayList<ObjectListView> listData;
    public AdapterExample(Context context, ArrayList<ObjectListView> listData)
    {
        this.context = context;
        this.listData = listData;
    }
    @Override
    public int getCount()
    {
        return listData.size();
    }
    @Override
    public Object getItem(int position)
    {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return listData.get(position).getSourceImg();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_view_example,null);
        }
        ImageView image = (ImageView)v.findViewById(R.id.imgListView);
        TextView text = (TextView)v.findViewById(R.id.txtListView);

        image.setImageResource(listData.get(position).getSourceImg());
        text.setText(listData.get(position).getTextList());

        return v;
    }
}
