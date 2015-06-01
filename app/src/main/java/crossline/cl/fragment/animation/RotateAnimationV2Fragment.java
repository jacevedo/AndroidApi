package crossline.cl.fragment.animation;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import crossline.cl.portafolio.R;

public class RotateAnimationV2Fragment extends Fragment implements OnClickListener
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
        ObjectAnimator animation = ObjectAnimator.ofFloat(imgFadeAnim,"rotationY",360.0f, 0.0f);
        animation.setDuration(1000);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.start();
    }

    private void startRightAnim()
    {
        ObjectAnimator animation = ObjectAnimator.ofFloat(imgFadeAnim,"rotationY",0.0f, 360.0f);
        animation.setDuration(1000);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.start();
    }

}
