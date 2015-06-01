package crossline.cl.fragment.principal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import crossline.cl.portafolio.R;
import crossline.cl.portafolio.RestConnectionActivity;


public class RestConnectionFragment extends Fragment implements DialogInterface.OnClickListener, OnItemClickListener
{

    private Dialog customDialog;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;



    private void initComponents(View v)
    {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView)v.findViewById(R.id.listRecycler);
        recyclerView.setLayoutManager(mLayoutManager);
        RecicleViewAdapterBlack adapter = new RecicleViewAdapterBlack(((BaseFragmentActivity)getActivity()).getRestConnectionList());
        recyclerView.addItemDecoration(new DecorationElements(new int[]{android.R.attr.listDivider}, getActivity()));
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    private void createDialogMessage()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(R.string.network_problem);
        dialogBuilder.setMessage(R.string.internet_not_connected);
        dialogBuilder.setPositiveButton(R.string.accept, this);
        dialogBuilder.create().show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        initComponents(v);
        return v;
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        dialog.dismiss();
    }

    @Override
    public void onItemClick(View view, int position)
    {
        if(((BaseFragmentActivity)getActivity()).isOnline())
        {
            Intent i = new Intent(getActivity(), RestConnectionActivity.class);
            i.putExtra("typeConnection", position);
            startActivity(i);
        }
        else
        {
            createDialogMessage();
        }
    }
}
