package crossline.cl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import crossline.cl.object.PetObject;
import crossline.cl.portafolio.R;

/**
 * Created by jacevedo on 30-12-14.
 */
public class PetAdapter extends BaseAdapter
{
    private Context context;
    private List<PetObject> listPet;

    public PetAdapter(Context context, List<PetObject> listPet)
    {
        this.context = context;
        this.listPet = listPet;
    }

    @Override
    public int getCount()
    {
        return listPet.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listPet.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return listPet.get(position).getCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_view_pets,null);
        }
        ((TextView)v.findViewById(R.id.txtPetCode)).setText(listPet.get(position).getCode()+"");
        ((TextView)v.findViewById(R.id.txtPetName)).setText(listPet.get(position).getPetName());
        ((TextView)v.findViewById(R.id.txtOwnerName)).setText(listPet.get(position).getPetOwner());
        ((TextView)v.findViewById(R.id.txtPetType)).setText(listPet.get(position).getPetType());
        ((TextView)v.findViewById(R.id.txtPetBreed)).setText(listPet.get(position).getPetBreed());
        return v;
    }
    public List<PetObject> getListPet()
    {
        return listPet;
    }
}
