package crossline.cl.object;

/**
 * Created by jacevedo on 02-01-15.
 */
public class RequestObject
{
    private int code;
    private String device;
    private String name_pet;
    private String name_owner;
    private String pet_type;
    private String race;

    public RequestObject()
    {
    }

    public RequestObject(int code, String device, String name_pet, String name_owner, String pet_type, String race)
    {
        this.code = code;
        this.device = device;
        this.name_pet = name_pet;
        this.name_owner = name_owner;
        this.pet_type = pet_type;
        this.race = race;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getDevice()
    {
        return device;
    }

    public void setDevice(String device)
    {
        this.device = device;
    }

    public String getName_pet()
    {
        return name_pet;
    }

    public void setName_pet(String name_pet)
    {
        this.name_pet = name_pet;
    }

    public String getName_owner()
    {
        return name_owner;
    }

    public void setName_owner(String name_owner)
    {
        this.name_owner = name_owner;
    }

    public String getPet_type()
    {
        return pet_type;
    }

    public void setPet_type(String pet_type)
    {
        this.pet_type = pet_type;
    }

    public String getRace()
    {
        return race;
    }

    public void setRace(String race)
    {
        this.race = race;
    }

    public void fillRequestObject(PetObject pet)
    {
        this.code = pet.getCode();
        this.device = "Android";
        this.name_pet = pet.getPetName();
        this.name_owner = pet.getPetOwner();
        this.pet_type = pet.getPetType();
        this.race = pet.getPetBreed();
    }
}
