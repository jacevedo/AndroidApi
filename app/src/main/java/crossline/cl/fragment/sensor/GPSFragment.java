package crossline.cl.fragment.sensor;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import crossline.cl.portafolio.R;


public class GPSFragment extends Fragment implements LocationListener
{
    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    private Marker marker;
    private LocationManager locationManager;
    private boolean isFirstTime = true;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        final LocationManager manager = (LocationManager) getActivity().getSystemService( Context.LOCATION_SERVICE );
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            getActivity().onBackPressed();
        }
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                3000,   // 3 sec
                10, this);
        isFirstTime = true;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_gps, container, false);
        initComponents(v);
        return v;
    }

    private void initComponents(View v)
    {
        initMap();
        getChildFragmentManager().beginTransaction().add(R.id.layout_map, mMapFragment).commit();

    }

    private void initMap()
    {
        mMapFragment = new SupportMapFragment()
        {
            @Override
            public void onActivityCreated(Bundle savedInstanceState)
            {
                super.onActivityCreated(savedInstanceState);
                mMap = mMapFragment.getMap();
                if (mMap != null)
                {
                    setupMap();
                }
            }
        };
    }

    private void setupMap()
    {
        LatLng madrid = new LatLng(-33.6682982,-70.363372);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)   //Centramos el mapa en Madrid
                .zoom(10)         //Establecemos el zoom en 19
                .build();

        CameraUpdate camUpd3 =
                    CameraUpdateFactory.newCameraPosition(camPos);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.animateCamera(camUpd3);
        marker = mMap.addMarker(createMarker());
    }

    private MarkerOptions createMarker()
    {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude(),
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude()));
        options.title(getString(R.string.my_position));
        return options;
    }


    @Override
    public void onLocationChanged(Location location)
    {
        marker.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
        if(isFirstTime)
        {
            CameraPosition camPos = new CameraPosition.Builder()
                    .target(marker.getPosition())
                    .zoom(16)
                    .build();

            CameraUpdate camUpd3 =
                    CameraUpdateFactory.newCameraPosition(camPos);
            mMap.animateCamera(camUpd3);
            isFirstTime = false;
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }
}
