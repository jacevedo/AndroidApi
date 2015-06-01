package crossline.cl.portafolio;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

import crossline.cl.adapter.RecicleViewAdapter;
import crossline.cl.adapter.item_decoration.DecorationElements;
import crossline.cl.fragment.principal.AnimFragment;
import crossline.cl.fragment.principal.ControlsFragment;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.fragment.principal.CustomViewsFragment;
import crossline.cl.fragment.principal.DialogExampleFragment;
import crossline.cl.fragment.principal.HomeFragment;
import crossline.cl.fragment.principal.MapFragment;
import crossline.cl.fragment.principal.MarshallingFragment;
import crossline.cl.fragment.principal.MultimediaFragment;
import crossline.cl.fragment.principal.RestConnectionFragment;
import crossline.cl.fragment.principal.SQLiteFragment;
import crossline.cl.fragment.principal.SensorFragment;


public class TestApp extends BaseFragmentActivity implements DialogInterface.OnClickListener, crossline.cl.listener.OnItemClickListener
{

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initElements();
        if(savedInstanceState==null)
        {
            int option = getIntent().getIntExtra("typeOption",-1);
            selectItem(-1);
            if(option!= -1)
            {
                selectItem(option);
            }
        }
    }

    private void initElements()
    {

        mLayoutManager = new LinearLayoutManager(this);
        mDrawerList.setLayoutManager(mLayoutManager);
        RecicleViewAdapter adapter = new RecicleViewAdapter(getHomeList());
        adapter.setOnItemClickListener(this);
        mDrawerList.addItemDecoration(new DecorationElements(new int[]{android.R.attr.listDivider}, this));
        mDrawerList.setAdapter(adapter);
    }

    public void selectItem(int position)
    {

        if(position != 0 && position != 11)
        {
            Fragment fragment = getFragment(position);
            FragmentManager fragmentManager = getSupportFragmentManager();
            if(position!=-1)
            {
                fragmentManager.beginTransaction().replace(R.id.principalContent, fragment).addToBackStack(null).commit();
            }
            else
            {
                fragmentManager.beginTransaction().replace(R.id.principalContent, fragment).commit();
            }
        }
        else if( position == 11)
        {
            dialogWidget();
        }
        else if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            onBackPressed();
        }

        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private void dialogWidget()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(R.string.widget);
        dialogBuilder.setMessage(R.string.open_widget);
        dialogBuilder.setPositiveButton(R.string.accept, this);
        dialogBuilder.create().show();
    }

    private Fragment getFragment(int position)
    {
        switch (position)
        {

            case -1:
                return new HomeFragment();
            case 1:
                return new ControlsFragment();
            case 2:
                return new AnimFragment();
            case 3:
                return new DialogExampleFragment();
            case 4:
                return new MultimediaFragment();
            case 5:
                return new SQLiteFragment();
            case 6:
                return new MarshallingFragment();
            case 7:
                return new RestConnectionFragment();
            case 8:
                //return new MapFragment();
            case 9:
                return new SensorFragment();
            case 10:
                return new CustomViewsFragment();
            default:
                return null;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        dialog.dismiss();
    }

    @Override
    public void onItemClick(View view, int position)
    {
        selectItem(position);
    }
}
