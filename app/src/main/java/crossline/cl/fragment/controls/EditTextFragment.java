package crossline.cl.fragment.controls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import crossline.cl.portafolio.R;

public class EditTextFragment extends Fragment implements View.OnClickListener
{
    private EditText edtText;
    private TextView txtTextChange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_edit_text, container, false);
        edtText = (EditText)v.findViewById(R.id.edtText);
        txtTextChange = (TextView)v.findViewById(R.id.txtTextChange);
        (v.findViewById(R.id.btnReadText)).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnReadText:
                txtTextChange.setText(edtText.getText());
                break;
        }
    }
}
