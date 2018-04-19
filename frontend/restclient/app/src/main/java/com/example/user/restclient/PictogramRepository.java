package com.example.user.restclient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by User on 28/10/2017.
 */

public class PictogramRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = PictogramContract.tablaPlayer.TABLE_NAME+".db";
    private static final int DB_VERSION = 1;

    public PictogramRepository(Context contexto) { super(contexto, DB_NAME, null, DB_VERSION);}

    public PictogramRepository(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySql = "CREATE TABLE " + PictogramContract.tablaPlayer.TABLE_NAME +" ("
                + PictogramContract.tablaPlayer.COL_NAME_ID +" INTEGER PRIMARY KEY ,"
                + PictogramContract.tablaPlayer.COL_NAME_IMAGE_PATH+" TEXT,"
                + PictogramContract.tablaPlayer.COL_NAME_NATIONALITY+" TEXT,"
                + PictogramContract.tablaPlayer.COL_NAME_DETAILS+" TEXT,"
                + PictogramContract.tablaPlayer.COL_NAME_COMMON_NAME+" TEXT)";

        sqLiteDatabase.execSQL(querySql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String consultaSQL = "DROP TABLE IF EXISTS " + PictogramContract.tablaPlayer.TABLE_NAME;
        sqLiteDatabase.execSQL(consultaSQL);
        onCreate(sqLiteDatabase);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PictogramContract.tablaPlayer.TABLE_NAME,null,null);
    }
    public long add(Pictogram pictogram){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(PictogramContract.tablaPlayer.COL_NAME_IMAGE_PATH, pictogram.getImage_path());
        valores.put(PictogramContract.tablaPlayer.COL_NAME_NATIONALITY, pictogram.getNationality());
        valores.put(PictogramContract.tablaPlayer.COL_NAME_COMMON_NAME, pictogram.getCommon_name());
        valores.put(PictogramContract.tablaPlayer.COL_NAME_DETAILS, pictogram.getdetails());
        return database.insert( PictogramContract.tablaPlayer.TABLE_NAME,null,valores);
    }

    public ArrayList<Pictogram> getAll() {
        String consultaSQL = "SELECT * FROM " + PictogramContract.tablaPlayer.TABLE_NAME;
        ArrayList<Pictogram> lista = new ArrayList<>();

        // Accedo a la DB en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {
                Pictogram Pictogram = new Pictogram();
                Pictogram.setId(cursor.getInt(cursor.getColumnIndex(PictogramContract.tablaPlayer.COL_NAME_ID)));
                Pictogram.setdetails(cursor.getString(cursor.getColumnIndex(PictogramContract.tablaPlayer.COL_NAME_DETAILS)));
                Pictogram.setImage_path(cursor.getString(cursor.getColumnIndex(PictogramContract.tablaPlayer.COL_NAME_IMAGE_PATH)));
                Pictogram.setNationality(cursor.getString(cursor.getColumnIndex(PictogramContract.tablaPlayer.COL_NAME_NATIONALITY)));
                Pictogram.setCommon_name(cursor.getString(cursor.getColumnIndex(PictogramContract.tablaPlayer.COL_NAME_COMMON_NAME)));

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
