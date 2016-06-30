package guillermoab.posgrado.unam.mx.practica2.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import guillermoab.posgrado.unam.mx.practica2.models.ModelAPP;

/**
 * Created by GuillermoAB on 29/06/2016.
 */
public class AppDataSource {
    private final SQLiteDatabase db;

    //Get DB to manipulate
    public AppDataSource(Context context){
        MySQLiteHelper helper = new MySQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
    //Insert APPS
    public void saveApp(ModelAPP modelAPP){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.COLUMN_APP_NAMEAPP,modelAPP.cName_App);
        contentValues.put(MySQLiteHelper.COLUMN_APP_NAMEDEV,modelAPP.cName_Dev);
        contentValues.put(MySQLiteHelper.COLUMN_APP_DETAILS,modelAPP.cApp_Details);
        contentValues.put(MySQLiteHelper.COLUMN_APP_INSTALL,modelAPP.bInstall);
        contentValues.put(MySQLiteHelper.COLUMN_APP_RESOURCE,modelAPP.nImageID);
        db.insert(MySQLiteHelper.TABLE_APP_NAME,null,contentValues);
    }
    //Delete APPS
    public void deleteAPP(ModelAPP modelAPP){
        if(modelAPP!=null){
            db.delete(MySQLiteHelper.TABLE_APP_NAME,MySQLiteHelper.COLUMN_APP_ID+"=?",new String[]{String.valueOf(modelAPP.id)});
        }
    }
    public void updateApp(ModelAPP modelAPP){

    }
    //Reading all items in DB
    public List<ModelAPP> getAllAPPS(){
        List<ModelAPP> modelAPPList = new ArrayList<>();
        Cursor cursor=db.query(MySQLiteHelper.TABLE_APP_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_APP_ID));
            String AppName = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_APP_NAMEAPP));
            String DevName = cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_APP_NAMEDEV));
            String AppDetails=cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_APP_DETAILS));
            int Install = cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_APP_INSTALL));
            int Image=cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_APP_RESOURCE));
            ModelAPP modelAPP = new ModelAPP(id,AppName,DevName,AppDetails,Install,Image);
            modelAPPList.add(modelAPP);
        }
        return modelAPPList;
    }

}
