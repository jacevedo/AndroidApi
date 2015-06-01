package crossline.cl.fragment.customviews;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import crossline.cl.adapter.AdapterList;
import crossline.cl.listener.OnLetterClick;
import crossline.cl.portafolio.R;
import crossline.cl.views.CustomAlphabetical;

public class AlphabeticalOrderFragment extends Fragment implements OnLetterClick, View.OnClickListener
{
    private Button btnShow;
    private CustomAlphabetical alphabetical;
    private ListView lstExample;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_alphabetical_order, container, false);
        initComponents(v);
        return v;
    }

    private void initComponents(View v)
    {

        btnShow = (Button)v.findViewById(R.id.btnShowView);
        alphabetical = (CustomAlphabetical)v.findViewById(R.id.customAlphabetical);
        lstExample = (ListView)v.findViewById(R.id.lstExample);

        lstExample.setAdapter(new AdapterList(getList(),getActivity(),alphabetical));


        alphabetical.setLetterColor(Color.GREEN);
        alphabetical.setSquareColor(Color.BLUE);
        alphabetical.setOnLetterClickListener(this);
        alphabetical.setEnableList(getList());
        btnShow.setOnClickListener(this);
    }
    private ArrayList<String> getList()
    {
        ArrayList<String> listAdapter = new ArrayList<String>();
        listAdapter.add("a");
        listAdapter.add("aa");
        listAdapter.add("aa");
        listAdapter.add("b");
        listAdapter.add("bb");
        listAdapter.add("bb");
        listAdapter.add("e");
        listAdapter.add("ee");
        listAdapter.add("eee");
        listAdapter.add("q");
        listAdapter.add("qq");
        listAdapter.add("qqq");
        listAdapter.add("t");
        listAdapter.add("tt");
        listAdapter.add("ttt");
        listAdapter.add("y");
        listAdapter.add("yy");
        listAdapter.add("yyy");
        listAdapter.add("o");
        listAdapter.add("o");
        listAdapter.add("ooo");
        listAdapter.add("w");
        listAdapter.add("ww");
        listAdapter.add("www");
        listAdapter.add("r");
        listAdapter.add("rr");
        listAdapter.add("rrr");
        listAdapter.add("m");
        listAdapter.add("mm");
        listAdapter.add("mmm");
        listAdapter.add("v");
        listAdapter.add("vv");
        listAdapter.add("vvv");
        listAdapter.add("z");
        listAdapter.add("zz");
        listAdapter.add("zzz");
        listAdapter.add("ñ");
        listAdapter.add("ññ");

        return listAdapter;
    }

    @Override
    public void onLetterClickListener(CustomAlphabetical view, char letterClick, boolean isEnabled)
    {
        if(isEnabled)
        {
            int position =  getPosition(letterClick);
            lstExample.setSelection(position);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnShowView:
                alphabetical.setShowView(true);
                break;
        }
    }
    private int getPosition(char letterClick)
    {
        ArrayList<String> listString = getList();
        Collator esCollator = Collator.getInstance(new Locale("es"));
        Collections.sort(listString, esCollator);
        for (int i=0;i<listString.size();i++)
        {
            if(listString.get(i).charAt(0)==letterClick)
            {
                return i;
            }
        }
        return 0;
    }
}
