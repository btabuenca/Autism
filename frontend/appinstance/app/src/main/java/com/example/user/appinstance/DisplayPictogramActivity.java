package com.example.user.appinstance;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * Created by User on 03/11/2017.
 */

public class DisplayPictogramActivity extends Activity {

    private TextView txtid;
    private TextView txtname;
    private TextView txtnationality;
    private EditText extDetails;
    private ImageView imgPlayer;
    private Context context;
    private Pictogram player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_pictogram);
        txtid = (TextView) findViewById(R.id.txtId);
        txtname = (TextView) findViewById(R.id.txtName);
        txtnationality = (TextView) findViewById(R.id.txtNacionalidad);
        extDetails = (EditText) findViewById(R.id.txDetails);
        imgPlayer = (ImageView) findViewById(R.id.imagePlayer);


        player = Comunicator.getObjeto();
        txtid.setText(Integer.toString(player.getId()));
        txtname.setText(player.getCommon_name());
        txtnationality.setText(player.getNationality());
        extDetails.setText(player.getdetails());
        Log.d("DDDDDDDDDDDDDDDDDDD", "despues" +extDetails.getText().toString());
        Picasso.with(this).
                load(player.getImage_path()).
                resize(150,150).into(imgPlayer);
        player = null;

    }

    public void Guardar(View view){
        player = Comunicator.getObjeto();

        String recurso = "content://com.example.user.restclient.provider/player/"+Integer.toString(player.getId());
        Uri uriContenido =  Uri.parse(recurso);

        ContentResolver contentResolver = getContentResolver();
        ContentValues valor = new ContentValues();
        valor.put("details",extDetails.getText().toString());
        int i = contentResolver.update(uriContenido, valor,null,null);
        extDetails.setText("");
        player = null;
        finish();
    }
}
