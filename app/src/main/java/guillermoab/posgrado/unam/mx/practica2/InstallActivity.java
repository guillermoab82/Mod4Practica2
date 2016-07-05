package guillermoab.posgrado.unam.mx.practica2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import guillermoab.posgrado.unam.mx.practica2.models.ModelAPP;
import guillermoab.posgrado.unam.mx.practica2.sql.AppDataSource;

/**
 * Created by GuillermoAB on 29/06/2016.
 */
public class InstallActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText txtNameApp;
    private EditText txtNameDev;
    private EditText txtDetailsApp;
    private CheckBox chkInstall;
    private AppDataSource appDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_install_title));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.install_btnInstall).setOnClickListener(this);
        txtNameApp = (EditText) findViewById(R.id.install_txtNameAPP);
        txtNameDev = (EditText) findViewById(R.id.install_txtNameDev);
        txtDetailsApp = (EditText) findViewById(R.id.install_txtAppDetails);
        chkInstall = (CheckBox) findViewById(R.id.install_chkInstall);
        appDataSource = new AppDataSource(getApplicationContext());
    }

    @Override
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
            case R.id.install_btnInstall:
                installapp();
                break;
        }
    }

    private void installapp() {
        String app = txtNameApp.getText().toString();
        String appdev = txtNameDev.getText().toString();
        String detailsapp = txtDetailsApp.getText().toString();
        boolean isInstalled = chkInstall.isChecked();
        List<ModelAPP> modelAppList = appDataSource.getAllAPPS();
        boolean isImg;
        int imgresource;
       isImg=!(modelAppList.size()%2==0);
        int install = 1;
        if(!TextUtils.isEmpty(app)&&!TextUtils.isEmpty(appdev)&&!TextUtils.isEmpty(detailsapp)){
            install=isInstalled?1:0;
            imgresource=isImg?R.drawable.ic_action_android:R.drawable.ic_action_spellcheck;
            ModelAPP modelAPP = new ModelAPP(0,app,appdev,detailsapp,install,imgresource);
            appDataSource.saveApp(modelAPP);
            Intent i = new Intent();
            setResult(RESULT_OK,i);
            isImg=!isImg;
            finish();
        }else{
            Toast.makeText(getApplicationContext(),getResources().getText(R.string.installMsgNoData),Toast.LENGTH_SHORT).show();
        }

    }
}
