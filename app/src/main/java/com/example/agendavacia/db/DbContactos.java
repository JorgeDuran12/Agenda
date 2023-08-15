package com.example.agendavacia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.agendavacia.entidades.Contactos;

import java.util.ArrayList;

public class DbContactos  extends  DbHelper{
    Context context;
    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarContacto(String nombre, String telefono, String correro_electronico ){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();/*Para escribir en la base de datos */
            ContentValues values = new ContentValues();/*Contenedor de valores*/
            values.put("nombre",nombre);/*Guardado para clave valor*/
            values.put("telefono",telefono);
            values.put("correro_electronico",correro_electronico);
            id = db.insert(TABLE_CONTACTO, null , values);/* guardamos el id del resgistro guardado*/
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Contactos>mostrarContactos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();/*para escribir el la base de datos*/

        ArrayList<Contactos> listaContactos = new ArrayList<>();/*creamos un arrays  de tipo contacto*/
        Contactos contacto = null; /*creamos una variable de tipo contactos de combre contacto y la inicialzamos en null*/
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTO, null );
        if (cursorContactos.moveToFirst()){/*nos movemos al primer registro que trae la consultam*/
        do {
            contacto = new Contactos();/*instaciamos el objeto contacto desde la clase Contactos para poder trabajor lo smetodos get y set */
            contacto.setId(cursorContactos.getInt(0));  /*aqui pasamos la informacion de cada registro y cada campo, se trabajo a partir 0*/
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo_electronico(cursorContactos.getString(3));
            /*una vez enviamos los parametros  que nesecitan , trabajos con listaContactos*/
            listaContactos.add(contacto);/*le pasamos la informacion que trae contactos el cual veine de las asignaciones anterioires */
        }while (cursorContactos.moveToNext());
        }
        cursorContactos.close();/*cierre del cursor*/
        return listaContactos;/*retornamos el arrays que armamos */
    }

    public Contactos verContacto(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();/*para escribir el la base de datos*/

        Contactos contacto = null; /*creamos una variable de tipo contactos de combre contacto y la inicialzamos en null*/
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTO +  " WHERE id = " + id + " LIMIT 1 ", null );
        if (cursorContactos.moveToFirst()){
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo_electronico(cursorContactos.getString(3));
        }
        cursorContactos.close();
        return contacto;

    }


    public boolean editarContacto( int id , String nombre, String telefono, String correro_electronico ){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();/*Para escribir en la base de datos */

        try {
            db.execSQL(" UPDATE  " + TABLE_CONTACTO + " SET nombre = '" + nombre  + " ', telefono = '" + telefono+"', correro_electronico = '" + correro_electronico + "' WHERE id = '" +  id + "' ");
            correcto = true;

        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }



    public boolean eliminarContacto( int id ){

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();/*Para escribir en la base de datos */

        try {
            db.execSQL( "DELETE FROM " + TABLE_CONTACTO + " WHERE id = '" + id + "' ");
            correcto = true;

        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

}


