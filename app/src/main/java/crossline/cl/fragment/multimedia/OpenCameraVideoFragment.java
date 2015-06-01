package crossline.cl.fragment.multimedia;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import android.widget.MediaController;

import crossline.cl.portafolio.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class OpenCameraVideoFragment extends Fragment implements View.OnClickListener
{
    static final int REQUEST_VIDEO_CAPTURE = 1;
    private VideoView vView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_open_camera_video, container, false);
        MediaController controller = new MediaController(getActivity());
        vView = (VideoView)v.findViewById(R.id.vView);
        vView.setMediaController(controller);
        v.findViewById(R.id.btnTakeVideo).setOnClickListener(this);
        return v;
    }
    private void takeVideoIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == getActivity().RESULT_OK)
        {
            Uri videoUri = data.getData();
            vView.setVideoURI(videoUri);
            vView.start();
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnTakeVideo:
                takeVideoIntent();
            break;
        }
    }
}
