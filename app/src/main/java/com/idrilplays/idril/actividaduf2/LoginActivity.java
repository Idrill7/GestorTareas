package com.idrilplays.idril.actividaduf2;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView subtitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Obtenemos las views segun su identificador
        titulo = (TextView) findViewById(R.id.titulo);
        subtitulo = (TextView) findViewById(R.id.subtitulo);

        // Creamos un objeto TypeFace, a este le indicamos que de los asset obtenga la fuente mediante el nombre dado
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "big_noodle_titling_oblique.ttf");
        // Asociamos la view con la fuente mediante el metodo setTypeface
        titulo.setTypeface(miFuente);
        subtitulo.setTypeface(miFuente);
    }
}
