package crossline.cl.fragment.principal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import crossline.cl.adapter.RecicleViewAdapterBlack;
import crossline.cl.adapter.item_decoration.DecorationElements;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.listener.OnItemClickListener;
import crossline.cl.portafolio.AnimationActivity;
import crossline.cl.portafolio.CustomViewsActivity;
import crossline.cl.portafolio.R;


public class CustomViewsFragment extends Fragment implements OnItemClickListener
{

    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        initComponents(v);
        return v;
    }
    private void initComponents(View v)
    {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView)v.findViewById(R.id.listRecycler);
        recyclerView.setLayoutManager(mLayoutManager);
        RecicleViewAdapterBlack adapter = new RecicleViewAdapterBlack(((BaseFragmentActivity)getActivity()).getViewsList());
        recyclerView.addItemDecoration(new DecorationElements(new int[]{android.R.attr.listDivider}, getActivity()));
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position)
    {
        Intent i = new Intent(getActivity(), CustomViewsActivity.class);
        i.putExtra("typeAnim", position);
        startActivity(i);
    }


}
