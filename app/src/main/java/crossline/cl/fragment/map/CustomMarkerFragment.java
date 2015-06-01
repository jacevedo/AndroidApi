package crossline.cl.fragment.map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import crossline.cl.portafolio.R;


public class CustomMarkerFragment extends Fragment implements GoogleMap.OnMapClickListener, GoogleMap.InfoWindowAdapter
{
    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;
    private int icon = 0;
    private ArrayList<Marker> listMarkers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_custom_marker, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        listMarkers = new ArrayList();
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
        mMap.setOnMapClickListener(CustomMarkerFragment.this);
        mMap.setInfoWindowAdapter(CustomMarkerFragment.this);
    }

    @Override
    public void onMapClick(LatLng latLng)
    {

        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.title("Latitude: " + latLng.latitude + "\nLongitude: " + latLng.longitude);
        options.icon(getImageBitmap());
        listMarkers.add(mMap.addMarker(options));
    }

    public BitmapDescriptor getImageBitmap()
    {
        BitmapDescriptor bitmap = null;
        switch (icon)
        {
            case 0:
                bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker0);
                break;
            case 1:
                bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker1);
                break;
            case 2:
                bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker2);
                break;
            case 3:
                bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker3);
                break;
        }
        if(icon == 3)
        {
            icon = 0;
        }
        else
        {
            icon++;
        }
        return bitmap;
    }

    @Override
    public View getInfoWindow(Marker marker)
    {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
        View v  = getActivity().getLayoutInflater().inflate(R.layout.marker_windows, null);
        ImageView imgMarker = (ImageView)v.findViewById(R.id.imgMarker);
        TextView txtTextMarker = (TextView)v.findViewById(R.id.txtTextMarker);
        txtTextMarker.setText("Latitude: " + marker.getPosition().latitude + "\nLongitude: " +
                                marker.getPosition().longitude);
        imgMarker.setImageResource(getImageResource(marker));

        return v;
    }

    private int getImageResource(Marker mainMarker)
    {
        int image = 0;
        int cont = 0;
        for(Marker marker : listMarkers)
        {
           if(marker.getPosition().latitude == mainMarker.getPosition().latitude &&
               marker.getPosition().longitude == mainMarker.getPosition().longitude )
            {
                switch (cont)
                {
                    case 0:
                        image = R.drawable.marker0;
                        break;
                    case 1:
                        image = R.drawable.marker1;
                        break;
                    case 2:
                        image = R.drawable.marker2;
                        break;
                    case 3:
                        image = R.drawable.marker3;
                        break;
                }
            }

            if(cont==3)
            {
                cont = 0;
            }
            else
            {
                cont++;
            }
        }
        return image;
    }
}
