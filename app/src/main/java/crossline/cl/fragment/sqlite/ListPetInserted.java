package crossline.cl.fragment.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import crossline.cl.helper.PetSQLiteHelper;
import crossline.cl.adapter.PetAdapter;
import crossline.cl.object.PetObject;
import crossline.cl.portafolio.R;


public class ListPetInserted extends Fragment
{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_list_pet_inserted, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        ListView list = (ListView)v.findViewById(R.id.lstPets);
        PetAdapter adapter = new PetAdapter(getActivity(),getListPets());
        list.setAdapter(adapter);

    }

    private List<PetObject> getListPets()
    {
        String[] campos = new String[] {"cod", "name_pet","name_owner","pet_type","race"};
        ArrayList<PetObject>petObjects = new ArrayList();

        PetSQLiteHelper sql = new PetSQLiteHelper(getActivity(), "dbPet",null,1);
        SQLiteDatabase db = sql.getWritableDatabase();

        Cursor c = db.query("PETS", campos, null, null, null, null, null);
        if(c.moveToFirst())
        {
            do
            {
                PetObject object = new PetObject();
                object.setCode(c.getInt(c.getColumnIndex("cod")));
                object.setPetName(c.getString(c.getColumnIndex("name_pet")));
                object.setPetOwner(c.getString(c.getColumnIndex("name_owner")));
                object.setPetType(c.getString(c.getColumnIndex("pet_type")));
                object.setPetBreed(c.getString(c.getColumnIndex("race")));
                petObjects.add(object);
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return petObjects;
    }
}
