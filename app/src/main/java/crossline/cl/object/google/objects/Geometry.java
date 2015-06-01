package crossline.cl.object.google.objects;

/**
 * Created by jacevedo on 02-01-15.
 */
public class Geometry
{
    private Bounds bounds;
    private Location location;
    private String location_type;
    private Bounds viewport;

    public Bounds getBounds()
    {
        return bounds;
    }

    public void setBounds(Bounds bounds)
    {
        this.bounds = bounds;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public String getLocation_type()
    {
        return location_type;
    }

    public void setLocation_type(String location_type)
    {
        this.location_type = location_type;
    }

    public Bounds getViewport()
    {
        return viewport;
    }

    public void setViewport(Bounds viewport)
    {
        this.viewport = viewport;
    }
}
