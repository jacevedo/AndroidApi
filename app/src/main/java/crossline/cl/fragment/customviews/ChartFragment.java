package crossline.cl.fragment.customviews;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import crossline.cl.portafolio.R;
import crossline.cl.views.ChartView;

public class ChartFragment extends Fragment implements View.OnClickListener
{
    private ChartView mChartView;
    private Button btnVertical;
    private Button btn90;
    private Button btn240;
    private Button btnPoint;
    private Button btnBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_chart, container, false);
        initElements(v);
        return v;
    }

    private void initElements(View v)
    {
        mChartView = (ChartView)v.findViewById(R.id.mChart);
        mChartView.setLeftTitle(getString(R.string.days_example));
        mChartView.setBottomTitle("");
        mChartView.setTextOrientation(ChartView.TextOrientation.ROTATE_90);
        mChartView.setListNumber(getListNumber());
        mChartView.setListBottomTitle(getNamesList());
        mChartView.setListColor(getListColor());
        mChartView.setGraphicType(ChartView.GraphicType.BAR);

        btnVertical = (Button)v.findViewById(R.id.btnVertical);
        btn90 = (Button)v.findViewById(R.id.btn90);
        btn240 = (Button)v.findViewById(R.id.btn240);
        btnPoint = (Button)v.findViewById(R.id.btnPuntos);
        btnBar = (Button)v.findViewById(R.id.btnBarras);

        btnVertical.setOnClickListener(this);
        btn90.setOnClickListener(this);
        btn240.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnBar.setOnClickListener(this);
    }

    private ArrayList<Integer> getListColor()
    {
        ArrayList<Integer> listColor = new ArrayList<Integer>();
        listColor.add(Color.BLUE);
        listColor.add(Color.BLACK);
        listColor.add(Color.GREEN);
        return listColor;
    }
    private ArrayList<ArrayList<Float>> getListNumber()
    {
        ArrayList<ArrayList<Float>> listFloat = new ArrayList<ArrayList<Float>>();

        ArrayList<Float> numbers = new ArrayList<Float>();
        ArrayList<Float> numbers2 = new ArrayList<Float>();
        ArrayList<Float> numbers3 = new ArrayList<Float>();
        ArrayList<Float> numbers4 = new ArrayList<Float>();

        numbers.add(1f);
        numbers.add(2f);
        numbers.add(3f);
        numbers.add(4f);
        numbers.add(5f);
        numbers.add(6f);
        numbers.add(7f);
        numbers.add(8f);
        numbers.add(9f);
        numbers.add(10f);
        numbers.add(11f);
        numbers.add(12f);

        numbers2.add(12f);
        numbers2.add(11f);
        numbers2.add(10f);
        numbers2.add(9f);
        numbers2.add(8f);
        numbers2.add(7f);
        numbers2.add(6f);
        numbers2.add(5f);
        numbers2.add(4f);
        numbers2.add(3f);
        numbers2.add(2f);
        numbers2.add(1f);

        numbers3.add(10f);
        numbers3.add(12f);
        numbers3.add(9f);
        numbers3.add(4f);
        numbers3.add(1f);
        numbers3.add(6f);
        numbers3.add(5f);
        numbers3.add(6f);
        numbers3.add(9f);
        numbers3.add(4f);
        numbers3.add(1f);
        numbers3.add(120f);

        listFloat.add(numbers);
        listFloat.add(numbers2);
        listFloat.add(numbers3);
        return listFloat;
    }
    private ArrayList<String> getNamesList()
    {
        ArrayList<String> namesList = new ArrayList<String>();
        namesList.add("Enero");
        namesList.add("Febrero");
        namesList.add("Marzo");
        namesList.add("Abril");
        namesList.add("Mayo");
        namesList.add("Junio");
        namesList.add("Julio");
        namesList.add("Agosto");
        namesList.add("Septiembre");
        namesList.add("Octubre");
        namesList.add("Noviembre");
        namesList.add("Diciembre");
        return namesList;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnVertical:
                mChartView.setTextOrientation(ChartView.TextOrientation.VERTICAL);
                break;
            case R.id.btn90:
                mChartView.setTextOrientation(ChartView.TextOrientation.ROTATE_90);
                break;
            case R.id.btn240:
                mChartView.setTextOrientation(ChartView.TextOrientation.ROTATE_270);
                break;
            case R.id.btnPuntos:
                mChartView.setGraphicType(ChartView.GraphicType.POINT);
                break;
            case R.id.btnBarras:
                mChartView.setGraphicType(ChartView.GraphicType.BAR);
                break;
        }
    }
}
