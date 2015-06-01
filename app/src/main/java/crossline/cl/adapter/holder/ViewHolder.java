package crossline.cl.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import crossline.cl.listener.OnItemClickListener;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private OnItemClickListener clickListener;
    // each data item is just a string in this case
    public TextView mTextView;
    private OnItemClickListener onClickListener;

    public ViewHolder(TextView v, OnItemClickListener clickListener)
    {
        super(v);
        this.clickListener = clickListener;
        mTextView = v;
        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(clickListener!= null)
        {
            clickListener.onItemClick(v, getLayoutPosition()); //OnItemClickListener mItemClickListener;
        }
    }

    public void setOnClickListener(OnItemClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }
}