package net.iessochoa.joseantoniolopez.practica6.modelo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import net.iessochoa.joseantoniolopez.practica6.R;

import static net.iessochoa.joseantoniolopez.practica6.modelo.NoticiaContract.uriMatcher;

/**
 * Created by JoseA on 04/01/2017.
 * * Para entender esta clase, tenéis la explicación del ContentProvider en
 * http://www.hermosaprogramacion.com/2015/06/tutorial-android-crear-un-content-provider-personalizado/
 */

public class NoticiaProvider extends ContentProvider {
    //definimos la base de datos
    private NoticiasDbHelper noticiasDbHelper;

    @Override
    public boolean onCreate() {
        noticiasDbHelper = new NoticiasDbHelper(getContext());
        return true;
    }

    /**
     * Permite identificar el tipo MIME correspondiente a la uri
     *
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case NoticiaContract.ALLROWS:
                return NoticiaContract.MULTIPLE_MIME;
            case NoticiaContract.SINGLE_ROW:
                return NoticiaContract.SINGLE_MIME;
            default:
                throw new IllegalArgumentException(getContext().getResources().getString(R.string.err_uritype) + uri);

        }

    }

    /**
     * Devuelve  un cursor con el resultado de la consulta realizada
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //abrimos la base de datos
        SQLiteDatabase db = noticiasDbHelper.getWritableDatabase();
        //miramos el tipo de consulta según la uri
        int match = uriMatcher.match(uri);
        Cursor cursor;
        switch (match) {
            case NoticiaContract.ALLROWS://consulta todos los registros
                cursor = db.query(NoticiaContract.NoticiaEntry.TABLE_NAME, projection,
                        selection, selectionArgs,
                        null, null, sortOrder);
                //nos permite avisar al observador de la uri en caso de que hayan modificaciones
                cursor.setNotificationUri(getContext().getContentResolver(), NoticiaContract.CONTENT_URI);
                break;
            case NoticiaContract.SINGLE_ROW://un solo registro basado en id
                long idNoticia = ContentUris.parseId(uri);
                cursor = db.query(NoticiaContract.NoticiaEntry.TABLE_NAME, projection,
                        NoticiaContract.NoticiaEntry._ID + " = " + idNoticia, selectionArgs,
                        null, null, sortOrder);

                cursor.setNotificationUri(getContext().getContentResolver(), NoticiaContract.CONTENT_URI);
                break;
            default:
                throw new IllegalArgumentException(getContext().getResources().getString(R.string.err_prov_uridesconocida) + uri.toString());

        }
        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        // Validar la uri
        if (uriMatcher.match(uri) != NoticiaContract.ALLROWS) {
            throw new IllegalArgumentException(getContext().getResources().getString(R.string.err_prov_insert) + uri);
        }


        // Si es necesario, verifica los valores

        // Inserción de nueva fila
        SQLiteDatabase db = noticiasDbHelper.getWritableDatabase();
        long rowId = db.insert(NoticiaContract.NoticiaEntry.TABLE_NAME, null, contentValues);
        if (rowId > 0) {
            Uri uri_noticia =
                    ContentUris.withAppendedId(NoticiaContract.CONTENT_URI, rowId);
            //notificamos al observador que ha habido un cambio
            getContext().getContentResolver().notifyChange(uri_noticia, null);
            return uri_noticia;
        } else
            throw new SQLException(getContext().getResources().getString(R.string.err_insert) + uri);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = noticiasDbHelper.getWritableDatabase();
        int affected;
        switch (NoticiaContract.uriMatcher.match(uri)) {
            case NoticiaContract.ALLROWS:
                affected = db.delete(NoticiaContract.NoticiaEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case NoticiaContract.SINGLE_ROW:
                String idActividad = uri.getPathSegments().get(1);
                affected = db.delete(NoticiaContract.NoticiaEntry.TABLE_NAME,
                        NoticiaContract.NoticiaEntry._ID + "=" + idActividad
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(getContext().getResources().getString(R.string.err_prov_uridesconocida) + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        //devuelve el número de filas afectadas
        return affected;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase db = noticiasDbHelper.getWritableDatabase();
        int affected;
        switch (NoticiaContract.uriMatcher.match(uri)) {
            case NoticiaContract.ALLROWS:
                affected = db.update(NoticiaContract.NoticiaEntry.TABLE_NAME, values,
                        selection, selectionArgs);
                break;
            case NoticiaContract.SINGLE_ROW:
                String idActividad = uri.getPathSegments().get(1);
                affected = db.update(NoticiaContract.NoticiaEntry.TABLE_NAME, values,
                        NoticiaContract.NoticiaEntry._ID + "=" + idActividad
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(getContext().getResources().getString(R.string.err_prov_uridesconocida) + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        //devuelve el número de filas afectadas
        return affected;
    }
}
