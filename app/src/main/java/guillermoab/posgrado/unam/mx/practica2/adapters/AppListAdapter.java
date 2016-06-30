package guillermoab.posgrado.unam.mx.practica2.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import guillermoab.posgrado.unam.mx.practica2.R;
import guillermoab.posgrado.unam.mx.practica2.models.ModelAPP;

/**
 * Created by GuillermoAB on 29/06/2016.
 */
public class AppListAdapter extends ArrayAdapter<ModelAPP>{
    public AppListAdapter(Context context, List<ModelAPP> apps) {
        super(context,0, apps);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_applist,parent,false));
        }
        TextView txtNameApp = (TextView) convertView.findViewById(R.id.row_appname);
        TextView txtNameDev = (TextView) convertView.findViewById(R.id.row_appdev);
        TextView txtInstall = (TextView) convertView.findViewById(R.id.row_appinstall);
        ImageView imgApp =(ImageView)convertView.findViewById(R.id.row_image);
        ModelAPP modelAPP = getItem(position);
        txtNameApp.setText(modelAPP.cName_App);
        txtNameDev.setText(modelAPP.cName_Dev);
        if(modelAPP.bInstall==0){
            txtInstall.setText(convertView.getResources().getText(R.string.msj_updated));
        }else{
            txtInstall.setText(convertView.getResources().getText(R.string.msj_install));
        }
        imgApp.setImageResource(modelAPP.nImageID);
        return convertView;
    }
}
