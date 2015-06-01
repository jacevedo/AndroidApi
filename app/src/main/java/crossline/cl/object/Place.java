package crossline.cl.object;

/**
 * Created by jacevedo on 02-01-15.
 */
public class Place
{
    private String client;
    private String address;
    private double lat;
    private double lng;
    private boolean selected;
    private int distance;

    public Place(String client, String address)
    {
        this.client = client;
        this.address = address;
        this.lat = 0;
        this.lng = 0;
        this.selected = false;
        this.distance = 0;

    }

    public Place(String client, double lat, double lon)
    {
        this.client = client;
        this.lat = lat;
        this.lng = lon;
        this.selected = false;
        this.distance = 0;
    }

    public Place(String client, String address, double lat, double lon)
    {
        this.client = client;
        this.address = address;
        this.lat = lat;
        this.lng = lon;
        this.selected = false;
        this.distance = 0;
    }
    public Place()
    {
        this.client = "";
        this.address = "";
        this.lat = 0;
        this.lng = 0;
        this.selected = false;
        this.distance = 0;
    }

    public String getClient()
    {
        return client;
    }

    public void setClient(String client)
    {
        this.client = client;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }
}
