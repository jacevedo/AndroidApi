package crossline.cl.fragment.multimedia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import crossline.cl.portafolio.R;

public class OpenVideoFragment extends Fragment implements View.OnClickListener
{
    private static final int RESULT_LOAD_IMAGE = 1;
    private VideoView vView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_open_video, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        MediaController controller = new MediaController(getActivity());
        vView = (VideoView)v.findViewById(R.id.vView);
        vView.setMediaController(controller);
        v.findViewById(R.id.btnTakeVideo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnTakeVideo:
                searchForVideo();
            break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == getActivity().RESULT_OK)
        {
            try
            {
                Uri selectedVideoUri = data.getData();
                vView.setVideoURI(selectedVideoUri);
                vView.start();
            }
            catch (Exception e)
            {
                Log.e("Open Picture", e.toString());
            }
        }
    }
    private void searchForVideo()
    {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
}
