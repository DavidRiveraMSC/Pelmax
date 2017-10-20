package com.proyectos.davidrivera.pelmax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by David Rivera on 19/10/2017.
 */

public class BaseHelper extends SQLiteOpenHelper{

    String tabla = "CREATE TABLE PRODUCTOS(ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCTO TEXT, PRECIO INTEGER, CANTIDAD INTEGER)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE PRODUCTOS");
        sqLiteDatabase.execSQL(tabla);
    }
}
