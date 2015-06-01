package crossline.cl.fragment.controls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import crossline.cl.portafolio.R;

/**
 * Created by jacevedo on 29-12-14.
 */
public class RadioButtonFragment extends Fragment implements View.OnClickListener
{
    private RadioGroup rdbGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_radio_button, container, false);
        rdbGroup = (RadioGroup)v.findViewById(R.id.rdbGroup);
        (v.findViewById(R.id.btnCheck)).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnCheck:
                Toast.makeText(getActivity(),getTextCheck(v),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String getTextCheck(View v)
    {
        int selectedOptionId = rdbGroup.getCheckedRadioButtonId();
        String respuesta = "";
        if(selectedOptionId!=-1)
        {
            RadioButton button = (RadioButton) ((View) v.getParent()).findViewById(selectedOptionId);
            respuesta = button.getText().toString();
        }
        else
        {
            respuesta = "No se ha seleccionado nada";
        }
        return respuesta;
    }
}
