package crossline.cl.fragment.rest;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import crossline.cl.adapter.PetAdapter;
import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.helper.HttpResponseHelper;
import crossline.cl.helper.PetSQLiteHelper;
import crossline.cl.object.PetObject;
import crossline.cl.object.RequestObject;
import crossline.cl.object.ResponseObject;
import crossline.cl.portafolio.R;


public class GetRequestFragment extends Fragment implements AdapterView.OnItemClickListener, Dialog.OnClickListener
{
    private ListView lstPet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
         View v = inflater.inflate(R.layout.fragment_rest_request, container, false);
        initComponent(v);
        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(!((BaseFragmentActivity)getActivity()).isOnline())
        {
            getActivity().onBackPressed();
        }
    }

    private void initComponent(View v)
    {
        ArrayList<PetObject> listPet = getPetsList();
        lstPet = (ListView)v.findViewById(R.id.lstPetsSerialize);
        PetAdapter adapter = new PetAdapter(getActivity(),listPet);
        lstPet.setAdapter(adapter);
        lstPet.setOnItemClickListener(this);
    }

    private ArrayList<PetObject> getPetsList()
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

        return petObjects;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        PetObject pet = (PetObject)lstPet.getAdapter().getItem(position);
        RequestObject requestObject = new RequestObject();
        requestObject.fillRequestObject(pet);
        new CreateAsyncRequest().execute(requestObject);
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        dialog.dismiss();
    }

    private class CreateAsyncRequest extends AsyncTask<RequestObject,Void,ResponseObject>
    {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute()
        {
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle(R.string.making_request);
            dialog.setMessage(getString(R.string.sending_information));
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected ResponseObject doInBackground(RequestObject... params)
        {
            HttpResponseHelper responseHelper = new HttpResponseHelper();
            List<NameValuePair> listData = new ArrayList();



            listData.add(new BasicNameValuePair("json_insert",getJson(params[0])));

            String url = "http://www.mobileapp.cl/portafolio/insert_pet.php";
            String json = responseHelper.makeHttpRequest(url,"GET",listData);

            return getResponseObject(json);
        }

        @Override
        protected void onPostExecute(ResponseObject responseObject)
        {
            dialog.dismiss();
            createMessageDialog(responseObject);
            super.onPostExecute(responseObject);
        }
    }

    private void createMessageDialog(ResponseObject responseObject)
    {
        String message = "";
        if(responseObject.getInsertedId()==-1)
        {
            message = getString(R.string.error);
        }
        else
        {
            message = getString(R.string.inserted_id) +" "+
                    responseObject.getInsertedId() + ", " + getString(R.string.page_to_check);
        }
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(R.string.title_response);
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton(R.string.accept, this);
        dialogBuilder.create().show();
    }

    private ResponseObject getResponseObject(String json)
    {
        Gson gson = new Gson();
        Type responseType = new TypeToken<ResponseObject>() {
        }.getType();
        return gson.fromJson(json,responseType);
    }

    private String getJson(RequestObject param)
    {
        Gson gson = new Gson();
        Type requestType = new TypeToken<RequestObject>() {
        }.getType();
        return gson.toJson(param,requestType);
    }
}
