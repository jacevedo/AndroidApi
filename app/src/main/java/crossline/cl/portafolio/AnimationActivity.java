package crossline.cl.portafolio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import crossline.cl.adapter.RecicleViewAdapter;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.fragment.animation.FadeAnimationFragment;
import crossline.cl.fragment.animation.RotateAnimationFragment;
import crossline.cl.fragment.animation.RotateAnimationV2Fragment;
import crossline.cl.fragment.animation.ScaleAnimationFragment;
import crossline.cl.fragment.animation.TranslateAnimationFragment;


public class AnimationActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener
{


    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initElements();
        if(savedInstanceState==null)
        {
            int option = getIntent().getIntExtra("typeAnim",-1);
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
                return new FadeAnimationFragment();
            case 1:
                return new RotateAnimationFragment();
            case 2:
                return new TranslateAnimationFragment();
            case 3:
                return new RotateAnimationV2Fragment();
            case 4:
                return new ScaleAnimationFragment();
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
