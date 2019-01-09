package net.iessochoa.joseantoniolopez.practica6.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import net.iessochoa.joseantoniolopez.practica6.R;
import net.iessochoa.joseantoniolopez.practica6.modelo.Noticia;
import net.iessochoa.joseantoniolopez.practica6.modelo.NoticiaContract;
import net.iessochoa.joseantoniolopez.practica6.modelo.NoticiasDbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_ERROR = "Error P6:";
    @BindView(R.id.tv_Noticias)
    TextView tvNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pruebaContentProvider();
    }

    private void pruebaContentProvider() {
        ContentResolver contentResolver = getContentResolver();
        try {
            //leemos el contendido de la base de datos
            Cursor cursor = contentResolver.query(NoticiaContract.CONTENT_URI, null, null, null, null);
            if (cursor.moveToFirst()) {
                //mostraremos las noticias por pantalla
                StringBuilder noticias = new StringBuilder();
                do {
                    Noticia noticia = NoticiasDbHelper.deCursorANoticia(cursor);
                    String s=noticia.toString();
                    noticias.append(noticia.toString());
                    noticias.append("\n");
                } while (cursor.moveToNext());
                tvNoticias.setText(noticias.toString());

            }
        } catch (SQLException e) {
            Log.e(TAG_ERROR, e.getMessage());
        }
    }
}

