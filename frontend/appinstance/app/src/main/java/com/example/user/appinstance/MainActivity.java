package com.example.user.appinstance;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String CONTENT_URI = "content://com.example.user.restclient.provider/player";
    private ArrayList<Pictogram> arrayJugadores = new ArrayList<>();
    private ListView lvJugadores ;
    // Proyección: columnas de la tabla a recuperar
    private static String[] PROJECTION = new String[] {
            "_id",
            "image_path",
            "nationality",
            "details",
            "common_name",
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvJugadores = (ListView) findViewById(R.id.listaJugadores);
        cargaJugadores(null);

        PictogramArrayAdapter adapter;
        adapter = new PictogramArrayAdapter(this,R.layout.elementos, arrayJugadores);
        lvJugadores.setAdapter(adapter);

        lvJugadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) ((LinearLayout) view).getChildAt(0); // obtiene tv con id
                int numJugador = Integer.parseInt(tvId.getText().toString())-1;

                Intent intent = new Intent(MainActivity.this, DisplayPictogramActivity.class);
                Comunicator.setObjeto(arrayJugadores.get(numJugador));
                startActivity(intent);
                arrayJugadores = new ArrayList<>();
                cargaJugadores(null);
            }
        });
    }

    void cargaJugadores(View view){

        String recurso = CONTENT_URI ;
        Uri uriContenido =  Uri.parse(recurso);

        // obtenemos el ContentResolver
        ContentResolver contentResolver = getContentResolver();
        // Se ejecuta la consulta
        Cursor cursor = contentResolver.query(
                uriContenido,   // uri del recurso
                PROJECTION,     // Columnas a devolver
                null,           // Condición de la query
                null,           // Argumentos variables de la query
                null            // Orden de los resultados
        );
        // if (cursor.getCount() != 0)
        if (cursor != null && cursor.getCount() != 0) {
            int id;
            String common_nam;
            String image_Path;
            String details;

            // índices de las columnas
            int colId       = cursor.getColumnIndex(PROJECTION[0]);
            int colCommon_name   = cursor.getColumnIndex(PROJECTION[4]);
            int colimage_Path    = cursor.getColumnIndex(PROJECTION[1]);
            int colDetails = cursor.getColumnIndex(PROJECTION[3]);
            int colnationality = cursor.getColumnIndex(PROJECTION[2]);



            // se recuperan y muestran los resultados recuperados en el cursor
            while (cursor.moveToNext()) {
                Pictogram player = new Pictogram();
                player.setId(cursor.getInt(colId));
                player.setCommon_name(cursor.getString(colCommon_name));
                player.setImage_path(cursor.getString(colimage_Path));
                player.setNationality(cursor.getString(colnationality));
                player.setdetails(cursor.getString(colDetails));
                arrayJugadores.add(player);
                player = null;
            }
            cursor.close();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "No se han obtenido datos",
                    Toast.LENGTH_LONG
            ).show();
        }
    }


}
