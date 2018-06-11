/**
 * Author: Bernardo Tabuenca
 *
 */

package com.example.user.restclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class PictogramListActivity extends Activity {


    private ListView listaPictogramas;
    private PictogramRepository db;
    private ArrayList<Pictogram> arrayPictograms;
    private PictogramAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_pictograms);
        arrayPictograms = new ArrayList<>();
        listaPictogramas = (ListView) findViewById(R.id.listaPictogramas);

        db = new PictogramRepository(getApplicationContext());
        arrayPictograms = db.getAll();
        adapter = new PictogramAdapter(this,R.layout.pictogram, arrayPictograms);
        listaPictogramas.setAdapter(adapter);

    }

    public void borrar(View v){
        ejecutarBorrado();
        finish();
    }
    public void ejecutarBorrado(){
        db.deleteAll();
        arrayPictograms = null;
        adapter = null;
        listaPictogramas = null;
    }

}
