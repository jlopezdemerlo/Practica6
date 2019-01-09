package net.iessochoa.joseantoniolopez.practica6.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase que mantiene el POJO de la noticia
 */
public class Noticia  {

    private String id;
    private String title;//título de la notica
    private String link;//Url a la noticia completa
    private String description;
    private String category;//categoria de la notica
    private String enclosure;//URL a la foto de la noticia
    private Date pubDate;//fecha de publicación


    //getter

    public Noticia(String id, String title, String link, String description, String category, String enclosure, Date pubDate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.category = category;
        this.enclosure = enclosure;
        this.pubDate = pubDate;
    }

    public Noticia(){};
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Date getpubDate() {
        return pubDate;
    }



    public String getEnclosure() {
        return enclosure;
    }
    //setter

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public void setpubDate(String pubDate) {
        try {
            this.pubDate = cambiaFormatoFecha(pubDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    /**
     * La noticia en el canal se guarda en el formato: Tue, 10 Jan 2017 09:25:42 GMT
     * Este método nos permitie obtener un tipo Date
     * @param fecha: String en formato Tue, 10 Jan 2017 09:25:42 GMT
     * @return
     * @throws ParseException
     */
    public static Date cambiaFormatoFecha(String fecha) throws ParseException {

        Date date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH).parse(fecha);
        return date;
    }
    @Override
    public String toString() {
        return  id + '-' + getFechaFormatoLocal()+'-'+category + '-' +
                title ;
    }

    /**
     * Devuelve un string con la fecha en formato local dependiente la configuración del móvil
     * @return
     */
    public String getFechaFormatoLocal()  {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());

        return df.format(pubDate);
    }
    //Codigo Parcelable

}
