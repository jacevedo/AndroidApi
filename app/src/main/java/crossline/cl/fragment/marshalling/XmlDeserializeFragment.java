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

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import crossline.cl.helper.PetSQLiteHelper;
import crossline.cl.adapter.PetAdapter;
import crossline.cl.object.PetList;
import crossline.cl.object.PetObject;
import crossline.cl.portafolio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class XmlDeserializeFragment extends Fragment implements View.OnClickListener
{

    private TextView txtSerializedText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_xml_deserialize, container, false);
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
        ArrayList<PetObject> petObjects = new ArrayList();

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

        return jsonSerializeList(petObjects);
    }

    private String jsonSerializeList(ArrayList<PetObject> petObjects)
    {
        PetList list = new PetList();
        list.setListPet(petObjects);
        String xml = "";

        Serializer serializer = new Persister();
        xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        try
        {

            StringWriter writer = new StringWriter();
            serializer.write(list, writer);
            xml+= writer.toString();
            xml+="\n</xml>";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return xml;
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
        String xml = txtSerializedText.getText().toString();
        PetList list = new PetList();
        Serializer serializer = new Persister();

        try
        {
            list = serializer.read(PetList.class,xml);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return list.getListPet();

    }


}
