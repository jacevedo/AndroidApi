package crossline.cl.portafolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import crossline.cl.adapter.RecicleViewAdapter;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.fragment.sqlite.DeleteSQLiteFragment;
import crossline.cl.fragment.sqlite.InsertFragment;
import crossline.cl.fragment.sqlite.ListPetInserted;
import crossline.cl.fragment.sqlite.UpdateSQLiteFragment;


public class SQLiteActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener
{

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initElements();
        if(savedInstanceState==null)
        {
            int option = getIntent().getIntExtra("typeDB",-1);
            selectItem(option);
        }
    }

    private void selectItem(int option)
    {
        Fragment mFragment = getFragment(option);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.principalContent, mFragment).commit();
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private Fragment getFragment(int option)
    {
        switch (option)
        {
            case 0:
                return new InsertFragment();
            case 1:
                return new ListPetInserted();
            case 2:
                return new UpdateSQLiteFragment();
            case 3:
                return new DeleteSQLiteFragment();
            default:
                return null;
        }
    }

    private void initElements()
    {
        mLayoutManager = new LinearLayoutManager(this);
        mDrawerList.setLayoutManager(mLayoutManager);

        mDrawerList.setAdapter(new RecicleViewAdapter(getHomeList()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        selectItem(position);
    }
    public String[] getPetType()
    {
        return new String[]{"none",getString(R.string.dog),
                getString(R.string.cat)};
    }
    public String[]getDogRace()
    {
        return new String[]{"none","Akita Inu","American Bulldog","American Pit Bull Terrier",
                "Andalusian Hound","Armant","Artois Hound","Australian Silky Terrier",
                "Basset Hound","Beagle","Billy","Boston Terrier","Mudi"};
    }
    public String[]getCatRace()
    {
        return new String[]{"none","American Shorthair","American Wirehair","Arabian Mau",
                "Asian","Balinese","Brazilian Shorthair","Burmese",
                "Chausie","Devon Rex","Egyptian Mau","Japanese Bobtail","Napoleon"};
    }
}
