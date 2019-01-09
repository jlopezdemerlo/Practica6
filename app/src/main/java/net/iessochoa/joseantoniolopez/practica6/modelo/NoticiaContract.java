package net.iessochoa.joseantoniolopez.practica6.modelo;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by JoseA on 02/01/2017.
 * Para entender esta clase, tenéis la explicación del ContentProvider en
 * http://www.hermosaprogramacion.com/2015/06/tutorial-android-crear-un-content-provider-personalizado/
 */

public class NoticiaContract {
    public static class NoticiaEntry{
        public static final String TABLE_NAME = "noticia";
        //nombre de las columnas
        public static final String _ID = BaseColumns._ID;//esta columna es necesaria para Android
        public static final String ITEM="item";
        public static final String TITLE="title";
        public static final String LINK="link";
        public static final String DESCRIPTION="description";
        public static final String CATEGORY="category";
        public static final String PUBDATE="pubDate";
        public static final String GUID="guid";
        public static final String ENCLOSURE="enclosure";
    }
    //*************Contrato ContentProvider***********
    //Autoridad del ContentProvider
    public final static String AUTHORITY ="net.iessochoa.joseantoniolopez.practica6.modelo.noticiaprovider";
    //Uri de contenido principal
    public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" +NoticiaEntry.TABLE_NAME);
    //Código para Uri para mútiples registros
    public static final int ALLROWS=1;
    //código para uris de un sólo registro
    public static final int SINGLE_ROW=2;

    /**
     * Comparador de URIs de contenido
     */
    public static final UriMatcher uriMatcher;

    // Asignación de URIs
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, NoticiaEntry.TABLE_NAME, ALLROWS);
        uriMatcher.addURI(AUTHORITY, NoticiaEntry.TABLE_NAME + "/#", SINGLE_ROW);
    }

    //Tipo MIME que retorna la consulta de una sola fila

    public final static String SINGLE_MIME =
            "vnd.android.cursor.item/vnd." + AUTHORITY + NoticiaEntry.TABLE_NAME;
    //Tipo MIME que retorna la consulta de {@link CONTENT_URI}

    public final static String MULTIPLE_MIME =
            "vnd.android.cursor.dir/vnd." + AUTHORITY + NoticiaEntry.TABLE_NAME;

}
