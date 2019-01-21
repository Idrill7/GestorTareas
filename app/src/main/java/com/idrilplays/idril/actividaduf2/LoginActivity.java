package com.idrilplays.idril.actividaduf2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView subtitulo;
    private TextView registro;
    private ImageView circulo;
    private TextInputEditText cajaUser;
    private TextInputEditText cajaPass;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Ocultamos la Actionbar
        //getSupportActionBar().hide();

        //Obtenemos las views segun su identificador
        titulo = (TextView) findViewById(R.id.titulo);
        subtitulo = (TextView) findViewById(R.id.subtitulo);
        registro = (TextView) findViewById(R.id.nuevoUser);
        circulo = (ImageView) findViewById(R.id.circuloLogin);
        cajaUser = (TextInputEditText) findViewById(R.id.cajaUser);
        cajaPass = (TextInputEditText) findViewById(R.id.cajaPassword);

        // Creamos un objeto TypeFace, a este le indicamos que de los asset obtenga la fuente mediante el nombre dado
        Typeface miFuente = Typeface.createFromAsset(getAssets(), "big_noodle_titling_oblique.ttf");
        // Asociamos la view con la fuente mediante el metodo setTypeface
        titulo.setTypeface(miFuente);
        subtitulo.setTypeface(miFuente);
    }

    /*
     * Creacion del metodo onclick para llamarlo desde la interfaz "android:onClick="crearCuenta""
     * 1. Debe de ser publico
     * 2. No puede devolver nada
     * 3. Tiene que recibir como parametro un view, obligatorio y necesario
     */
    /**
     * Metodo con un toast para indicar que el registro de cuentas no esta implementado aun
     * @param view la view que es el TextView
     */
    public void crearCuenta(View view) {
        // Creamos un objeto de la clase Toast
        Toast toastCuenta = Toast.makeText(this,"Funcionalidad no implementada", Toast.LENGTH_LONG);
        // Creamos objeto de la clase Animation
        Animation animacionRegistrar = AnimationUtils.loadAnimation(this, R.anim.animacionregistrar);
        //Aniciamos la animacion
        view.startAnimation(animacionRegistrar);
        //  Establecemos la posicion
        toastCuenta.setGravity(Gravity.BOTTOM,0,280);
        //Mostramos el toast
        toastCuenta.show();
    }

    /**
     * Animacion al hacer click en el logo que mueve el circulo trasero
     * @param view la view que hace la llamada en el onclick
     */
    public void moverCirculo(View view) {
        // Creamos objeto de la clase Animation
        Animation animacionRegistrar = AnimationUtils.loadAnimation(this, R.anim.animacioncirculosplash);
        //Aniciamos la animacion
        circulo.startAnimation(animacionRegistrar);

        //Cambiamos el color del subtitulo
        subtitulo.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        subtitulo.setShadowLayer(5,0,0, android.R.color.white);
    }

    /**
     * Toast para recordar contrasena
     * @param view la view que hace la llamada en el onclick, el textView
     */
    public void recordarPass(View view) {


        String textoUser = cajaUser.getText().toString();
        // Si coincide el usuario , se le dice cual es la pass

        if ( textoUser.equals("Alex")) {

            // Creamos un objeto de la clase Toast
            Toast toastPass = Toast.makeText(this,"Alex, tu contrasena es: IRONman19" , Toast.LENGTH_LONG);
            //  Establecemos la posicion
            toastPass.setGravity(Gravity.BOTTOM,0,450);
            //Mostramos el toast
            toastPass.show();

        } // Si no coincide, se le indica
        else {
            // Creamos un objeto de la clase Toast
            Toast toastPass = Toast.makeText(this,"No eres Alex" , Toast.LENGTH_LONG);
            //  Establecemos la posicion
            toastPass.setGravity(Gravity.BOTTOM,0,450);
            //Mostramos el toast
            toastPass.show();
        }


    }

    /**
     * Funcionalidad al boton de Login para cambiar de Activity
     * @param view que es el Button de acceso
     *
     */
    public void hacerLogin(View view){

        String textoUser = cajaUser.getText().toString();
        String textoPassword = cajaPass.getText().toString();

        // Si coincide el usuario y la password pasa a la siguiente Activity
        if ( textoUser.equals("Alex") && textoPassword.equals("IRONman19")) {

            mp = MediaPlayer.create(this, R.raw.correctlogin);
            mp.setVolume(0.4f,0.4f);
            mp.start();
            //Creamos objeto de la clase Intent
            Intent intent = new Intent(this, MainActivity.class);
            // Comenzamos el cambio de actividad
            startActivity(intent);


        } // Si no coincide, se le indica
        else {
            mp = MediaPlayer.create(this, R.raw.wronglogin);
            mp.setVolume(0.2f,0.2f);
            mp.start();
            // Creamos un objeto de la clase Toast
            Toast toastPass = Toast.makeText(this,"Credenciales no validas" , Toast.LENGTH_LONG);
            //  Establecemos la posicion
            toastPass.setGravity(Gravity.TOP,0,300);
            //Mostramos el toast
            toastPass.show();
        }

    }

}
