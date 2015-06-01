package crossline.cl.fragment.animation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import crossline.cl.portafolio.R;

public class FadeAnimationFragment extends Fragment implements View.OnClickListener, Animation.AnimationListener
{
    private ImageView imgFadeAnim;
    private boolean isAnimationStart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_fade_animation, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        isAnimationStart = false;
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
                startHalfAnim();
                break;
            case R.id.btnAnim2:
                startCompleteAnim();
                break;
        }
    }

    private void startHalfAnim()
    {
        AlphaAnimation anim = null;
        if(imgFadeAnim.getAlpha()==1.0)
        {
            anim = new AlphaAnimation(1.0f, 0.0f);
        }
        else
        {
            anim = new AlphaAnimation(0.0f, 1.0f);
        }
        anim.setAnimationListener(this);
        anim.setFillAfter(true);
        anim.setDuration(2000);
        imgFadeAnim.startAnimation(anim);
    }

    private void startCompleteAnim()
    {
        AlphaAnimation anim = new AlphaAnimation(1.0f,0.0f);
        anim.setFillAfter(true);
        anim.setDuration(2000);
        anim.setRepeatCount(1);
        anim.setRepeatMode(AlphaAnimation.REVERSE);
        imgFadeAnim.startAnimation(anim);
    }

    @Override
    public void onAnimationStart(Animation animation)
    {
        if(imgFadeAnim.getAlpha() == 0.0)
        {
            imgFadeAnim.setAlpha(1.0f);
            isAnimationStart = true;
        }
    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        if(imgFadeAnim.getAlpha() == 1.0 && !isAnimationStart)
        {
            imgFadeAnim.setAlpha(0.0f);
        }
        if(isAnimationStart)
        {
            isAnimationStart = false;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation)
    {

    }
}
