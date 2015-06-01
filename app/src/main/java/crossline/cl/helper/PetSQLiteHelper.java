package crossline.cl.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jacevedo on 30-12-14.
 */
public class PetSQLiteHelper extends SQLiteOpenHelper
{
    private String tablePet = "CREATE TABLE IF NOT EXISTS PETS(cod INTEGER PRIMARY KEY AUTOINCREMENT," +
            " name_pet TEXT, name_owner TEXT, pet_type TEXT, race TEXT)";

    public PetSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    public PetSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler)
    {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(tablePet);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS PETS");

        db.execSQL(tablePet);
    }
}
