package guillermoab.posgrado.unam.mx.practica2.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import guillermoab.posgrado.unam.mx.practica2.DetailsActivity;
import guillermoab.posgrado.unam.mx.practica2.R;

/**
 * Created by UDS_Estructuracion on 02/07/2016.
 */
public class ServiceUpdateNotification extends Service {
    private MyAsyncTask myAsyncTask;
    private int id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getExtras()!=null && intent.getExtras().containsKey("key_id")){
            id=intent.getExtras().getInt("key_id");
        }
        if(myAsyncTask==null){
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute();
        }
        return START_STICKY;
    }

    private class MyAsyncTask extends AsyncTask<Integer,Integer,Boolean>{
        private NotificationCompat.Builder mNotified;
        @Override
        protected void onPreExecute() {
            //TODO get app name and merge to contentText
            mNotified = new NotificationCompat
                    .Builder(getApplicationContext())
                    .setContentTitle(getResources().getString(R.string.service_Title_update))
                    .setContentText(getResources().getString(R.string.service_Text_update))
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_settings))
                    .setSmallIcon(R.drawable.ic_action_perm_data_setting);
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            for (int i=0;i<6;i++)
            {
                publishProgress(i);
                try {
                    Thread.sleep(1000*1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mNotified.setProgress(6,values[0],false);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(id,mNotified.build());
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                //TODO get appname and merge to ContentText
                mNotified.setProgress(0,0,false);
                mNotified.setContentTitle(getResources().getString(R.string.service_Title_update_End));
                mNotified.setContentText(getResources().getString(R.string.service_Text_update_End));
                mNotified.setContentInfo(getResources().getString(R.string.service_Info_update_End));
                mNotified.setAutoCancel(true);
                mNotified.setStyle(new NotificationCompat.BigTextStyle().bigText(getResources().getString(R.string.service_Info_update_End)));
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,
                        new Intent(getApplicationContext(), DetailsActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
                mNotified.setContentIntent(pendingIntent);
                NotificationManager manager  = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(id,mNotified.build());
            }
            myAsyncTask=null;
            stopSelf();
        }
    }
}
