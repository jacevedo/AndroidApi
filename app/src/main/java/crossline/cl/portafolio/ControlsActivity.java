package crossline.cl.portafolio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import crossline.cl.adapter.RecicleViewAdapter;
import crossline.cl.fragment.controls.AutoCompleteFragment;
import crossline.cl.fragment.controls.ButtonFragment;
import crossline.cl.fragment.controls.CheckBoxFragment;
import crossline.cl.fragment.controls.EditTextFragment;
import crossline.cl.fragment.controls.ImagesFragment;
import crossline.cl.fragment.controls.ListViewFragment;
import crossline.cl.fragment.controls.ProgressSeekBarFragment;
import crossline.cl.fragment.controls.RadioButtonFragment;
import crossline.cl.fragment.controls.TextViewFragment;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.fragment.controls.WebViewFragment;


public class ControlsActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener
{

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initElements();
        if(savedInstanceState==null)
        {
            int option = getIntent().getIntExtra("typeControl",-1);
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
                return new ButtonFragment();
            case 1:
                return new TextViewFragment();
            case 2:
                return new EditTextFragment();
            case 3:
                return new ListViewFragment();
            case 4:
                return new RadioButtonFragment();
            case 5:
                return new CheckBoxFragment();
            case 6:
                return new ImagesFragment();
            case 7:
                return new AutoCompleteFragment();
            case 8:
                return new ProgressSeekBarFragment();
            case 9:
                return new WebViewFragment();
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
