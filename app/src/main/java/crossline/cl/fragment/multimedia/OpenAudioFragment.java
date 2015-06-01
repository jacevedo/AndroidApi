package crossline.cl.fragment.multimedia;


import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import crossline.cl.portafolio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenAudioFragment extends Fragment implements View.OnClickListener, MediaPlayer.OnCompletionListener
{
    private static final int REQUEST_AUDIO_CAPTURE = 1;
    private TextView txtNameAudio;
    private Button btnPlayAudio;
    private MediaPlayer player;
    private Uri audioUri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_open_audio, container, false);
        txtNameAudio = (TextView)v.findViewById(R.id.txtNameAudio);
        btnPlayAudio  = (Button)v.findViewById(R.id.btnPlayAudio);
        btnPlayAudio.setOnClickListener(this);
        v.findViewById(R.id.btnSearchAudio).setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnSearchAudio:
                openAudio();
                break;
            case R.id.btnPlayAudio:
                playAudio((Button)v);

                break;
        }
    }

    private void playAudio(Button v)
    {
        if(audioUri != null)
        {
            if(player!=null && player.isPlaying())
            {
                player.release();
                player = null;
                v.setText(R.string.play_audio);
            }
            else
            {
                try
                {
                    player = new MediaPlayer();
                    player.setDataSource(getActivity(),audioUri);
                    player.prepare();
                    player.start();
                    v.setText(R.string.stop);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

    private void openAudio()
    {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,REQUEST_AUDIO_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_AUDIO_CAPTURE && resultCode == getActivity().RESULT_OK)
        {
            audioUri = data.getData();
            player = new MediaPlayer();
            String[] arrayImage = getImage(audioUri).split("/");
            String nomImage = arrayImage[arrayImage.length-1];
            txtNameAudio.setText(nomImage);
        }
    }
    private String getImage(Uri uri)
    {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getActivity().getContentResolver().query(uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }
    @Override
    public void onCompletion(MediaPlayer mp)
    {
        playAudio(btnPlayAudio);
    }
}
