package com.idrilplays.idril.actividaduf2;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cambio de color del texto de la ActionBar mediante html
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + getString(R.string.actionBarMainText) + "</font>"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Anadimos elementos a la barra, este caso los del archivo menu.xml
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        //Creamos el objeto de EditText que sera la caja de texto que va a ser contenida dentro del AlertDialog, el contexto sera this, por ello lo creamos antes.
        EditText cajaTexto = new EditText(this);

        /**
         * Construimos un objeto AlertDialog que es el de la alerta de dialogo y llamamos al constuctor con el metodo Builder(), le pasamos el contexto que va a ser this, este.
         * metodo .setTitle , establecemos el titulo
         * metodo .setMessage , establece el mensaje del cuadro de dialogo
         * metodo .setView, establecemos el View que va a contener, en este caso un EditText
         * metodo .setPositiveButton, establecemos el texto que muestra y un listener del evento onClick que lanza al ser pulsado del boton "positivo",
         * metodo .setPositiveButton, establecemos el texto que muestra y como segundo parametro pasaremos un "null" para que no haga nada al hacer click en el boton"negativo",
         * metodo .create que crea el dialogo de alerta
         */

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(Html.fromHtml("<font color=\"#ffa751\">" + getString(R.string.dialogTitle) + "</font>"))
                .setMessage("\tInserte una tarea pendiente")
                .setView(cajaTexto)
                .setNegativeButton(getString(R.string.dialogNegativeText),null)
                .setPositiveButton(getString(R.string.dialogPositiveText), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .create();


        // mostramos la ventana
        dialog.show();
        // Cambiamos el color de los botones de cancelar y aniadir
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        return super.onOptionsItemSelected(item);
    }
}
