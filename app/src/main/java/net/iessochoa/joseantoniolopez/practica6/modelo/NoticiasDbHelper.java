package net.iessochoa.joseantoniolopez.practica6.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JoseA on 02/01/2017.
 */

public class NoticiasDbHelper extends SQLiteOpenHelper {
    //si cambiamos la versión, en SQLiteOpenHelper se llamará a onUpdate
    private static final int DATABASE_VERSION = 1;
    //nombre del archivo
    private static final String DATABASE_NAME = "Noticias.db";
    //creamos las sentencias que nos serán útiles en la clase. Muchas de ellas parametrizadas
    private static final String CREATE_TABLE = "CREATE TABLE if not exists " + NoticiaContract.NoticiaEntry.TABLE_NAME + " ("
            + NoticiaContract.NoticiaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NoticiaContract.NoticiaEntry.TITLE + " TEXT NOT NULL,"
            + NoticiaContract.NoticiaEntry.LINK + " TEXT NOT NULL,"
            + NoticiaContract.NoticiaEntry.DESCRIPTION + " TEXT, "
            + NoticiaContract.NoticiaEntry.CATEGORY + " TEXT, "
            + NoticiaContract.NoticiaEntry.ENCLOSURE + " TEXT, "
            + NoticiaContract.NoticiaEntry.PUBDATE + " TEXT, "
            + "UNIQUE (" + NoticiaContract.NoticiaEntry.LINK + ")"
            + ")";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + NoticiaContract.NoticiaEntry.TABLE_NAME;

