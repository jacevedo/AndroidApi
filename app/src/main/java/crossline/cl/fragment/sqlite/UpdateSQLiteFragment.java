package crossline.cl.fragment.sqlite;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import crossline.cl.helper.PetSQLiteHelper;
import crossline.cl.object.PetObject;
import crossline.cl.portafolio.R;
import crossline.cl.portafolio.SQLiteActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateSQLiteFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener
{
    private List<PetObject> petObjectList;
    private EditText edtPetName;
    private EditText edtOwnerName;
    private Spinner spCodeName;
    private Spinner spPetType;
    private Spinner spPetRace;
    private boolean isFullInformation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_sqlite, container, false);
        initComponent(v);
        return v;
    }

    private void initComponent(View v)
    {
        petObjectList = getPetObjectList();
        isFullInformation = false;
        edtPetName = (EditText)v.findViewById(R.id.edtPetName);
        edtOwnerName = (EditText)v.findViewById(R.id.edtPetOwner);
        spPetType = (Spinner)v.findViewById(R.id.spTypePet);
        spPetRace = (Spinner)v.findViewById(R.id.spPetRace);
        spCodeName = (Spinner)v.findViewById(R.id.spIdNamePet);

        spCodeName.setAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,
                petObjectList));

        spPetType.setAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,
                ((SQLiteActivity) getActivity()).getPetType()));

        spPetType.setOnItemSelectedListener(this);
        spCodeName.setOnItemSelectedListener(this);
        v.findViewById(R.id.btnSubmit).setOnClickListener(this);

        unableView();
    }

    private void unableView()
    {
        edtPetName.setEnabled(false);
        edtOwnerName.setEnabled(false);
        spPetType.setEnabled(false);
        spPetRace.setEnabled(false);
    }
    private void enableView()
    {
        edtPetName.setEnabled(true);
        edtOwnerName.setEnabled(true);
        spPetType.setEnabled(true);
        spPetRace.setEnabled(true);
    }
    private void fullInformation(PetObject petObject)
    {
        isFullInformation = true;
        int petType = searchPetType(petObject.getPetType());
        edtPetName.setText(petObject.getPetName());
        edtOwnerName.setText(petObject.getPetOwner());
        spPetType.setSelection(petType);
        fullBreedSpinner(petType);
        int petBreed = searchPetBreed(petObject.getPetBreed());
        Log.e("petBreed",petBreed+"");
        spPetRace.setSelection(petBreed);
    }

    private int searchPetBreed(String petBreed)
    {
        for(int i = 0; i<spPetRace.getAdapter().getCount();i++)
        {
            if(spPetRace.getAdapter().getItem(i).toString().compareToIgnoreCase(petBreed)==0)
            {
                return i;
            }
        }
        return -1;
    }

    private int searchPetType(String petType)
    {
        for(int i = 0; i<spPetType.getAdapter().getCount();i++)
        {
            if(spPetType.getAdapter().getItem(i).toString().compareToIgnoreCase(petType)==0)
            {
                return i;
            }
        }
        return -1;
    }

    private void clearView()
    {
        edtPetName.setText("");
        edtOwnerName.setText("");
        spPetType.setSelection(0);
        fullBreedSpinner(0);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (parent.getId())
        {
            case R.id.spIdNamePet:
                fullAll(position);
                break;
            case R.id.spTypePet:
                if(!isFullInformation)
                {
                    fullBreedSpinner(position);
                }
                else
                {
                    isFullInformation = false;
                }
                break;

        }
    }

    private void fullAll(int position)
    {
        switch (position)
        {
            case 0:
                clearView();
                unableView();
            break;
            default:
                enableView();
                fullInformation(petObjectList.get(position));
                break;

        }
    }



    private void fullBreedSpinner(int position)
    {
        switch (position)
        {
            case 0:
                spPetRace.setAdapter(new ArrayAdapter(getActivity(),
                        android.R.layout.simple_list_item_1,new String[]{getString(R.string.select_type)}));
                break;
            case 1:
                spPetRace.setAdapter(new ArrayAdapter(getActivity(),
                        android.R.layout.simple_list_item_1,
                        ((SQLiteActivity) getActivity()).getDogRace()));
                break;
            case 2:
                spPetRace.setAdapter(new ArrayAdapter(getActivity(),
                        android.R.layout.simple_list_item_1,
                        ((SQLiteActivity) getActivity()).getCatRace()));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnSubmit:
                validateUpdate();
                break;
        }
    }
    private void validateUpdate()
    {
        if(edtPetName.getText().toString().compareToIgnoreCase("")!=0 &&
                edtOwnerName.getText().toString().compareToIgnoreCase("")!=0&&
                spPetType.getSelectedItemPosition()!=0 &&
                spPetRace.getSelectedItemPosition()!=0)
        {
            updateData(spCodeName.getSelectedItemPosition(), edtPetName.getText().toString(), edtOwnerName.getText().toString(),
                    (String) spPetType.getSelectedItem(), (String) spPetRace.getSelectedItem());
        }
        else
        {
            putCursorEmptyInput();
        }
    }
    private void updateData(int position,String petName, String ownerName, String petType, String petRace)
    {
        PetSQLiteHelper sql = new PetSQLiteHelper(getActivity(), "dbPet",null,1);
        SQLiteDatabase db = sql.getWritableDatabase();
        String [] args = new String []{position+""};
        ContentValues newRow = new ContentValues();
        newRow.put("name_pet", petName);
        newRow.put("name_owner",ownerName);
        newRow.put("pet_type",petType);
        newRow.put("race",petRace);
        if(db!=null)
        {
            long successful = db.update("PETS", newRow, "cod=?", args);
            if(successful!=-1)
            {
                Toast.makeText(getActivity(), R.string.update_ok, Toast.LENGTH_SHORT).show();
                clearView();
                unableView();
            }
            else
            {

                Toast.makeText(getActivity(),R.string.update_fail,Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }
    private void putCursorEmptyInput()
    {
        Toast.makeText(getActivity(),"Se deben llenar todos los campos", Toast.LENGTH_SHORT).show();

        if(edtPetName.getText().toString().compareToIgnoreCase("")==0)
        {
            edtPetName.requestFocus();
        }
        else if(edtOwnerName.getText().toString().compareToIgnoreCase("")==0)
        {
            edtOwnerName.requestFocus();
        }
        else if(spPetType.getSelectedItemPosition()==0)
        {
            spPetType.requestFocus();
        }
        else if(spPetRace.getSelectedItemPosition()==0)
        {
            spPetRace.requestFocus();
        }
    }

}
