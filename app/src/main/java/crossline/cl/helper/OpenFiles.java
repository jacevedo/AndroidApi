package crossline.cl.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

public class OpenFiles implements OnClickListener
{
	private Context context;
    private static final int PDF = 0;
    private static final int OFFICE = 1;
    private int option;

	
	public OpenFiles(Context context)
	{
		this.context = context;
	}

    public void pdfOpen(String fileRoute)
    {
        String fileName = keepFileInternalStorage(fileRoute);
        try
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(
                    Uri.parse("file://" +context.getFilesDir() + "/"+ fileName),
                    "application/pdf");

            context.startActivity(intent);
        }
        catch(ActivityNotFoundException ac)
        {
            option = PDF;
            createDialog();

        }
    }

    private void createDialog()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Usted no posee la aplicacion para abrir  este tipo de archivo ");
        builder.setMessage("�Desea descargar una aplicacion desde el Android Market?");
        builder.setPositiveButton("Si",this);
        builder.setNegativeButton("No", null);
        builder.create().show();
		
	}
	private String keepFileInternalStorage(String fileRoute)
    {
    	AssetManager assetManager = context.getAssets();
    	String fileName = "";
        InputStream in = null;
        OutputStream out = null;
        File file = new File(context.getFilesDir(), fileRoute);
        try
        {
        	fileName = file.getName();
            in = assetManager.open(fileRoute);
            out = context.openFileOutput(fileName, Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            out.flush();
            out.close(); 
            out = null;
            in = null;
        }
        catch (Exception e)
        {
            Log.e("copiar dentro telefono", e.getMessage());
        }
		return fileName;
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

	@Override
	public void onClick(DialogInterface dialog, int which)
	{
		Intent marketIntent = new Intent(Intent.ACTION_VIEW);
        if(option == PDF)
        {
            marketIntent
                    .setData(Uri
                            .parse("market://details?id=com.adobe.reader"));
        }
        else
        {
            marketIntent
                    .setData(Uri
                            .parse("market://details?id=com.microsoft.office.officehub"));
        }
        context.startActivity(marketIntent);
		
	}
//com.microsoft.office.officehub
    public void xlsOpen(String s)
    {
        String fileName = keepFileInternalStorage(s);
        try
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(
                    Uri.parse("file://" +context.getFilesDir() + "/"+ fileName),
                    "application/pdf");

            context.startActivity(intent);
        }
        catch(ActivityNotFoundException ac)
        {
            option = OFFICE;
            createDialog();

        }
    }
}
