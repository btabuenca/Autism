package com.example.user.restclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
/**
 * Created by User on 30/10/2017.
 */

public class PictogramListActivity extends Activity {

    private PictogramAPIRESTService apiService;
    private ListView listaJugadores;
    private PictogramRepository db;
    private ArrayList<Pictogram> arrayPictograms;
    private PictogramAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pictograms);
        arrayPictograms = new ArrayList<>();
        listaJugadores = (ListView) findViewById(R.id.listaJugadores);

        db = new PictogramRepository(getApplicationContext());
        arrayPictograms = db.getAll();
        adapter = new PictogramAdapter(this,R.layout.pictogram, arrayPictograms);
        listaJugadores.setAdapter(adapter);

    }

    public void borrar(View v){
        ejecutarBorrado();
        finish();
    }
    public void ejecutarBorrado(){
        db.deleteAll();
        arrayPictograms = null;
        adapter = null;
        listaJugadores = null;
    }

}
