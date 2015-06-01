package crossline.cl.object.google.objects;

/**
 * Created by jacevedo on 02-01-15.
 */
public class Results
{
    private AddressComponents[] address_components;
    private String formatted_address;
    private Geometry geometry;

    public AddressComponents[] getAddress_components()
    {
        return address_components;
    }

    public void setAddress_components(AddressComponents[] address_components)
    {
        this.address_components = address_components;
    }

    public String getFormatted_address()
    {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address)
    {
        this.formatted_address = formatted_address;
    }

    public Geometry getGeometry()
    {
        return geometry;
    }

    public void setGeometry(Geometry geometry)
    {
        this.geometry = geometry;
    }
}
