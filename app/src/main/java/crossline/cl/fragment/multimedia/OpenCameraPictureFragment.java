package crossline.cl.fragment.multimedia;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import crossline.cl.portafolio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenCameraPictureFragment extends Fragment implements View.OnClickListener
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imgPicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_open_camera_picture, container, false);
        imgPicture = (ImageView)v.findViewById(R.id.imgPicture);
        v.findViewById(R.id.btnTakePicture).setOnClickListener(this);
        return v;
    }
    private void takePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgPicture.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnTakePicture:
                takePictureIntent();
            break;
        }
    }
}
