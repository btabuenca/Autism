package com.example.user.restclient;

import android.content.ContentProvider;
import android.content.Context;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by User on 01/11/2017.
 */

public class PictogramProvider extends ContentProvider {
    private Context context;
    private static final String AUTHORITY =
            PictogramProvider.class.getPackage().getName() + ".provider";
    private static final String ENTITY = "player";
   private static final String uri ="content://com.example.user.api_sport_adrian_rodriguez.provider/player";
    private static final Uri CONTENT_URI = Uri.parse(uri);

    // Tipos de URI accesibles
    private static final int ID_URI_PLAYER_ALL  = 1;
    private static final int ID_URI_PLAYER_UNICO = 2;

    private static final UriMatcher uriMatcher;
    // Inicializamos el objeto UriMatcher con las rutas disponibles
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ENTITY, ID_URI_PLAYER_ALL);
        uriMatcher.addURI(AUTHORITY, ENTITY + "/#", ID_URI_PLAYER_UNICO);
    }

    // Base de datos
    private PictogramRepository pictogram_repository;


    public boolean onCreate() {
        pictogram_repository = new PictogramRepository(getContext());
        return true;
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // Si es una consulta a un ID concreto se añade el WHERE
        String where = "";
        switch (uriMatcher.match(uri)) {
            case ID_URI_PLAYER_UNICO:  // URI termina en /#
                where = PictogramContract.tablaPlayer.COL_NAME_ID +" = ?";
                selectionArgs = new String[1];
                selectionArgs[0] = uri.getLastPathSegment();
                break;
            case ID_URI_PLAYER_ALL:
                break;
            default:
                throw new IllegalArgumentException("URI INCORRECTA  " + uri );
        }

        SQLiteDatabase db = pictogram_repository.getReadableDatabase();
        Cursor cursor = db.query(
                PictogramContract.tablaPlayer.TABLE_NAME,
                projection,
                where,
                selectionArgs,
                null, null, sortOrder);

        return cursor;
    }


    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int numFilasAfectadas;

        // Si es una consulta a un ID concreto se añade el WHERE
        String where = selection;
        if (uriMatcher.match(uri) == ID_URI_PLAYER_UNICO) {
            where = "_id = " + uri.getLastPathSegment();
        }

        SQLiteDatabase db = pictogram_repository.getWritableDatabase();
        numFilasAfectadas = db.update(PictogramContract.tablaPlayer.TABLE_NAME, values, where, selectionArgs);

        return numFilasAfectadas;
    }

    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        int numFilasAfectadas;

        String where = selection;
        if (uriMatcher.match(uri) == ID_URI_PLAYER_UNICO) {
            where = "_id = " + uri.getLastPathSegment();
        }

        SQLiteDatabase db = pictogram_repository.getWritableDatabase();
        numFilasAfectadas = db.delete(PictogramContract.tablaPlayer.TABLE_NAME, where, selectionArgs);

        return numFilasAfectadas;
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, ContentValues values) {

        long regId;

        SQLiteDatabase db = pictogram_repository.getWritableDatabase();
        regId = db.insert(PictogramContract.tablaPlayer.TABLE_NAME, null, values);

        @SuppressWarnings("UnnecessaryLocalVariable")
        Uri nuevoURI = ContentUris.withAppendedId(CONTENT_URI, regId);

        return nuevoURI;
    }

    /**
     * Implement this to handle requests for the MIME type of the data at the
     * given URI.  The returned MIME type should start with
     * <code>vnd.android.cursor.item</code> for a single record,
     * or <code>vnd.android.cursor.dir/</code> for multiple items.
     * This method can be called from multiple threads, as described in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html#Threads">Processes
     * and Threads</a>.
     * <p>
     * <p>Note that there are no permissions needed for an application to
     * access this information; if your content provider requires read and/or
     * write permissions, or is not exported, all applications can still call
     * this method regardless of their access permissions.  This allows them
     * to retrieve the MIME type for a URI when dispatching intents.
     *
     * @param uri the URI to query.
     * @return a MIME type string, or {@code null} if there is no type.
     */
    @Nullable
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)) {
            case ID_URI_PLAYER_ALL:
                return "vnd.android.cursor.dir/vnd."+ AUTHORITY +".player";    // lista de entidades
            case ID_URI_PLAYER_UNICO:
                return "vnd.android.cursor.item/vnd."+ AUTHORITY +".player";   // entidad única
            default:
                return null;
        }
    }

    public Context getApplicationContext() {
        return getContext();
    }
}
