package crossline.cl.fragment.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import crossline.cl.helper.PetSQLiteHelper;
import crossline.cl.portafolio.R;
import crossline.cl.portafolio.SQLiteActivity;

public class InsertFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener
{
    private EditText edtPetName;
    private EditText edtOwnerName;
    private Spinner spPetType;
    private Spinner spPetRace;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
       View v = inflater.inflate(R.layout.fragment_insert, container, false);
       initComponent(v);
       return v;
    }

    private void initComponent(View v)
    {
        edtPetName = (EditText)v.findViewById(R.id.edtPetName);
        edtOwnerName = (EditText)v.findViewById(R.id.edtPetOwner);
        spPetType = (Spinner)v.findViewById(R.id.spTypePet);
        spPetRace = (Spinner)v.findViewById(R.id.spPetRace);

        spPetType.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,
                                                ((SQLiteActivity) getActivity()).getPetType()));

        spPetType.setOnItemSelectedListener(this);
        v.findViewById(R.id.btnSubmit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnSubmit:
                validateInsert();
                break;
        }
    }

    private void validateInsert()
    {
        if(edtPetName.getText().toString().compareToIgnoreCase("")!=0 &&
                edtOwnerName.getText().toString().compareToIgnoreCase("")!=0&&
                spPetType.getSelectedItemPosition()!=0 &&
                spPetRace.getSelectedItemPosition()!=0)
        {
            insertData(edtPetName.getText().toString(),edtOwnerName.getText().toString(),
                    (String)spPetType.getSelectedItem(),(String)spPetRace.getSelectedItem());
        }
        else
        {
            putCursorEmptyInput();
        }
    }

    private void insertData(String petName, String ownerName, String petType, String petRace)
    {
        PetSQLiteHelper sql = new PetSQLiteHelper(getActivity(), "dbPet",null,1);
        SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues newRow = new ContentValues();
        newRow.put("name_pet", petName);
        newRow.put("name_owner",ownerName);
        newRow.put("pet_type",petType);
        newRow.put("race",petRace);
        if(db!=null)
        {
            long successful = db.insert("PETS",null,newRow);
            if(successful!=-1)
            {
                Toast.makeText(getActivity(),R.string.insert_ok,Toast.LENGTH_SHORT).show();
                clearView();
            }
            else
            {

                Toast.makeText(getActivity(),R.string.insert_fail,Toast.LENGTH_SHORT).show();
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
    private void fullBreedSpinner(int i)
    {
        switch (i)
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
       fullBreedSpinner(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
    private void clearView()
    {
        edtPetName.setText("");
        edtOwnerName.setText("");
        spPetType.setSelection(0);
        fullBreedSpinner(0);
    }


}
