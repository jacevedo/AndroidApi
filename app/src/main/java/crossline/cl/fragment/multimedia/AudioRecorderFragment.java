package crossline.cl.fragment.multimedia;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;

import crossline.cl.portafolio.R;


public class AudioRecorderFragment extends Fragment implements View.OnClickListener, MediaPlayer.OnCompletionListener
{
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;

    private MediaRecorder mRecorder = null;

    private MediaPlayer   mPlayer = null;

    private boolean mStartRecording = true;
    private boolean mStartPlaying = true;
    private Button btnPlayAudio;


    public AudioRecorderFragment()
    {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/audioRecordTest.mp3";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_audio_record, container, false);
        btnPlayAudio = (Button) v.findViewById(R.id.btnPlay);
        v.findViewById(R.id.btnRec).setOnClickListener(this);
        btnPlayAudio.setOnClickListener(this);
        return v;
    }

    private void onRecord(boolean start)
    {
        if (start)
        {
            startRecording();
        }
        else
        {
            stopRecording();
        }
    }

    private void onPlay(boolean start)
    {
        if (start)
        {
            startPlaying();
        }
        else
        {
            stopPlaying();
        }
    }

    private void startPlaying()
    {
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(this);
        try
        {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying()
    {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording()
    {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try
        {
            mRecorder.prepare();
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording()
    {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnRec:
                recording((Button) v);
            break;
            case R.id.btnPlay:
                playing((Button)v);
            break;
        }
    }

    private void playing(Button v)
    {
        onPlay(mStartPlaying);
        if (mStartPlaying)
        {
            v.setText(R.string.stop);
        }
        else
        {
            v.setText(R.string.play_audio);
        }
        mStartPlaying = !mStartPlaying;
    }

    private void recording(Button v)
    {
        onRecord(mStartRecording);
        if (mStartRecording)
        {
            v.setText(R.string.stop);
        }
        else
        {
            v.setText(R.string.start_record_audio);
        }
        mStartRecording = !mStartRecording;
    }



    @Override
    public void onPause()
    {
        super.onPause();
        if (mRecorder != null)
        {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null)
        {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
       playing(btnPlayAudio);

    }
}
