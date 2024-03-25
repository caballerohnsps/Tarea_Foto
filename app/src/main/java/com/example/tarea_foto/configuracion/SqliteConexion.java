package com.example.tarea_foto.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteConexion extends SQLiteOpenHelper {
    public static final String nameDataBase = "BD_Foto";
    public static final int versionDataBase = 1;
    public static final String tableName = "Fotos";
    public static final String columnid = "Id";
    public static final String columnphoto = "Foto";
    public static final String columndescription = "descripcion";
    public static final String CreateTable =
            "CREATE TABLE " + tableName + "(" +
                    columnid + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    columnphoto + " BLOB," +
                    columndescription + " TEXT" +
                    ")";
    public static final String DropTablePhotos = "DROP TABLE IF EXISTS " + tableName;

    public static final String SelectTablePhotos= "SELECT * FROM " + tableName;


    public SqliteConexion(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, nameDataBase, factory, versionDataBase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CreateTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DropTablePhotos);
        onCreate(db);
    }
}
