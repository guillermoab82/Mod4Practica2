package guillermoab.posgrado.unam.mx.practica2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import guillermoab.posgrado.unam.mx.practica2.models.ModelAPP;
import guillermoab.posgrado.unam.mx.practica2.sql.AppDataSource;

/**
 * Created by UDS_Estructuracion on 04/07/2016.
 */
public class EditActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText appName;
    private EditText appDev;
    private EditText appDetails;
    private CheckBox appInstall;
    private int id;
    private int nInstall;
    private int nImg;
    private AppDataSource appDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_edit_title));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appName = (EditText) findViewById(R.id.edit_appname);
        appDev = (EditText) findViewById(R.id.edit_appdev);
        appDetails = (EditText) findViewById(R.id.edit_appdetails);
        appInstall = (CheckBox) findViewById(R.id.edit_chkInstall);
        findViewById(R.id.edit_btnsave).setOnClickListener(this);
        appDataSource = new AppDataSource(getApplicationContext());
        if(getIntent()!=null){
            id=getIntent().getExtras().getInt("id");
            appName.setText(getIntent().getExtras().getString("appName"));
            appDev.setText(getIntent().getExtras().getString("appDev"));
            appDetails.setText(getIntent().getExtras().getString("appDetails"));
            nImg = getIntent().getExtras().getInt("appImg");
            nInstall = getIntent().getExtras().getInt("appInstall");
            if (nInstall==1){
                appInstall.setChecked(true);
                appInstall.setEnabled(false);
            }else{
                appInstall.setChecked(false);
                appInstall.setEnabled(true);
            }
        }else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.installMsgNoData),Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_btnsave:
                update_app();
                break;
        }
    }

    private void update_app() {
        String appname = appName.getText().toString();
        String appdev = appDev.getText().toString();
        String appdetails = appDetails.getText().toString();
        boolean bInstall = appInstall.isChecked();
        if(!TextUtils.isEmpty(appname)&&!TextUtils.isEmpty(appdev)&&!TextUtils.isEmpty(appdetails)){
            nInstall=bInstall?1:0;
            ModelAPP modelAPP = new ModelAPP(id,appname,appdev,appdetails,nInstall,nImg);
            appDataSource.updateApp(modelAPP);
            Intent i = new Intent();
            i.putExtra("id",id);
            i.putExtra("appName",appname);
            i.putExtra("appDev",appdev);
            i.putExtra("appDetails",appdetails);
            i.putExtra("appImg",nImg);
            i.putExtra("appInstall",nInstall);
            setResult(RESULT_OK,i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(),getResources().getText(R.string.installMsgNoData),Toast.LENGTH_SHORT).show();
        }
    }
}
