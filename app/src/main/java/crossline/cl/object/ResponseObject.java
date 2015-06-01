package crossline.cl.object;

/**
 * Created by jacevedo on 02-01-15.
 */
public class ResponseObject
{
    private int inserted_id;
    private String inserted;

    public String getInserted()
    {
        return inserted;
    }

    public void setInserted(String inserted)
    {
        this.inserted = inserted;
    }

    public int getInsertedId()
    {
        return inserted_id;
    }

    public void setInsertedId(int inserted_id)
    {
        this.inserted_id = inserted_id;
    }
}
