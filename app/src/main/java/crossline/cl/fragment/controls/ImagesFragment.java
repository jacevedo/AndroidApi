package crossline.cl.fragment.controls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import crossline.cl.portafolio.R;

public class ImagesFragment extends Fragment implements View.OnClickListener
{
    private int[] images;
    private ImageView imgExample;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_images, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        imgExample = (ImageView)v.findViewById(R.id.imgExample);
        v.findViewById(R.id.btnImageButton).setOnClickListener(this);


        images = getImages();
    }

    private int[]getImages()
    {
        return new int[]{R.drawable.image1,
                         R.drawable.image2,
                         R.drawable.image3,
                         R.drawable.image4,
                         R.drawable.image5};
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnImageButton:
                changeImages((ImageButton)v);
                break;
        }
    }

    private void changeImages(ImageButton v)
    {
        if(imgExample.getTag().toString().compareToIgnoreCase("image1")==0)
        {
            v.setImageResource(R.drawable.image3);
            imgExample.setImageResource(R.drawable.image2);
            imgExample.setTag("image2");
        }
        else if(imgExample.getTag().toString().compareToIgnoreCase("image2")==0)
        {
            v.setImageResource(R.drawable.image4);
            imgExample.setImageResource(R.drawable.image3);
            imgExample.setTag("image3");
        }
        else if(imgExample.getTag().toString().compareToIgnoreCase("image3")==0)
        {
            v.setImageResource(R.drawable.image5);
            imgExample.setImageResource(R.drawable.image4);
            imgExample.setTag("image4");
        }
        else if(imgExample.getTag().toString().compareToIgnoreCase("image4")==0)
        {
            v.setImageResource(R.drawable.image1);
            imgExample.setImageResource(R.drawable.image5);
            imgExample.setTag("image5");
        }
        else
        {
            v.setImageResource(R.drawable.image2);
            imgExample.setImageResource(R.drawable.image1);
            imgExample.setTag("image1");
        }
    }
}
