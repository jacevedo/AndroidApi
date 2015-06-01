package crossline.cl.fragment.sqlite;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import crossline.cl.helper.PetSQLiteHelper;
import crossline.cl.object.PetObject;
import crossline.cl.portafolio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteSQLiteFragment extends Fragment implements View.OnClickListener
{
    private List<PetObject> petObjectList;
    private Spinner spCodeName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_delete_sqlite, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        petObjectList = getPetObjectList();
        spCodeName = (Spinner)v.findViewById(R.id.spIdNamePet);

        spCodeName.setAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,
                petObjectList));

        v.findViewById(R.id.btnSubmit).setOnClickListener(this);
    }
    private List<PetObject> getPetObjectList()
    {
        String[] campos = new String[] {"cod", "name_pet","name_owner","pet_type","race"};
        ArrayList<PetObject> petObjects = new ArrayList();

        PetSQLiteHelper sql = new PetSQLiteHelper(getActivity(), "dbPet",null,1);
        SQLiteDatabase db = sql.getWritableDatabase();

        Cursor c = db.query("PETS", campos, null, null, null, null, null);

        PetObject object1 = new PetObject();
        object1.setCode(-1);
        object1.setPetName("-");
        object1.setPetOwner("-");
        object1.setPetType("-");
        object1.setPetBreed("-");
        petObjects.add(object1);

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

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnSubmit:
                deleteData();
                break;
        }
    }

    private void deleteData()
    {
        if(spCodeName.getSelectedItemPosition()!=0)
        {
            PetSQLiteHelper sql = new PetSQLiteHelper(getActivity(), "dbPet",null,1);
            SQLiteDatabase db = sql.getWritableDatabase();
            String[]args = new String[]{petObjectList.get(
                                                spCodeName.getSelectedItemPosition()).getCode()+""};
            int successful = db.delete("PETS","cod=?",args);
            if(successful!=-1)
            {
                Toast.makeText(getActivity(), R.string.delete_ok, Toast.LENGTH_SHORT).show();
            }
            else
            {

                Toast.makeText(getActivity(),R.string.delete_fail,Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
        else
        {
            Toast.makeText(getActivity(),R.string.select_codeName,Toast.LENGTH_SHORT).show();
            spCodeName.requestFocus();
        }
    }
}
