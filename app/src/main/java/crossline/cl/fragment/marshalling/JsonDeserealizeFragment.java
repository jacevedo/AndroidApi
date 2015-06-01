package crossline.cl.fragment.marshalling;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import crossline.cl.helper.PetSQLiteHelper;
import crossline.cl.adapter.PetAdapter;
import crossline.cl.object.PetObject;
import crossline.cl.portafolio.R;

public class JsonDeserealizeFragment extends Fragment implements View.OnClickListener
{
    private TextView txtSerializedText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_json_deserealize, container, false);
        initElements(v);
        return v;
    }

    private void initElements(View v)
    {
        txtSerializedText = (TextView)v.findViewById(R.id.txtSerializedText);
        txtSerializedText.setText(getPetsList());
        v.findViewById(R.id.btnDeserealizeButton).setOnClickListener(this);
    }
    private String getPetsList()
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

        if(petObjects.size()==0)
        {
            petObjects.add(getPet());
        }

        return jsonSerilizeList(petObjects);
    }

    private String jsonSerilizeList(ArrayList<PetObject> petObjects)
    {
        Gson gson = new Gson();

        Type animalType = new TypeToken<List<PetObject>>() {
        }.getType();

        return gson.toJson(petObjects, animalType);
    }

    private PetObject getPet()
    {
        PetObject object = new PetObject();
        object.setCode(1);
        object.setPetName("Eli");
        object.setPetOwner("Lala");
        object.setPetType(getString(R.string.cat));
        object.setPetBreed("Devon Rex");
        return object;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnDeserealizeButton:
                openDialogDeserialized();
                break;
        }
    }

    private void openDialogDeserialized()
    {
        Dialog customDialog = new Dialog(getActivity());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.deserialize_dialog);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationZoom;

        PetAdapter adapter = new PetAdapter(getActivity(),getPetListDeserialized());
        ((ListView)customDialog.findViewById(R.id.lstDeserializeDialog)).setAdapter(adapter);
        customDialog.show();
    }

    private List<PetObject> getPetListDeserialized()
    {
        Gson gson = new Gson();
        Type animalType = new TypeToken<List<PetObject>>() {
        }.getType();
        List<PetObject> listPet = gson.fromJson(txtSerializedText.getText().toString(),animalType);
        return listPet;
    }


}
