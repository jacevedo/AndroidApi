package crossline.cl.fragment.controls;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import crossline.cl.portafolio.R;


public class ProgressSeekBarFragment extends Fragment implements SeekBar.OnSeekBarChangeListener
{
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private TextView txtProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_progres_seek_bar, container, false);
        initComponents(v);
        return v;
    }

    private void initComponents(View v)
    {
        progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        seekBar = (SeekBar)v.findViewById(R.id.seekBar);
        txtProgress = (TextView)v.findViewById(R.id.txtProgess);

        progressBar.setMax(100);
        seekBar.setMax(100);

        seekBar.setOnSeekBarChangeListener(this);

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        txtProgress.setText(getString(R.string.progress) +" "+ progress);
        progressBar.setProgress(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {
        txtProgress.setText(getString(R.string.progress) +" "+ seekBar.getProgress());
        progressBar.setProgress(seekBar.getProgress());
    }
}
