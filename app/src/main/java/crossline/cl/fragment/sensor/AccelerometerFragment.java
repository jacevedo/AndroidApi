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
import android.widget.TextView;

import crossline.cl.portafolio.R;


public class AccelerometerFragment extends Fragment implements SensorEventListener
{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView txtXAxis;
    private TextView txtYAxis;
    private TextView txtZAxis;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initSensor();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void initSensor()
    {
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
        {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        }
        else
        {
            getActivity().onBackPressed();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        initElements(v);
        return v;
    }

    private void initElements(View v)
    {
        txtXAxis = (TextView)v.findViewById(R.id.txtEjeX);
        txtYAxis = (TextView)v.findViewById(R.id.txtEjeY);
        txtZAxis = (TextView)v.findViewById(R.id.txtEjeZ);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            txtXAxis.setText(event.values[0]+"");
            txtYAxis.setText(event.values[1]+"");
            txtZAxis.setText(event.values[2]+"");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}
