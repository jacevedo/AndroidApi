package crossline.cl.fragment.controls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import crossline.cl.adapter.AdapterExample;
import crossline.cl.object.ObjectListView;
import crossline.cl.portafolio.R;

public class ListViewFragment extends Fragment implements AdapterView.OnItemClickListener
{
    private ListView listView;
    private AdapterExample adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_list_view, container, false);
        initElements(v);
        return v;
    }

    private void initElements(View v)
    {
        adapter = new AdapterExample(getActivity(),getListData());
        listView = (ListView)v.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private ArrayList<ObjectListView> getListData()
    {
        ArrayList<ObjectListView> listObjects = new ArrayList<ObjectListView>();
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.first_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.second_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.third_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.fourth_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.fifth_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.sixth_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.seventh_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.eighth_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.ninth_object)));
        listObjects.add(new ObjectListView(R.drawable.ic_launcher,getString(R.string.tenth_object)));
        return listObjects;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(getActivity(), ((ObjectListView) adapter.getItem(position)).getTextList(),
                                                                        Toast.LENGTH_SHORT).show();
    }
}
