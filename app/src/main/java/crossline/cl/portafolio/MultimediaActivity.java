package crossline.cl.portafolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import crossline.cl.adapter.RecicleViewAdapter;
import crossline.cl.helper.OpenFiles;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.fragment.multimedia.AudioRecorderFragment;
import crossline.cl.fragment.multimedia.CustomCameraFragment;
import crossline.cl.fragment.multimedia.OpenAudioFragment;
import crossline.cl.fragment.multimedia.OpenCameraPictureFragment;
import crossline.cl.fragment.multimedia.OpenCameraVideoFragment;
import crossline.cl.fragment.multimedia.OpenPictureFragment;
import crossline.cl.fragment.multimedia.OpenVideoFragment;


public class MultimediaActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener
{
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initElements();
        if(savedInstanceState==null)
        {
            int option = getIntent().getIntExtra("typeControl",-1);
            selectItem(option);
        }
    }

    private void selectItem(int option)
    {
        if(option == 7)
        {
            openPdf();
            finish();
        }
        else if(option == 8)
        {
            openXls();
            finish();
        }
        else
        {
            Fragment mFragment = getFragment(option);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.principalContent, mFragment).commit();
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }



    private Fragment getFragment(int option)
    {
        switch (option)
        {
            case 0:
                return new OpenCameraPictureFragment();
            case 1:
                return new CustomCameraFragment();
            case 2:
                return new OpenPictureFragment();
            case 3:
                return new OpenCameraVideoFragment();
            case 4:
                return new OpenVideoFragment();
            case 5:
                return new AudioRecorderFragment();
            case 6:
                return new OpenAudioFragment();
            default:
                return null;
        }
    }

    private void openPdf()
    {
        OpenFiles openFile = new OpenFiles(this);
        openFile.pdfOpen("producto_ii_v3.pdf");
    }
    private void openXls()
    {
        OpenFiles openFile = new OpenFiles(this);
        openFile.xlsOpen("Libro1.xlsx");
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
}
