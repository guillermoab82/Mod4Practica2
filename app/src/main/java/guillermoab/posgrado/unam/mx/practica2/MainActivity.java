package guillermoab.posgrado.unam.mx.practica2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import guillermoab.posgrado.unam.mx.practica2.adapters.AppListAdapter;
import guillermoab.posgrado.unam.mx.practica2.models.ModelAPP;
import guillermoab.posgrado.unam.mx.practica2.sql.AppDataSource;

public class MainActivity extends AppCompatActivity {

    private AppDataSource appDataSource;
    private Button btn_addapp;
    private static final int REQUEST_CODE_INSTALL_APP = 1;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.main_list);
        appDataSource = new AppDataSource(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //final Context  me = this;
        List<ModelAPP> modelAppList = appDataSource.getAllAPPS();
        if(!modelAppList.isEmpty()) {
            fill_list(getApplicationContext(), modelAppList);
        }else{
            Toast.makeText(getApplicationContext(),getResources().getText(R.string.NoApps),Toast.LENGTH_SHORT).show();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long ID) {
                AppListAdapter appListAdap = (AppListAdapter) parent.getAdapter();
                ModelAPP modAPP = appListAdap.getItem(position);
                Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                intent.putExtra("id",modAPP.id);
                intent.putExtra("appName",modAPP.cName_App);
                intent.putExtra("appDev",modAPP.cName_Dev);
                intent.putExtra("appDetails",modAPP.cApp_Details);
                intent.putExtra("appInstall",modAPP.bInstall);
                intent.putExtra("appImg",modAPP.nImageID);
                startActivity(intent);
            }
        });
        /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long ID) {
                AppListAdapter adapter = (AppListAdapter) parent.getAdapter();
                final ModelAPP modelAPP = adapter.getItem(position);
                String msg_del = getResources().getString(R.string.delete_message);

                new AlertDialog.Builder(me)
                        .setTitle(getResources().getString(R.string.delete_title))
                        .setMessage(String.format(msg_del+" %s?",modelAPP.cName_App))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                appDataSource.deleteAPP(modelAPP);
                                fill_list(getApplicationContext(),appDataSource.getAllAPPS());
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setCancelable(false).create().show();
                return true;
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activitymain,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_main_add:
                startActivityForResult(new Intent(getApplicationContext(),InstallActivity.class),REQUEST_CODE_INSTALL_APP);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CODE_INSTALL_APP==requestCode && resultCode==RESULT_OK){
            //Toast.makeText(getApplicationContext(),"Regreso",Toast.LENGTH_SHORT).show();
            List<ModelAPP> model = appDataSource.getAllAPPS();
            fill_list(getApplicationContext(),model);
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void fill_list(Context context, List<ModelAPP> mapp){
        listView.setAdapter(new AppListAdapter(context,mapp));
    }

}
