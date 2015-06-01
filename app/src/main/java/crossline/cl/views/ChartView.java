package crossline.cl.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Jaime on 08/09/2014.
 */
public class ChartView extends View
{
    private float height;
    private float width;
    private Paint mPaintLines;
    private Paint mPaintLeftText;
    private Paint mPaintBottomText;
    private Paint mPaintTitleText;
    private String leftTitle;
    private String bottomTitle;
    private float bottomTitleTextSize;
    private float titleLeftTextSize;
    private TextOrientation textOrientation;
    private ArrayList<ArrayList<Float>> listNumber;
    private ArrayList<String> listBottomTitle;
    private Float highValue;
    private ArrayList<Integer> listColor;
    private GraphicType graphicType;

    public ChartView(Context context)
    {
        super(context);
        init();
    }


    public ChartView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        getMeasured();
        drawGraphic(canvas);
    }
    private void drawGraphic(Canvas canvas)
    {

        switch (graphicType)
        {
            case POINT:
                drawCommonThinks(canvas);
                drawPoint(canvas);
                break;
            case BAR:
                drawCommonThinks(canvas);
                drawBar(canvas);
                break;
            case CIRCLE:
                drawCircle(canvas);
                break;
        }
    }

    private void drawCircle(Canvas canvas)
    {
        float diameter = Math.min(getHeight(),getWidth());
    }

    private void drawCommonThinks(Canvas canvas)
    {
        float[] arrayFloat = getLines();
        if(arrayFloat != null && arrayFloat.length!= 0 && arrayFloat.length%2==0)
        {
            canvas.drawLines(arrayFloat, mPaintLines);
        }
        drawLeftTitle(canvas);
        drawLeftNumbers(canvas);
        drawBottomText(canvas);
    }
    private void drawBar(Canvas canvas)
    {
        Paint mPaintValue = new Paint(Paint.ANTI_ALIAS_FLAG);

        float widthIntern = getWidth()- mPaintLines.getStrokeWidth();
        float innerWidth = ((widthIntern) - (width*2));
        int counter = 0;
        int counter2 = 0;
        int listNumberCount = listNumber.size();

        for(ArrayList<Float> list : listNumber)
        {

            if(listColor!=null && listColor.size()>counter)
            {
                mPaintValue.setColor(listColor.get(counter++));
            }
            else
            {
                mPaintValue.setColor(Color.RED);
            }
            for (int i = 0; i < list.size(); i++)
            {
                float actualWidth = innerWidth/ list.size();
                float startWidth  = (actualWidth * (i + 1)) + width  - (width/4);
                float finishWidth =  (actualWidth * (i + 1)) + width  + (width/4);

                float spaceWidth = (finishWidth - startWidth)/listNumberCount;

                float visibleStartWidth = startWidth + spaceWidth * counter2;
                float visibleFinishWidth = startWidth + (spaceWidth * (counter2+1));

                float finishHeight = height * 1;
                float startHeight = height * 9;


                float space = startHeight - finishHeight;

                float yAxy = list.get(i) * space / highValue;
                yAxy = startHeight - yAxy;

                canvas.drawRect(visibleStartWidth,yAxy,visibleFinishWidth,startHeight,mPaintValue);
            }
            counter2++;
        }
    }

    private void drawBottomText(Canvas canvas)
    {
        Paint mPaintValue = new Paint();
        mPaintValue.setTextSize(15);
        float widthIntern = getWidth()- mPaintLines.getStrokeWidth();
        float innerWidth = ((widthIntern) - (width*2));
        float actualWidth = innerWidth/listBottomTitle.size();
        float heightText = getHeight() - (height-mPaintValue.getTextSize());
        float widthActual;
        for(int i=0;i<listBottomTitle.size();i++)
        {
            widthActual = ((actualWidth * (i+1))+ width - ((listBottomTitle.get(i).length()*mPaintValue.getTextSize())/4));
            canvas.drawText(listBottomTitle.get(i),widthActual,heightText,mPaintValue);
        }
    }

    private void drawPoint(Canvas canvas)
    {
        Paint mPaintValue = new Paint(Paint.ANTI_ALIAS_FLAG);

        float widthIntern = getWidth()- mPaintLines.getStrokeWidth();
        float innerWidth = ((widthIntern) - (width*2));
        int counter = 0;
        if(listNumber!=null)
        {
            for (ArrayList<Float> list : listNumber)
            {
                if (listColor != null && listColor.size() > counter)
                {
                    mPaintValue.setColor(listColor.get(counter++));
                }
                else
                {
                    mPaintValue.setColor(Color.RED);
                }
                float actualWidth = innerWidth / list.size();
                float previousX = 0;
                float previousY = 0;
                for (int i = 0; i < list.size(); i++)
                {
                    float yAxy = 0;
                    float widthActual = (actualWidth * (i + 1)) + width;

                    float finishHeight = height * 1;
                    float startHeight = height * 9;
                    float space = startHeight - finishHeight;

                    yAxy = list.get(i) * space / highValue;
                    yAxy = startHeight - yAxy;

                    canvas.drawCircle(widthActual, yAxy, 8, mPaintValue);
                    if (i != 0)
                    {
                        canvas.drawLine(previousX, previousY, widthActual, yAxy, mPaintValue);
                    }
                    previousX = widthActual;
                    previousY = yAxy;

                }
            }
        }
    }
    private float getMostValue()
    {
        float x = 0;
        if(listNumber != null)
        {
            for (ArrayList<Float> list : listNumber)
            {
                for (float y : list)
                {
                    if (y >= x)
                    {
                        x = y;
                    }
                }
            }
        }
        return x;
    }
    private void drawLeftNumbers(Canvas canvas)
    {
        if(highValue!= null)
        {
            Paint mPaintValue = new Paint();
            mPaintValue.setTextSize(15);
            float subtractValue = highValue / 8;
            float value = highValue;
            int largo = (""+highValue).length()-2;
            for (int i = 0; i < 9; i++)
            {
                float heightActual = height * (i + 1);
                DecimalFormat df = new DecimalFormat("0.0");

                if (i != 8)
                {
                    canvas.drawText(df.format(value) + "", width - mPaintValue.getTextSize() * largo, heightActual + 5, mPaintValue);
                }
                else
                {
                    canvas.drawText(0 + "", width - mPaintValue.getTextSize() * 2, heightActual + 5, mPaintValue);
                }
                value = value - subtractValue;
            }
        }
    }
    private void drawLeftTitle(Canvas canvas)
    {
        if(titleLeftTextSize==0)
        {
            mPaintLeftText.setTextSize(getWidth() / 50);
        }
        else
        {
            mPaintLeftText.setTextSize(titleLeftTextSize);
        }
        if(leftTitle!=null && leftTitle.compareToIgnoreCase("")!=0)
        {
            float y = (getHeight() / 2) - ((mPaintLeftText.getTextSize() / 2) * leftTitle.length());
            switch (textOrientation)
            {

                case VERTICAL:
                    putTextVertical(canvas,y);
                    break;
                case ROTATE_90:
                    y = ((getHeight()* 20)/ 100) - ((mPaintLeftText.getTextSize() / 2) * leftTitle.length());
                    canvas.save();
                    canvas.rotate(90,getHeight()/2,getWidth()/2);
                    canvas.drawText(leftTitle,y,(getWidth()*77)/100,mPaintLeftText);
                    canvas.restore();
                    break;
                case ROTATE_270:
                    y = (getHeight() / 2) + ((mPaintLeftText.getTextSize() / 2) * leftTitle.length());
                    canvas.save();
                    canvas.rotate(-90,getHeight()/2,getWidth()/2);
                    canvas.drawText(leftTitle,y,getHeight()*40/100,mPaintLeftText);
                    canvas.restore();
                    break;
            }

        }
    }

    private void putTextVertical(Canvas canvas,float y)
    {
        float marginLeft = (getWidth()*1)/100;
        for (char c : leftTitle.toCharArray())
        {
            if (c == 'i' || c == 'f' || c == 'j' || c == 'l' || c == 'r' || c == 't' || c == 'I')
            {
                canvas.drawText(c + "", marginLeft+12, y, mPaintLeftText);
                y += mPaintLeftText.getTextSize();
            }
            else if (c == 'm' || c == 'w' || c == 'M' || c == 'W')
            {
                canvas.drawText(c + "", marginLeft+8, y, mPaintLeftText);
                y += mPaintLeftText.getTextSize();
            }
            else
            {
                canvas.drawText(c + "", marginLeft+10, y, mPaintLeftText);
                y += mPaintLeftText.getTextSize();
            }

        }
    }

    private void init()
    {
        graphicType = GraphicType.POINT;
        mPaintLines = new Paint();
        mPaintLines.setStrokeWidth(3);
        mPaintLines.setColor(Color.BLACK);

        mPaintLeftText = new Paint();
        mPaintLeftText.setColor(Color.BLACK);

        mPaintBottomText = new Paint();
        mPaintBottomText.setColor(Color.BLACK);

        mPaintTitleText = new Paint();
        mPaintTitleText.setColor(Color.BLACK);

        titleLeftTextSize = 0;
        textOrientation = TextOrientation.ROTATE_90;
        highValue = getMostValue();
        listBottomTitle = new ArrayList<String>();
    }

    private float[] getLines()
    {
        if(listBottomTitle!=null && listBottomTitle.size()!=0)
        {
            float[] listPoint = new float[32 + ((listBottomTitle.size() + 1) * 4) + 10];
            float widthIntern = getWidth() - mPaintLines.getStrokeWidth();
            float innerWidth = ((widthIntern) - (width * 2));
            float actualWidth = innerWidth / (listBottomTitle.size() );

            int counter = 0;//76
            float heightActual = 0;
            float widthActual = actualWidth * (listBottomTitle.size() + 1) + width;
            for (int i = 0; i < 9; i++)
            {
                heightActual = height * (i + 1);
                listPoint[counter++] = width;
                listPoint[counter++] = heightActual;
                listPoint[counter++] = widthActual;
                listPoint[counter++] = heightActual;
            }
            //float startX, float startY, float stopX, float stopY, Paint paint
            for (int i = 0; i < listBottomTitle.size() + 2; i++)
            {
                widthActual = actualWidth * i + width;
                listPoint[counter++] = widthActual;
                listPoint[counter++] = height;
                listPoint[counter++] = widthActual;
                listPoint[counter++] =  heightActual;
            }
            return listPoint;
        }
        else
        {
            float[] listPoint = new float[4];
            listPoint[0] = 1;
            listPoint[1] = 2;
            listPoint[2] = 3;
            listPoint[3] = 4;
            return listPoint;
        }


    }

    private void getMeasured()
    {
        height = getHeight()/10;
        width = getWidth()/10;
    }

    public String getLeftTitle()
    {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle)
    {
        this.leftTitle = leftTitle;
        invalidate();
        requestLayout();
    }

    public float getTitleLeftTextSize()
    {
        return titleLeftTextSize;
    }

    public void setTitleLeftTextSize(float titleLeftTextSize)
    {
        this.titleLeftTextSize = titleLeftTextSize;
        invalidate();
        requestLayout();
    }

    public String getBottomTitle()
    {
        return bottomTitle;
    }

    public void setBottomTitle(String bottomTitle)
    {
        this.bottomTitle = bottomTitle;
        invalidate();
        requestLayout();
    }

    public float getBottomTitleTextSize()
    {
        return bottomTitleTextSize;
    }

    public void setBottomTitleTextSize(float bottomTitleTextSize)
    {
        this.bottomTitleTextSize = bottomTitleTextSize;
        invalidate();
        requestLayout();
    }

    public TextOrientation getTextOrientation()
    {
        return textOrientation;
    }

    public void setTextOrientation(TextOrientation textOrientation)
    {
        this.textOrientation = textOrientation;
        invalidate();
        requestLayout();
    }
    public ArrayList<ArrayList<Float>> getListNumber()
    {
        return listNumber;
    }

    public void setListNumber(ArrayList<ArrayList<Float>> listNumber)
    {
        this.listNumber = listNumber;
        highValue = getMostValue();
        invalidate();
        requestLayout();
    }
    public void setListBottomTitle(ArrayList<String> listBottomTitle)
    {
        this.listBottomTitle = listBottomTitle;
        highValue = getMostValue();
        invalidate();
        requestLayout();
    }
    public void  addNumber(ArrayList<Float> number)
    {
        this.listNumber.add(number);
        highValue = getMostValue();
        invalidate();
        requestLayout();
    }
    public void setListColor(ArrayList<Integer>listColor)
    {
        this.listColor = listColor;
        invalidate();
        requestLayout();
    }
    public void setGraphicType(GraphicType graphicType)
    {
        this.graphicType = graphicType;
        invalidate();
        requestLayout();
    }
    public enum TextOrientation
    {
        VERTICAL,ROTATE_90,ROTATE_270;
    }
    public enum GraphicType
    {
        POINT, BAR ,CIRCLE;

    }
}
