package crossline.cl.fragment.map;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.helper.MapHelper;
import crossline.cl.object.google.objects.FindAddress;
import crossline.cl.object.google.objects.Location;
import crossline.cl.portafolio.R;


public class PutAddressFragment extends Fragment implements View.OnClickListener
{
    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    private Dialog customDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_put_address, container, false);
        setHasOptionsMenu(true);
        initComponents();
        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(!((BaseFragmentActivity)getActivity()).isOnline())
        {
            getActivity().onBackPressed();
        }
    }

    private void initComponents()
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
                .target(madrid)
                .zoom(10)
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.animateCamera(camUpd3);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_map,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.search_address:
                openDialogSearchAddress();
                break;

        }
        return false;
    }

    private void openDialogSearchAddress()
    {
        customDialog = new Dialog(getActivity());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.search_address);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationZoom;
        customDialog.findViewById(R.id.btnSearchAddress).setOnClickListener(this);
        customDialog.show();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnSearchAddress:
                String address = ((EditText)((View)v.getParent()).
                                                findViewById(R.id.edtAddress)).getText().toString();
                new SearchAddressAsync().execute(address);
                break;
        }
    }
    private class SearchAddressAsync extends AsyncTask<String, Void,FindAddress>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute()
        {
            customDialog.dismiss();
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle(R.string.searching);
            dialog.setMessage(getString(R.string.searching_address));
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected FindAddress doInBackground(String... params)
        {
            MapHelper helper = new MapHelper();
            return helper.getLatLng(params[0]);
        }

        @Override
        protected void onPostExecute(FindAddress address)
        {
            dialog.dismiss();
            mMap.addMarker(createMarker (address.getResults()[0].getFormatted_address(),
                                         address.getResults()[0].getGeometry().getLocation()));
            positionCamera(address.getResults()[0].getGeometry().getLocation());
            super.onPostExecute(address);
        }
    }

    private void positionCamera(Location location)
    {
        LatLng madrid = new LatLng(location.getLat(),location.getLng());
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)   //Centramos el mapa en Madrid
                .zoom(16)         //Establecemos el zoom en 19
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        mMap.animateCamera(camUpd3);
    }

    private MarkerOptions createMarker(String formatted_address, Location location)
    {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(location.getLat(),location.getLng()));
        options.title(formatted_address);
        return options;
    }

}
