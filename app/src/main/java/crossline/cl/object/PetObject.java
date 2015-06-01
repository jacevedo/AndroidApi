package crossline.cl.object;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by jacevedo on 30-12-14.
 */

public class PetObject
{
    @Element
    private int code;
    @Element
    private String petName;
    @Element
    private String petOwner;
    @Element
    private String petType;
    @Element
    private String petBreed;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getPetName()
    {
        return petName;
    }

    public void setPetName(String petName)
    {
        this.petName = petName;
    }

    public String getPetOwner()
    {
        return petOwner;
    }

    public void setPetOwner(String petOwner)
    {
        this.petOwner = petOwner;
    }

    public String getPetType()
    {
        return petType;
    }

    public void setPetType(String petType)
    {
        this.petType = petType;
    }

    public String getPetBreed()
    {
        return petBreed;
    }

    public void setPetBreed(String petBreed)
    {
        this.petBreed = petBreed;
    }

    @Override
    public String toString()
    {
        return getCode()+"-"+getPetName();
    }
}
