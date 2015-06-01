package crossline.cl.fragment.controls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import crossline.cl.portafolio.R;

public class AutoCompleteFragment extends Fragment implements AdapterView.OnItemClickListener
{

    private String[] countries;
    private AutoCompleteTextView edtAutoComplete;
    private MultiAutoCompleteTextView edtMultiAutoComplete;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_auto_complete, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        countries = getResources().
                getStringArray(R.array.list_of_countries);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,
                                                                                        countries);
        edtAutoComplete =((AutoCompleteTextView)v.findViewById(R.id.edtAutoComplete));
        edtAutoComplete.setAdapter(adapter);
        edtAutoComplete.setOnItemClickListener(this);

        edtMultiAutoComplete = (MultiAutoCompleteTextView)v.findViewById(R.id.edtMultiAutoComplete);
        edtMultiAutoComplete.setAdapter(adapter);
        edtMultiAutoComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        edtAutoComplete.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(getActivity(),((TextView)view).getText(), Toast.LENGTH_SHORT).show();

    }
}
