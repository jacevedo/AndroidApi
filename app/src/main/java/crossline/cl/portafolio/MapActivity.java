package crossline.cl.portafolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import crossline.cl.adapter.RecicleViewAdapter;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.fragment.map.CustomMarkerFragment;
import crossline.cl.fragment.map.MapMarkerFragment;
import crossline.cl.fragment.map.PutAddressFragment;


public class MapActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener
{
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initElements();
        if(savedInstanceState==null)
        {
            int option = getIntent().getIntExtra("typeMap",-1);
            selectItem(option);
        }
    }

    private void selectItem(int option)
    {
        Fragment mFragment = getFragment(option);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.principalContent, mFragment).commit();
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    private Fragment getFragment(int option)
    {
        switch (option)
        {
            case 0:
                return new MapMarkerFragment();
            case 1:
                return new CustomMarkerFragment();
            case 2:
                return new PutAddressFragment();
            default:
                return null;
        }
    }

    private void initElements()
    {
        mLayoutManager = new LinearLayoutManager(this);
        mDrawerList.setLayoutManager(mLayoutManager);

        mDrawerList.setAdapter(new RecicleViewAdapter(getHomeList()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        selectItem(position);
    }

}
