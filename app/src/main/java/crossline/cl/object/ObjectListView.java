package crossline.cl.object;

public class ObjectListView
{
    private int sourceImg;
    private String textList;

    public ObjectListView(int sourceImg, String textList)
    {
        this.sourceImg = sourceImg;
        this.textList = textList;
    }

    public int getSourceImg()
    {
        return sourceImg;
    }

    public void setSourceImg(int sourceImg)
    {
        this.sourceImg = sourceImg;
    }

    public String getTextList()
    {
        return textList;
    }

    public void setTextList(String textList)
    {
        this.textList = textList;
    }
}
