package guillermoab.posgrado.unam.mx.practica2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import guillermoab.posgrado.unam.mx.practica2.services.ServiceUpdateNotification;

/**
 * Created by GuillermoAB on 29/06/2016.
 */
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView img;
    private TextView txtappname;
    private TextView txtdevname;
    private TextView txtdetails;
    private int id,img_int,install;
    private TextView chkInstall;
    private static final int REQUEST_CODE_EDIT_APP = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        findViewById(R.id.details_btnUninstall).setOnClickListener(this);
        findViewById(R.id.details_btnOpen).setOnClickListener(this);
        findViewById(R.id.details_btnUpdate).setOnClickListener(this);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_details_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        img = (ImageView) findViewById(R.id.details_imgAPP);
        txtappname=(TextView) findViewById(R.id.details_txtAppName);
        txtdevname=(TextView) findViewById(R.id.details_txtDevName);
        txtdetails=(TextView) findViewById(R.id.details_txtAppDetails);
        chkInstall = (TextView) findViewById(R.id.details_chkInstall);
        if(getIntent()!=null && getIntent().getExtras().containsKey("id")){
            id=getIntent().getExtras().getInt("id");
            txtappname.setText(getIntent().getExtras().getString("appName"));
            txtdevname.setText(getIntent().getExtras().getString("appDev"));
            txtdetails.setText(getIntent().getExtras().getString("appDetails"));
            img_int=getIntent().getExtras().getInt("appImg");
            img.setImageResource(img_int);
            install = getIntent().getExtras().getInt("appInstall");
            if(install==0){
                chkInstall.setText(getResources().getString(R.string.msj_install));
            }else{
                chkInstall.setText(getResources().getString(R.string.msj_updated));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activitydetails,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_details_edit:
                Intent intent = new Intent(getApplicationContext(),EditActivity.class);
                intent.putExtra("id",id);
                String appName = txtappname.getText().toString();
                String appDev = txtdevname.getText().toString();
                String appDetails = txtdetails.getText().toString();
                intent.putExtra("appName",appName);
                intent.putExtra("appDev",appDev);
                intent.putExtra("appDetails",appDetails);
                intent.putExtra("appImg",img_int);
                intent.putExtra("appInstall",install);
                startActivityForResult(intent,REQUEST_CODE_EDIT_APP);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.details_btnUninstall:
                unistallApp();
                break;
            case R.id.details_btnOpen:
                openAPP();
                break;
            case R.id.details_btnUpdate:
                updateApp();
                break;
        }
    }

    private void unistallApp() {

    }

    private void updateApp() {
        startService(new Intent(getApplicationContext(), ServiceUpdateNotification.class));
    }

    private void openAPP() {
        String url = getResources().getString(R.string.details_url1);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CODE_EDIT_APP==requestCode && resultCode==RESULT_OK){
            //Toast.makeText(getApplicationContext(),"Regreso",Toast.LENGTH_SHORT).show();
            fill_data(data);
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void fill_data(Intent data) {
        if(data!=null && getIntent().getExtras().containsKey("id")){
            id=data.getExtras().getInt("id");
            txtappname.setText(data.getExtras().getString("appName"));
            txtdevname.setText(data.getExtras().getString("appDev"));
            txtdetails.setText(data.getExtras().getString("appDetails"));
            img_int=data.getExtras().getInt("appImg");
            img.setImageResource(img_int);
            install = data.getExtras().getInt("appInstall");
            if(install==0){
                chkInstall.setText(getResources().getString(R.string.msj_install));
            }else{
                chkInstall.setText(getResources().getString(R.string.msj_updated));
            }
        }
    }
}
