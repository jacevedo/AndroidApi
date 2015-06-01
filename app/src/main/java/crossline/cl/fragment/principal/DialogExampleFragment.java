package crossline.cl.fragment.principal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import crossline.cl.adapter.RecicleViewAdapterBlack;
import crossline.cl.adapter.item_decoration.DecorationElements;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.listener.OnItemClickListener;
import crossline.cl.portafolio.AnimationActivity;
import crossline.cl.portafolio.R;


public class DialogExampleFragment extends Fragment implements DialogInterface.OnClickListener, View.OnClickListener, OnItemClickListener
{

    private Dialog customDialog;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;


    private void createTranslateDialog()
    {
        customDialog = new Dialog(getActivity());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.custom_dialog);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        customDialog.findViewById(R.id.btnAceptar).setOnClickListener(this);
        customDialog.show();
    }

    private void createZoomDialog()
    {
        customDialog = new Dialog(getActivity());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.custom_dialog);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationZoom;
        customDialog.findViewById(R.id.btnAceptar).setOnClickListener(this);
        customDialog.show();
    }

    private void createTransparentCustomDialog()
    {
        customDialog = new Dialog(getActivity());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.custom_dialog);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        customDialog.findViewById(R.id.btnAceptar).setOnClickListener(this);
        customDialog.show();
    }

    private void createCustomDialog()
    {
        customDialog = new Dialog(getActivity());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.custom_dialog);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.findViewById(R.id.btnAceptar).setOnClickListener(this);
        customDialog.show();
    }

    private void createNormalDialog()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(R.string.title_normal_dialog);
        dialogBuilder.setMessage(R.string.mesagge_normal_dialog);
        dialogBuilder.setPositiveButton(R.string.accept, this);
        dialogBuilder.setNegativeButton(R.string.cancel, this);
        dialogBuilder.setNeutralButton(R.string.neutral, this);
        dialogBuilder.create().show();
    }

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
        RecicleViewAdapterBlack adapter = new RecicleViewAdapterBlack(((BaseFragmentActivity)getActivity()).getAnimationList());
        recyclerView.addItemDecoration(new DecorationElements(new int[]{android.R.attr.listDivider}, getActivity()));
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position)
    {
        switch (position)
        {
            case 0:
                createNormalDialog();
                break;
            case 1:
                createCustomDialog();
                break;
            case 2:
                createTransparentCustomDialog();
                break;
            case 3:
                createZoomDialog();
                break;
            case 4:
                createTranslateDialog();
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        switch (which)
        {
            case DialogInterface.BUTTON_POSITIVE:
                Toast.makeText(getActivity(),R.string.accept,Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            break;
            case DialogInterface.BUTTON_NEGATIVE:
                Toast.makeText(getActivity(),R.string.cancel,Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            break;
            case DialogInterface.BUTTON_NEUTRAL:
                Toast.makeText(getActivity(),R.string.neutral,Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            break;
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAceptar:
                customDialog.dismiss();
                break;
        }
    }
}
