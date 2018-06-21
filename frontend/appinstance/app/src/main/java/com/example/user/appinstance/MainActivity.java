/**
 * Author: Bernardo Tabuenca
 *
 */

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

    private static final String CONTENT_URI = "content://com.example.user.restclient.provider/pictogram";
    private ArrayList<Pictogram> arrayPictogramas = new ArrayList<>();
    private ListView lvJugadores ;

    // Proyección: columnas de la tabla a recuperar
    private static String[] PROJECTION = new String[] {
            "_id",
            "pictogram_name",
            "pictogram_description",
            "category_name",
            "pictogram_img",
            "pictogram_sound"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvJugadores = (ListView) findViewById(R.id.listaPictogramas);
        cargaPictogramas(null);

        PictogramArrayAdapter adapter;
        adapter = new PictogramArrayAdapter(this,R.layout.elementos, arrayPictogramas);
        lvJugadores.setAdapter(adapter);

        lvJugadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) ((LinearLayout) view).getChildAt(0); // obtiene tv con id
                int numJugador = Integer.parseInt(tvId.getText().toString())-1;

                Intent intent = new Intent(MainActivity.this, DisplayPictogramActivity.class);
                Comunicator.setObjeto(arrayPictogramas.get(numJugador));
                startActivity(intent);
                arrayPictogramas = new ArrayList<>();
                cargaPictogramas(null);
            }
        });
    }

    void cargaPictogramas(View view){

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
            int colName   = cursor.getColumnIndex(PROJECTION[1]);
            int colDescription = cursor.getColumnIndex(PROJECTION[2]);
            int colCategoryName = cursor.getColumnIndex(PROJECTION[3]);
            int colImg    = cursor.getColumnIndex(PROJECTION[4]);
            int colSound    = cursor.getColumnIndex(PROJECTION[5]);
            int colDetails = cursor.getColumnIndex(PROJECTION[6]);


            // se recuperan y muestran los resultados recuperados en el cursor
            while (cursor.moveToNext()) {
                Pictogram pic = new Pictogram();
                pic.setId(cursor.getInt(colId));
                pic.setPictogram_name(cursor.getString(colName));
                pic.setPictogram_description(cursor.getString(colDescription));
                pic.setCategory_name(cursor.getString(colCategoryName));
                pic.setPictogram_img(cursor.getString(colImg));
                pic.setDetails(cursor.getString(colDetails));
                arrayPictogramas.add(pic);
                pic = null;
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
