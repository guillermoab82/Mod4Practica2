package guillermoab.posgrado.unam.mx.practica2.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by GuillermoAB on 28/06/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{
    private final static String DATABASE_NAME = "app_install_db";
    private static final int DATABASE_VERSION = 1;

    //Table to store apps
    public static final String TABLE_APP_NAME="apps_table";
    public static final String COLUMN_APP_ID= BaseColumns._ID;
    public static final String COLUMN_APP_NAMEAPP= "cName_APP";
    public static final String COLUMN_APP_NAMEDEV= "cName_Dev";
    public static final String COLUMN_APP_DETAILS= "cAPP_Details";
    public static final String COLUMN_APP_INSTALL= "bAPP_Install";
    public static final String COLUMN_APP_RESOURCE= "nResourceID";

   
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
