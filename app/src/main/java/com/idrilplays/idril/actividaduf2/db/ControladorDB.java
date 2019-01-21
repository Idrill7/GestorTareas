package com.idrilplays.idril.actividaduf2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControladorDB extends SQLiteOpenHelper {

    public ControladorDB(Context context) {
        super(context, "com.idrilplays.idril.actividaduf2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Creamos la tabla
        db.execSQL("CREATE TABLE TAREA (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    /**
     * Metodo publico que no retorna nada y recibe por argumento una tarea que es insertada
     * @param tarea texto de la tarea
     */
    public void addTarea(String tarea){

        /*
         * Creamos un objeto de la clase ContentValues y mediante el metodo put() le daremos una clave:valor, esta clave sera
         * el nombre del campo y un valor el que recibimos por el argumento.
         * Esto lo usaremos en el metodo insert
         */
        ContentValues registro = new ContentValues();
        registro.put("NOMBRE", tarea);

        /*
         * Primero se abre la conexion a la BD, al controlador, this, le ejecutamos el metodo para lectura
         * y escritura, getWritableDatabase ya que vamos a hacer un insert.
         * Esto devuelve una referencia de la BD que guardaremos en una variable de SQLiteDatabase
         */
        SQLiteDatabase db = this.getWritableDatabase();

        /*
         * Segundo ejecutamos la sentencia, en este caso un insert.
         * Al metodo insert() le pasaremos por argumento el nombre de la tabla, un nulo en ColumnHack y el ContentValues, que sera el registro
         * a insertar, que contiene el nombre del campo y el valor-
         */
        db.insert("TAREA", null, registro);

        /*
         * Como ultimo y tercer paso, cerramos la conexion con la BD
         */
        db.close();
    }

    /**
     * Metodo publico que devuelve un Array de String que seran las tareas
     */
    public String[] obtenerTareas() {

        // Abrimos la conexion esta vez con getReadableDatabase porque es solo para una lectura, una query
        SQLiteDatabase db = this.getReadableDatabase();

        /*
         * Realizamos la consulta mediante el metodo rawQuery, le pasamos como argumento la sentencia y como segundo un nulo ya que no vamos a hacer uso de selectionArgs
         * Obtenemos un conjunto de registros, un cursor, donde vamos a guardar el resultado de la SELECT.
         *
         * Recorremos el cursor, nos movemos a la primera posicion y obtenemos el texto, asi hasta obtener de todos los registros los campos.
         */
        Cursor cursor = db.rawQuery("SELECT * FROM TAREA", null);
        // Guardamos cuantos registros hemos obtenido en el cursor
        int registros = cursor.getCount();

        // Si registros es 0, la tabla esta vacia
        if (registros == 0) {
            // Cerramos la base de datos
            db.close();
            // Retornamos null
            return null;
        }
        // Si el numero de registros es mayor que 0, guardamos el campo NOMBRE y lo vamos guardando en un Array para devolverlo
        else {
            // nos creamos el Array donde guardaremos los nombres de las tareas, el tamanio sera tanto como los resgistros
            String[] tareas = new String[registros];
            // nos movemos al primer registro
            cursor.moveToFirst();
            // recorremos con un for todos los registros, nos interesa el campo que tiene la posicion 1, que es NOMBRE y no 0 que es ID
            for (int i = 0; i < registros; i++) {
                // guardamos el campo NOMBRE en el Array de String
                tareas[i] = cursor.getString(1);
                // nos movemos al siguiente registro
                cursor.moveToNext();
            }
            // una vez se sale del for, hemos recorrido todos los registros
            // cerramos la base de datos
            db.close();
            // retornamos el array creado
            return tareas;
        }

    }

    /**
     * Metodo que devuelve el numero de registros que tiene la Tabla TAREA
     */
    public int numeroRegistros(){

        // Abrimos la BD para lecturas
        SQLiteDatabase db = this.getReadableDatabase();

        // Hacemos un select y lo guardamos en el cursor
        Cursor cursor = db.rawQuery("SELECT * FROM TAREA" , null);

        // Guardamos cuantos registros hemos obtenido en el cursor
        int registros = cursor.getCount();

        // Cerramos la conexion
        db.close();

        // Retornamos el numero de registros guardados en el cursor
        return registros;



    }
}
