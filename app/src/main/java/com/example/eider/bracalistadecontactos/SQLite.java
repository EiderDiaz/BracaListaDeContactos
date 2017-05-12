package com.example.eider.bracalistadecontactos;

/**
 * Created by Eider on 09/05/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

    private static final String NOMBREBD = "contactos.bd";
    private static final int VERSIONBD = 1;
    private SQLiteDatabase BD;

    public static final String NOMBRETBLCONTACTOS="tblcontactos";
    public static final String CAMPO_ID = "idcontactos";
    public static final String CAMPO_NOMBRE="nombrecontacto";
    public static final String CAMPO_TELEFONO="telefonocontacto";
    public static final String CAMPO_EMAIL="emailcontacto";

    public static final String CREARTBLCONTACTOS=" CREATE TABLE "+NOMBRETBLCONTACTOS+" ("+CAMPO_ID+" integer primary key autoincrement , "+CAMPO_NOMBRE+" text not null , "+CAMPO_TELEFONO+" text , "+CAMPO_EMAIL+" text) ;";
    public static final String INSERTARREGISTRO (String nombre, String telefono, String email){
        return "INSERT INTO "+ NOMBRETBLCONTACTOS+ "("+ CAMPO_NOMBRE+" , "+CAMPO_TELEFONO+" , "+CAMPO_EMAIL+
                ") VALUES ('"+ nombre+ "' , '"+ telefono+"' , '" + email + "');";
    }

    public static final String SELECCIONARDATOSTABLACONTACTOS= "SELECT * FROM "+NOMBRETBLCONTACTOS;



    public  SQLite(Context context){

        super(context, NOMBREBD, null, VERSIONBD);
        BD = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREARTBLCONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tblcontactos");
        onCreate(db);
    }

    public void insertarRegistro(String nombre, String telefono, String email){

        BD.execSQL(INSERTARREGISTRO(nombre,telefono,email));

    }

    public Cursor seleccionarTablaContactos(){

        Cursor c = BD.rawQuery(SELECCIONARDATOSTABLACONTACTOS,null);

        return c;

    }



}
