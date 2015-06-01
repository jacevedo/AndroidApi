package crossline.cl.fragment.animation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import crossline.cl.portafolio.R;

public class ScaleAnimationFragment extends Fragment implements View.OnClickListener
{
    private ImageView imgFadeAnim;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_scale_animation, container, false);
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
                scaleIn();
                break;
            case R.id.btnAnim2:
                scaleOut();
                break;
        }
    }

    private void scaleIn()
    {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                                            ScaleAnimation.RELATIVE_TO_SELF,0.5f,
                                            Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        anim.setRepeatCount(1);
        anim.setRepeatMode(ScaleAnimation.REVERSE);

        imgFadeAnim.startAnimation(anim);
    }

    private void scaleOut()
    {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                                                    ScaleAnimation.RELATIVE_TO_SELF,0.5f,
                                                    Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        anim.setRepeatCount(1);
        anim.setRepeatMode(ScaleAnimation.REVERSE);

        imgFadeAnim.startAnimation(anim);
    }
}
