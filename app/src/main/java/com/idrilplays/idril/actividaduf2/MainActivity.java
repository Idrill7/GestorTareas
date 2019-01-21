package com.idrilplays.idril.actividaduf2;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.idrilplays.idril.actividaduf2.db.ControladorDB;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Creamos la referencia a  un objeto de la clase ControladorDB
    private ControladorDB controlador;
    // Creamos la referencia a  un un objeto de la clase ArrayAdapter
    private ArrayAdapter<String> myAdapter;
    // Creamos la referencia al ListView
    private ListView listaTareas;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Le damos la referencia al objeto controlador
        controlador = new ControladorDB(this);
        // Obtenemos la ListView por su identificador
        listaTareas = (ListView) findViewById(R.id.listadoTareas);

        // Lanzamos el metodo actualizarUI para actualizar la interfaz
        actualizarUI();

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
         final EditText cajaTexto = new EditText(this);

        /*
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
                        // Almacenamos la cadena de texto en una variable, que sera el contenido del EditText
                        String tarea= cajaTexto.getText().toString();

                        //Usamos el metodo addTarea para insertar el texto de la caja como una tarea
                        controlador.addTarea(tarea);

                        // Lanzamos el metodo actualizarUI para actualizar la interfaz
                        actualizarUI();

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

    /**
     * Creamos un metodo para actualizar la UI, y que el ListView se actualice
     */
    private void actualizarUI(){

        /*
         *   Tenemos que averiguar cuantos registros tiene la tabla, para ello usamos el metodo del
         *         controlador numeroRegistros, si este es igual a 0 , se le pasa nulo al listView
         */
        if ( controlador.numeroRegistros() == 0 ) {
            listaTareas.setAdapter(null);
        }
        else {
            /*
             * Creamos el objeto ArrayAdapter y le pasamos por argumentos el contexto, con que vamos a rellenar cada elemento del listview que va a ser el archivo item_list.xml
             * dentro del item_list obtenemos el TextView que es lo que contendra el texto, y un metodo de la clase ControladorDB que devuelve el NOMBRE de todas las tareas
             */
            myAdapter = new ArrayAdapter<>(this, R.layout.item_list,R.id.textoTarea, controlador.obtenerTareas());
            // Le establecemos el Adapter al ListView
            listaTareas.setAdapter(myAdapter);
        }

    }

    public void borrarTarea(View view) {

        // Obtenemos el View padre del view que entra por argumento, que es RelativeLayout del boton que llama a este metodo en el onclik
        View parent = (View) view.getParent();
        // Dentro del padre buscamos el identificador del TextView, lo guardamos en una variable TextView que es lo que nos devuelve
        TextView tareaTextView = (TextView) parent.findViewById(R.id.textoTarea);
        // Guardamos en otra variable el contenido del TextView
        String tarea = tareaTextView.getText().toString();

        // Usamos el metodo borrarTarea de la clase ControladorDB y le pasamos como argumento el texto de la tarea que acompana al boton
        controlador.borrarTarea(tarea);

        // sonido al borrar tarea
        mp = MediaPlayer.create(this, R.raw.donetask);
        mp.start();

        // Actualizamos la interfaz
        actualizarUI();
    }

}
