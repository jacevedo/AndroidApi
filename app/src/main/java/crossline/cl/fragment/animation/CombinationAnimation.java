package crossline.cl.fragment.animation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import crossline.cl.portafolio.R;


public class CombinationAnimation extends Fragment
{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_convination_animation, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {

    }
}
