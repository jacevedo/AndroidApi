package crossline.cl.fragment.map;

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
import com.google.android.gms.maps.model.MarkerOptions;

import crossline.cl.portafolio.R;

public class MapMarkerFragment extends Fragment implements GoogleMap.OnMapClickListener
{
    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_map_marker, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
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
                //.bearing(45)      //Establecemos la orientación con el noreste arriba
               // .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.animateCamera(camUpd3);
        mMap.setOnMapClickListener(MapMarkerFragment.this);
    }

    @Override
    public void onMapClick(LatLng latLng)
    {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.title("Latitude: " + latLng.latitude + "\nLongitude: " + latLng.longitude);
        mMap.addMarker(options);

    }
}
