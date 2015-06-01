package crossline.cl.fragment.rest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import crossline.cl.base.BaseFragmentActivity;
import crossline.cl.helper.HttpResponseHelper;
import crossline.cl.object.ResponseObject;
import crossline.cl.portafolio.R;


public class UploadImageFragment extends Fragment implements View.OnClickListener,Dialog.OnClickListener
{
    private ImageView image;
    private Bitmap bm;
    private static final int RESULT_LOAD_IMG = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_upload_image, container, false);
        initComponents(v);
        return v;
    }

    private void initComponents(View v)
    {
        image = (ImageView)v.findViewById(R.id.imgUpLoad);
        v.findViewById(R.id.btnUploadImage).setOnClickListener(this);
        v.findViewById(R.id.btnLoadImage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnUploadImage:
                uploadImage();
            break;
            case R.id.btnLoadImage:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            break;
        }
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

    private void uploadImage()
    {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte [] ba = bao.toByteArray();
        String base64 = Base64.encodeToString(ba,0);
        new UploadAsyncImage().execute(base64);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMG && resultCode == getActivity().RESULT_OK)
        {
            try
            {
                Uri selectedImageUri = data.getData();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                bm = BitmapFactory.decodeFile(getImage(selectedImageUri));
                image.setImageBitmap(bm);
            }
            catch (Exception e)
            {
                Log.e("Open Picture", e.toString());
            }
        }
    }
    private String getImage(Uri uri)
    {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getActivity().getContentResolver().query(uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        dialog.dismiss();
    }

    class UploadAsyncImage extends AsyncTask<String,Void,String>
    {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute()
        {
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle(R.string.sending_information);
            dialog.setMessage(getString(R.string.uploading_image));
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params)
        {
            HttpResponseHelper responseHelper = new HttpResponseHelper();
            List<NameValuePair> listData = new ArrayList();
            listData.add(new BasicNameValuePair("image",params[0]));
            String url = "http://www.mobileapp.cl/portafolio/upload_picture.php";

            String json = responseHelper.makeHttpRequest(url,"POST",listData);
            return json;
        }

        @Override
        protected void onPostExecute(String s)
        {
            dialog.dismiss();
            ResponseObject object = decodeJson(s);
            createDialog(object);
            super.onPostExecute(s);
        }
    }

    private void createDialog(ResponseObject responseObject)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(R.string.title_response);


        if(responseObject.getInsertedId()!=-1)
        {
            dialogBuilder.setMessage(getString(R.string.inserted_id) + " " +
                    responseObject.getInsertedId() + " " + getString(R.string.page_to_check));
        }
        else
        {
            dialogBuilder.setMessage(getString(R.string.error_image));
        }
        dialogBuilder.setPositiveButton(R.string.accept, this);
        dialogBuilder.create().show();
    }

    private ResponseObject decodeJson(String s)
    {
        ResponseObject response = null;
        try
        {
            Gson gson = new Gson();
            Type responseType = new TypeToken<ResponseObject>() {
            }.getType();
            response = gson.fromJson(s,responseType);
        }
        catch (Exception e)
        {
            response = new ResponseObject();
            response.setInserted("Error");
            response.setInsertedId(-1);
        }
        return response;
    }
}
