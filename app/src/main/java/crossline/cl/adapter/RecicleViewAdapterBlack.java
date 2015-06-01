package crossline.cl.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import crossline.cl.adapter.holder.ViewHolder;
import crossline.cl.adapter.holder.ViewHolderBlack;
import crossline.cl.listener.OnItemClickListener;
import crossline.cl.portafolio.R;

/**
 * Created by jacevedo on 31-05-15.
 */
public class RecicleViewAdapterBlack extends RecyclerView.Adapter<ViewHolderBlack>
{
    private String[] listExample;
    private OnItemClickListener clickListener;
    private ViewHolderBlack vh;

    public RecicleViewAdapterBlack(String[] listExample)
    {
        this.listExample = listExample;
    }

    @Override
    public ViewHolderBlack onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_text2, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        vh = new ViewHolderBlack(v, clickListener);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolderBlack viewHolder, int i)
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
