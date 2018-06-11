/**
 * Author: Bernardo Tabuenca
 *
 */
package com.example.user.restclient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;



public class PictogramRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = PictogramContract.tablePictograms.TABLE_NAME+".db";
    private static final int DB_VERSION = 1;

    public PictogramRepository(Context contexto) { super(contexto, DB_NAME, null, DB_VERSION);}

    public PictogramRepository(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySql = "CREATE TABLE " + PictogramContract.tablePictograms.TABLE_NAME +" ("
                + PictogramContract.tablePictograms.COL_PICTOGRAM_ID +" INTEGER PRIMARY KEY ,"
                + PictogramContract.tablePictograms.COL_PICTOGRAM_SOUND +" TEXT,"
                + PictogramContract.tablePictograms.COL_PICTOGRAM_IMG +" TEXT,"
                + PictogramContract.tablePictograms.COL_PICTOGRAM_CATEGORYNAME +" TEXT,"
                + PictogramContract.tablePictograms.COL_PICTOGRAM_DESCRIPTION +" TEXT,"
                + PictogramContract.tablePictograms.COL_PICTOGRAM_NAME +" TEXT)";

        sqLiteDatabase.execSQL(querySql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String consultaSQL = "DROP TABLE IF EXISTS " + PictogramContract.tablePictograms.TABLE_NAME;
        sqLiteDatabase.execSQL(consultaSQL);
        onCreate(sqLiteDatabase);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PictogramContract.tablePictograms.TABLE_NAME,null,null);
    }
    public long add(Pictogram pictogram){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(PictogramContract.tablePictograms.COL_PICTOGRAM_IMG, pictogram.getPictogram_img());
        valores.put(PictogramContract.tablePictograms.COL_PICTOGRAM_CATEGORYNAME, pictogram.getCategory_name());
        valores.put(PictogramContract.tablePictograms.COL_PICTOGRAM_NAME, pictogram.getPictogram_name());
        valores.put(PictogramContract.tablePictograms.COL_PICTOGRAM_DESCRIPTION, pictogram.getPictogram_description());

        return database.insert( PictogramContract.tablePictograms.TABLE_NAME,null,valores);
    }

    public ArrayList<Pictogram> getAll() {
        String consultaSQL = "SELECT * FROM " + PictogramContract.tablePictograms.TABLE_NAME;
        ArrayList<Pictogram> lista = new ArrayList<>();

        // Accedo a la DB en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {
                Pictogram Pictogram = new Pictogram();


                Pictogram.setPictogram_id(cursor.getInt(cursor.getColumnIndex(PictogramContract.tablePictograms.COL_PICTOGRAM_ID)));
                Pictogram.setPictogram_name(cursor.getString(cursor.getColumnIndex(PictogramContract.tablePictograms.COL_PICTOGRAM_NAME)));
                Pictogram.setPictogram_description(cursor.getString(cursor.getColumnIndex(PictogramContract.tablePictograms.COL_PICTOGRAM_DESCRIPTION)));
                Pictogram.setPictogram_img(cursor.getString(cursor.getColumnIndex(PictogramContract.tablePictograms.COL_PICTOGRAM_IMG)));
                Pictogram.setPictogram_sound(cursor.getString(cursor.getColumnIndex(PictogramContract.tablePictograms.COL_PICTOGRAM_SOUND)));



                lista.add(Pictogram);

                Pictogram = null;
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return lista;
    }
}
