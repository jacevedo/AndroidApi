package crossline.cl.object.google.objects;

/**
 * Created by jacevedo on 02-01-15.
 */
public class Bounds
{
    private Location northeast;
    private Location southwest;

    public Location getNortheast()
    {
        return northeast;
    }

    public void setNortheast(Location northeast)
    {
        this.northeast = northeast;
    }

    public Location getSouthwest()
    {
        return southwest;
    }

    public void setSouthwest(Location southwest)
    {
        this.southwest = southwest;
    }
}
