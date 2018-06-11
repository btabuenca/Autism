/**
 * Author: Bernardo Tabuenca
 *
 */

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


public class DisplayPictogramActivity extends Activity {

    private TextView txtid;
    private TextView txtname;
    private TextView txtdescription;
    private TextView txtcategoryname;

    private EditText extDetails;
    private ImageView imgPlayer;
    private Context context;
    private Pictogram pictogram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("DDDDDDDDDDDDDDDDDDD", "vamos");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_pictogram);

        txtid = (TextView) findViewById(R.id.txtId);
        txtname = (TextView) findViewById(R.id.txtName);
        txtdescription = (TextView) findViewById(R.id.txtDescription);
        txtcategoryname = (TextView) findViewById(R.id.txtCategoryName);


        extDetails = (EditText) findViewById(R.id.txDetails);
        imgPlayer = (ImageView) findViewById(R.id.imagePictogram);


        pictogram = Comunicator.getObjeto();


        txtid.setText(Integer.toString(pictogram.getId()));
        txtname.setText(pictogram.getPictogram_name());
        txtdescription.setText(pictogram.getPictogram_description());
        extDetails.setText(pictogram.getDetails());


        Log.d("DDDDDDDDDDDDDDDDDDD", "que nos vamos" +extDetails.getText().toString());

        Picasso.with(this).
                load(pictogram.getPictogram_img()).
                resize(150,150).into(imgPlayer);

        pictogram = null;

    }

    public void Guardar(View view){
        pictogram = Comunicator.getObjeto();

        String recurso = "content://com.example.user.restclient.provider/pictogram/"+Integer.toString(pictogram.getId());
        Uri uriContenido =  Uri.parse(recurso);

        ContentResolver contentResolver = getContentResolver();
        ContentValues valor = new ContentValues();
        valor.put("details",extDetails.getText().toString());
        int i = contentResolver.update(uriContenido, valor,null,null);
        extDetails.setText("");
        pictogram = null;
        finish();
    }
}