    public NoticiasDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        crearDatosPrueba(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    /**
     * Nos permite crear unos registros de prueba del canal RSS
     *
     * @param db
     */
    private void crearDatosPrueba(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "Un baile de proteínas para crear leche artificial casi tan buena como la materna");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/biomedicina/52799/un-baile-de-proteinas-para-crear-leche-artificial/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "La leche humana tiene unas 1.600 proteínas diferentes capaces de adaptarse a cada bebé, ahora una 'start-up' pretende usar proteínas recombinantes para replicarla");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Biomedicina");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/files/178456/BabyMilk_th.jpg");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2017-01-02");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "Un asesinato junto a un Amazon Echo reaviva el debate sobre la privacidad");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/informatica/52798/un-asesinato-junto-a-un-amazon-echo-reaviva-el/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "Los datos grabados en un altavoz Echo de Amazon podrían ayudar a esclarecer un caso de asesinato en EEUU, pero falta consenso sobre el papel de la empresa");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Informatica");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/files/178452/EchoAsesinato_th.jpg");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2017-01-02");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "La forma en que el cerebro procesa los sonidos, posible clave para diagnosticar contusiones");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/biomedicina/52783/la-forma-en-que-el-cerebro-procesa-los-sonidos/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "Un equipo de investigación desarrolla una prueba para diagnosticar contusiones cerebrales a partir de cómo interpreta el habla el cerebro, pero podría no ser práctica en un entorno real");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Biomedicina");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/files/178390/contusiones_th.jpg");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2016-12-29");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "Galileo, la apuesta de la Unión Europea contra el GPS de EEUU, abre su señal al público");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/negocios/52785/galileo-la-apuesta-de-la-union-europea-contra-el/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "Desarrollado por la Unión Europea y la Agencia Espacial Europea, la constelación de satélites europeos para posicionamiento Galileo ofrecerá nuevas oportunidades con un claro componente civil");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Negocios");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/negocios/52785/galileo-la-apuesta-de-la-union-europea-contra-el/");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2016-12-28");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "Dimisiones, despidos y acciones en barrena nublan el futuro de OvaScience");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/biomedicina/52810/dimisiones-despidos-y-acciones-en-barrena-nublan/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "La empresa que prometía fertilidad a cualquier edad cambia de CEO dos veces en un año y piensa reducir un 30 % la plantilla\n");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Biomedicina");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/files/178530/OvaScience_th.jpg");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2017-01-04");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "Un coste prohibitivo y la falta de contenidos lastran el gran año de la realidad virtual");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/movil/52809/un-coste-prohibitivo-y-la-falta-de-contenidos/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "Tras años de expectación, el mercado ha visto en 2016 los primeros cascos de RV de calidad. Sin embargo, las cifras de su debut están lejos de considerarse un éxito");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Biomedicina");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/files/178528/RV2016_TR_po.jpg");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2017-01-04");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "El lado más viral de la tecnología: nuestros mejores GIF de 2016");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/robotica/52807/el-lado-mas-viral-de-la-tecnologia-nuestros/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "Internet y las redes sociales han convertido los GIF en una forma de comunicación indispensable, también para los avances tecnológicos. Aquí está nuestra selección con los que hemos publicado");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Biomedicina");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/files/178523/gafas%20digitales%20de%20Apple_th.gif");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2017-01-04");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "Un enjambre de 1.000 robots transporta y almacena los productos frescos del súper");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/negocios/52806/un-enjambre-de-1000-robots-transporta-y-almacena/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "Un supermercado en línea de Reino Unido asegura que sus instalaciones están más automatizadas que las de Amazon");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Negocios");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/files/178515/OcadoSupermercadoRobots_th.jpg");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2017-01-03");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, "Así aprende Facebook sobre tu vida fuera de internet: brókers de datos");
        values.put(NoticiaContract.NoticiaEntry.LINK, "http://www.technologyreview.es/movil/52805/asi-aprende-facebook-sobre-tu-vida-fuera-de/");
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION, "Según ProPublica, la red social recurre a brókers de datos para comprar datos sobre la vida 'offline' de sus usuarios y mejorar sus ingresos por publicidad");
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, "Negocios");
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, "http://www.technologyreview.es/files/178517/FacebookBrokersPrivacida_th.jpg");
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, "2017-01-03");
        db.insertOrThrow(NoticiaContract.NoticiaEntry.TABLE_NAME, null, values);


    }

    /**
     * Dada una posición del cursor, nos devuelve un objeto Notcia
     */
    public static Noticia deCursorANoticia(Cursor cursor) {

        String id = cursor.getString(0);
        String title = cursor.getString(1);
        String link = cursor.getString(2);
        String description = cursor.getString(3);
        String category = cursor.getString(4);
        String enclosure = cursor.getString(5);
        Date pubdate = fechaBDtoFecha(cursor.getString(6));


        return new Noticia(id, title, link, description, category, enclosure, pubdate);

    }

    /**
     * La fecha en la base de datos la guardamos en formato AAAA-MM-DD
     * y como un string. Este método nos devuelve un Date
     * @param f
     * @return
     */
    public static Date fechaBDtoFecha(String f) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(f);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }
        return fecha;
    }

    /**
     * Nos permite transformar una noticia a ContentValues
     * @param noticia
     * @return
     */
    public static ContentValues deNoticiaAContentValues(Noticia noticia){
        ContentValues values = new ContentValues();
        values.put(NoticiaContract.NoticiaEntry.TITLE, noticia.getTitle());
        values.put(NoticiaContract.NoticiaEntry.LINK, noticia.getLink());
        values.put(NoticiaContract.NoticiaEntry.DESCRIPTION,noticia.getDescription());
        values.put(NoticiaContract.NoticiaEntry.CATEGORY, noticia.getCategory());
        values.put(NoticiaContract.NoticiaEntry.ENCLOSURE, noticia.getEnclosure());
        values.put(NoticiaContract.NoticiaEntry.PUBDATE, fechaToFechaDB(noticia.getpubDate()));
        return  values;
    }

    /**
     * El inverso del anterior
     * @param fecha
     * @return
     */
    public	static String fechaToFechaDB(Date fecha){
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(fecha);
    }

}
