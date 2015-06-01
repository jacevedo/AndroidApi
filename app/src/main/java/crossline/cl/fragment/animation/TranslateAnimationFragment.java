package crossline.cl.fragment.animation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import crossline.cl.portafolio.R;

public class TranslateAnimationFragment extends Fragment implements View.OnClickListener
{
    private ImageView imgAnim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_translate_animation, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        imgAnim = (ImageView)v.findViewById(R.id.imgAnim);

        v.findViewById(R.id.btnTop).setOnClickListener(this);
        v.findViewById(R.id.btnBottom).setOnClickListener(this);
        v.findViewById(R.id.btnLeft).setOnClickListener(this);
        v.findViewById(R.id.btnRight).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnTop:
                animTop();
                break;
            case R.id.btnBottom:
                animBottom();
                break;
            case R.id.btnLeft:
                animLeft();
                break;
            case R.id.btnRight:
                animRight();
                break;
        }
    }

    private void animRight()
    {
        TranslateAnimation anim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                                   TranslateAnimation.RELATIVE_TO_SELF, 1.0f,
                                   TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                                   TranslateAnimation.RELATIVE_TO_SELF, 0.0f);
        anim.setDuration(500);
        anim.setRepeatCount(1);
        anim.setRepeatMode(TranslateAnimation.REVERSE);

        imgAnim.startAnimation(anim);
    }

    private void animLeft()
    {
        TranslateAnimation anim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, -1.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f);
        anim.setDuration(500);
        anim.setRepeatCount(1);
        anim.setRepeatMode(TranslateAnimation.REVERSE);

        imgAnim.startAnimation(anim);
    }

    private void animBottom()
    {
        TranslateAnimation anim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1.0f);
        anim.setDuration(500);
        anim.setRepeatCount(1);
        anim.setRepeatMode(TranslateAnimation.REVERSE);

        imgAnim.startAnimation(anim);
    }

    private void animTop()
    {
        TranslateAnimation anim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, -1.0f);
        anim.setDuration(500);
        anim.setRepeatCount(1);
        anim.setRepeatMode(TranslateAnimation.REVERSE);

        imgAnim.startAnimation(anim);
    }
}
