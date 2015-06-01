package crossline.cl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import crossline.cl.adapter.holder.ViewHolder;
import crossline.cl.listener.OnItemClickListener;
import crossline.cl.portafolio.R;

/**
 * Created by jacevedo on 31-05-15.
 */
public class RecicleViewAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private String[] listExample;
    private OnItemClickListener clickListener;
    private ViewHolder vh;

    public RecicleViewAdapter(String [] listExample)
    {
        this.listExample = listExample;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_text, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        vh = new ViewHolder(v, clickListener);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        viewHolder.mTextView.setText(listExample[i]);
    }

    @Override
    public int getItemCount()
    {
        return listExample.length;
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.clickListener = listener;
        if(vh!=null)
        {
            vh.setOnClickListener(listener);
        }
    }


}
