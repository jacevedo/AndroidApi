package crossline.cl.fragment.controls;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import crossline.cl.portafolio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckBoxFragment extends Fragment implements View.OnClickListener
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_check_box, container, false);
        v.findViewById(R.id.btnCheckBox).setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCheckBox:
                Toast.makeText(getActivity(),getCountAndText(v),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String getCountAndText(View v)
    {
        RelativeLayout layout = (RelativeLayout)v.getParent();
        int count = 0;
        String text="";
        for(int i = 0; i<layout.getChildCount();i++)
        {
            if(layout.getChildAt(i) instanceof CheckBox && ((CheckBox)layout.getChildAt(i)).isChecked())
            {
                count++;
                text = text + "\nText Nº " + i + " " + ((CheckBox)layout.getChildAt(i)).getText();
            }
        }
        return count +" selected " + text;
    }
}
