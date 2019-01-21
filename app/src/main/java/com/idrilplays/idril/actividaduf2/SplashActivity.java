package com.idrilplays.idril.actividaduf2;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener{

    private TextView tituloApp;
    private ImageView cruzLogo;
    private ImageView ticLogo;
    private ImageView circuloSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Obtenemos las views segun su identificador
        tituloApp = (TextView) findViewById(R.id.tituloApp);
        cruzLogo = (ImageView) findViewById(R.id.logoCruz);
        ticLogo = (ImageView) findViewById(R.id.logoTick);
        circuloSplash = (ImageView) findViewById(R.id.circuloSplash);

        //Ocultamos la Actionbar
       //getSupportActionBar().hide();

        // Creamos un objeto TypeFace, a este le indicamos que de los asset obtenga la fuente mediante el nombre dado
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "big_noodle_titling.ttf");
        // Asociamos la view con la fuente mediante el metodo setTypeface
        tituloApp.setTypeface(miFuente);

        // Animaciones
        Animation animacionCruz = AnimationUtils.loadAnimation(this, R.anim.animacioncruz);
        Animation animacionTic = AnimationUtils.loadAnimation(this, R.anim.animaciontic);
        Animation animacionTitulo = AnimationUtils.loadAnimation(this, R.anim.animaciontextosplash);
        Animation animacionCirculo = AnimationUtils.loadAnimation(this,R.anim.animacioncirculosplash);

        //Ligamos las animaciones
        cruzLogo.startAnimation(animacionCruz);
        ticLogo.startAnimation(animacionTic);
        tituloApp.startAnimation(animacionTitulo);
        circuloSplash.startAnimation(animacionCirculo);

        // Ponemos a la escucha la animacion para pasar al Login
        animacionTitulo.setAnimationListener(this);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    //Usaremos este metodo para que cuando acabe la animacion, se lance la activity Login
    public void onAnimationEnd(Animation animation) {
        //Para cambiar de una activity a otra hay que crear un Intent
        //Establecemos por parametro la "ruta" del origen y el destino
        Intent intent = new Intent(this, LoginActivity.class);
        // Iniciamos el cambio de activity
        startActivity(intent);

        //Acabamos el Activity del splash ya que no vamos a retroceder a ella
        finish();

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
