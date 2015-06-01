package crossline.cl.fragment.animation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import crossline.cl.portafolio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RotateAnimationFragment extends Fragment implements View.OnClickListener
{
    private ImageView imgFadeAnim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rotate_animation, container, false);
        initComponent(v);
        return v;
    }
    private void initComponent(View v)
    {
        imgFadeAnim = (ImageView)v.findViewById(R.id.imgFadeAnim);

        v.findViewById(R.id.btnAnim1).setOnClickListener(this);
        v.findViewById(R.id.btnAnim2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAnim1:
                startLeftAnim();
                break;
            case R.id.btnAnim2:
                startRightAnim();
                break;
        }
    }

    private void startLeftAnim()
    {
        RotateAnimation anim = new RotateAnimation(360f , 0f,RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(2000);
        imgFadeAnim.startAnimation(anim);
    }

    private void startRightAnim()
    {
        RotateAnimation anim = new RotateAnimation(0f , 360f,RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(2000);
        imgFadeAnim.startAnimation(anim);
    }

}
