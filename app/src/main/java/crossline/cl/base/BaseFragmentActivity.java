package crossline.cl.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import crossline.cl.adapter.RecicleViewAdapter;
import crossline.cl.portafolio.R;

/**
 * Created by jacevedo on 29-12-14.
 */
public class BaseFragmentActivity extends ActionBarActivity
{
    protected ActionBarDrawerToggle mDrawerToggle;
    protected RecyclerView mDrawerList;
    protected DrawerLayout mDrawerLayout;
    protected CharSequence mDrawerTitle;
    protected CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_app);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initElements();
        mTitle = mDrawerTitle = getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        )
        {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    private void initElements()
    {
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView)findViewById(R.id.navigation);

    }
    public boolean isOnline()
    {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String [] getControlsList()
    {
        return new String[]{getString(R.string.button_example),
                            getString(R.string.text_view_example),
                            getString(R.string.edit_text_example),
                            getString(R.string.list_view_example),
                            getString(R.string.radio_button_example),
                            getString(R.string.check_box_example),
                            getString(R.string.images_example),
                            getString(R.string.auto_complete_example),
                            getString(R.string.progress_and_seek_bar),
                            getString(R.string.web_view_example)};
    }

    public String[] getAnimationList()
    {
        return new String[]{getString(R.string.fade_anim_example),
                            getString(R.string.rotate_anim_example),
                            getString(R.string.translate_anim_example),
                            getString(R.string.rotate_anim_example)+" V2",
                            getString(R.string.scale_anim_example)};
    }
    public String[] getHomeList()
    {
        return new String[]{getString(R.string.home),
                            getString(R.string.item_one),
                            getString(R.string.item_two),
                            getString(R.string.dialog),
                            getString(R.string.multimedia),
                            getString(R.string.data_base),
                            getString(R.string.xml_json),
                            getString(R.string.rest_connections),
                            getString(R.string.maps), //Retirados por eliminacion del proyecto en google console
                            getString(R.string.sensor),
                            getString(R.string.custom_view),
                            getString(R.string.widget)};
    }
    public String[] getMarshallingList()
    {
        return new String[]{getString(R.string.json_serialization),
                            getString(R.string.json_deserializacion),
                            getString(R.string.xml_serialization),
                            getString(R.string.xml_deserizalizacion)};
    }
    public String[] getDialogList()
    {
        return new String[]{getString(R.string.normal_dialog),
                            getString(R.string.custom_dialog),
                            getString(R.string.transparent_background),
                            getString(R.string.zoom_transition),
                            getString(R.string.translate_transicion)};
    }
    public String[] getMultimediaList()
    {
        return new String[]{getString(R.string.open_camera_picture),
                            getString(R.string.take_picture),
                            getString(R.string.open_picture),
                            getString(R.string.open_camera_video),
                            getString(R.string.open_video),
                            getString(R.string.take_audio),
                            getString(R.string.open_audio),
                            getString(R.string.open_pdf),
                            getString(R.string.open_office_file)};
    }
    public String[] getSQLiteList()
    {
        return new String[]{getString(R.string.insert_sqlite),
                            getString(R.string.list_sqlite),
                            getString(R.string.update_sqlite),
                            getString(R.string.delete_sqlite)};
    }
    public String[] getRestConnectionList()
    {
        return new String[]{getString(R.string.get_request),
                            getString(R.string.post_request),
                            getString(R.string.upload_image)};
    }
    public String[] getMapList()
    {
        return new String[]{getString(R.string.map_marker),
                            getString(R.string.customMarker),
                            getString(R.string.get_address)};
    }
    public String[] getSensorList()
    {
        return new String[]{getString(R.string.accelerometer),
                "GPS",
                getString(R.string.light)};
    }
    public String[] getViewsList()
    {
        return new String[]{getString(R.string.calendar),
                getString(R.string.alphabetical_order),
                getString(R.string.custom_graph)};
    }
}
