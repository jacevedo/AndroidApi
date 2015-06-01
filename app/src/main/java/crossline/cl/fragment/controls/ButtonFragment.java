package crossline.cl.fragment.controls;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import crossline.cl.portafolio.R;

public class ButtonFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener
{
    private ToggleButton btnToggle;
    private Switch btnSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_button, container, false);
        initElement(v);
        return v;
    }

    private void initElement(View v)
    {
        btnToggle = (ToggleButton)v.findViewById(R.id.btnToggle);
        btnSwitch = (Switch)v.findViewById(R.id.btnSwitch);

        btnToggle.setOnCheckedChangeListener(this);
        btnSwitch.setOnCheckedChangeListener(this);

        setTextButtons();

        v.findViewById(R.id.btnHelloWorld).setOnClickListener(this);
        v.findViewById(R.id.btnCheckState).setOnClickListener(this);
    }

    private void setTextButtons()
    {
        btnSwitch.setTextOff(getString(R.string.text_off));
        btnSwitch.setTextOn(getString(R.string.text_on));

        btnToggle.setTextOff(getString(R.string.text_off));
        btnToggle.setTextOn(getString(R.string.text_on));
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnHelloWorld:
                Toast.makeText(getActivity(), getString(R.string.hello_world), Toast.LENGTH_SHORT).show();
            break;
            case R.id.btnCheckState:
                Toast.makeText(getActivity(),
                        "Switch " + (btnSwitch.isChecked()?checked():notChecked())+"\nToggle " +
                       (btnToggle.isChecked()?checked():notChecked()),Toast.LENGTH_SHORT).show();
                 break;
        }
    }
    private String checked()
    {
        return getString(R.string.checked);
    }
    private String notChecked()
    {
        return getString(R.string.not_checked);
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        switch (buttonView.getId())
        {
            case R.id.btnSwitch:
                Toast.makeText(getActivity(), "Switch " + (isChecked?checked():notChecked()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnToggle:
                Toast.makeText(getActivity(), "Toggle " + (isChecked?checked():notChecked()),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
