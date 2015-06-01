package crossline.cl.fragment.controls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import crossline.cl.portafolio.R;

public class TextViewFragment extends Fragment implements View.OnClickListener
{
    private TextView txtTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_text_view, container, false);
        txtTextView = (TextView)v.findViewById(R.id.txtTextView);
        (v.findViewById(R.id.btnChangueText)).setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnChangueText:
                txtTextView.setText(txtTextView.getText()+" Hello");
                break;
        }
    }
}
