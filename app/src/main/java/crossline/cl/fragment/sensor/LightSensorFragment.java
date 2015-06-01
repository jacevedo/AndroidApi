package crossline.cl.fragment.sensor;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import crossline.cl.portafolio.R;

public class LightSensorFragment extends Fragment implements SensorEventListener
{
    private ProgressBar bar;
    private TextView txtLightValue;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private float max;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sensorManager
                = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        lightSensor
                = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null)
        {

        }
        else
        {

            sensorManager.registerListener(this,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
            max =  lightSensor.getMaximumRange();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        lightSensor
                = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor == null)
        {
            getActivity().onBackPressed();
        }
        else
        {
            sensorManager.registerListener(this,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
            max =  lightSensor.getMaximumRange();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_ligth_sensor, container, false);
        initComponents(v);
        return v;
    }

    private void initComponents(View v)
    {
        bar = (ProgressBar)v.findViewById(R.id.prLight);
        txtLightValue = (TextView)v.findViewById(R.id.txtValueLight);
        bar.setMax((int)max);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType()==Sensor.TYPE_LIGHT)
        {
            final float currentReading = event.values[0];
            bar.setProgress((int) currentReading);
            txtLightValue.setText(getString(R.string.value_light_sensor) +
                                                                    String.valueOf(currentReading));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}
