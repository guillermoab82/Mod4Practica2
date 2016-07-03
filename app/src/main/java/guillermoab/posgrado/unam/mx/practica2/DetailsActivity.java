package guillermoab.posgrado.unam.mx.practica2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    private int id,install;
    private TextView chkInstall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        findViewById(R.id.details_btnUninstall).setOnClickListener(this);
        findViewById(R.id.details_btnOpen).setOnClickListener(this);
        findViewById(R.id.details_btnUpdate).setOnClickListener(this);
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
            img.setImageResource(getIntent().getExtras().getInt("appImg"));
            install = getIntent().getExtras().getInt("appInstall");
            if(install==0){
                chkInstall.setText(getResources().getString(R.string.msj_updated));
            }else{
                chkInstall.setText(getResources().getString(R.string.msj_install));
            }
        }
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
}
