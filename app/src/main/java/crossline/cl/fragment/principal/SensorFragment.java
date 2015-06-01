package crossline.cl.fragment.principal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import crossline.cl.adapter.RecicleViewAdapterBlack;
import crossline.cl.adapter.item_decoration.DecorationElements;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.listener.OnItemClickListener;
import crossline.cl.portafolio.R;
import crossline.cl.portafolio.SensorActivity;


public class SensorFragment extends Fragment implements DialogInterface.OnClickListener, OnItemClickListener
{

    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        initComponents(v);
        return v;
    }
    private void initComponents(View v)
    {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = (RecyclerView)v.findViewById(R.id.listRecycler);
        recyclerView.setLayoutManager(mLayoutManager);
        RecicleViewAdapterBlack adapter = new RecicleViewAdapterBlack(((BaseFragmentActivity)getActivity()).getSensorList());
        recyclerView.addItemDecoration(new DecorationElements(new int[]{android.R.attr.listDivider}, getActivity()));
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private boolean hasLightSensor()
    {
        SensorManager mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void createDialogLight()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(R.string.sensor_problem);
        dialogBuilder.setMessage(R.string.light_not_exist);
        dialogBuilder.setPositiveButton(R.string.accept, this);
        dialogBuilder.create().show();
    }

    private void createDialogGPS()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(R.string.sensor_problem);
        dialogBuilder.setMessage(R.string.gps_not_turn_on);
        dialogBuilder.setPositiveButton(R.string.accept, this);
        dialogBuilder.create().show();
    }

    private boolean hasGPS()
    {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean hasAccelerometer()
    {
        SensorManager mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void createDialogAccelerometer()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(R.string.sensor_problem);
        dialogBuilder.setMessage(R.string.accelerometer_not_exist);
        dialogBuilder.setPositiveButton(R.string.accept, this);
        dialogBuilder.create().show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        dialog.dismiss();
    }

    @Override
    public void onItemClick(View view, int position)
    {
        if(position==0 && !hasAccelerometer())
        {
            createDialogAccelerometer();
            return;
        }
        if(position==1 && !hasGPS())
        {
            createDialogGPS();
            return;
        }
        if(position==2 && !hasLightSensor())
        {
            createDialogLight();
            return;
        }
        Intent i = new Intent(getActivity(), SensorActivity.class);
        i.putExtra("typeSensor", position);
        startActivity(i);
    }
}
