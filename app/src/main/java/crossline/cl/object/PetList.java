package crossline.cl.object;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacevedo on 31-12-14.
 */
@Root
public class PetList
{
    @ElementList
    private List<PetObject> listPet = new ArrayList();

    public List<PetObject> getListPet()
    {
        return listPet;
    }

    public void setListPet(List<PetObject> listPet)
    {
        this.listPet = listPet;
    }
}
