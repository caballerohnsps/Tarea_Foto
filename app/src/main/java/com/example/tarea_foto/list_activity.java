package com.example.tarea_foto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_foto.configuracion.SqliteConexion;
import com.example.tarea_foto.configuracion.foto;
import com.example.tarea_foto.configuracion.lista_adapter;

import java.util.ArrayList;
import java.util.List;

public class list_activity extends AppCompatActivity {
    ListView listView;
    List<foto> mData = new ArrayList<>();
    lista_adapter mAdapter;
    SqliteConexion conexion;

    Button btn_atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        conexion = new SqliteConexion(this, null);

        listView = (ListView) findViewById(R.id.listView);
        obtenerTabla();
        mAdapter = new lista_adapter(list_activity.this,mData);
        listView.setAdapter(mAdapter);
        btn_atras = (Button) findViewById(R.id.btnAtras);

        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void obtenerTabla() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        mData.clear();
        Cursor cursor = db.rawQuery(SqliteConexion.SelectTablePhotos, null);


        while (cursor.moveToNext()) {
           foto photograh = new foto();
            photograh.setId(cursor.getString(0));
            photograh.setDescripcion(cursor.getString(2));
            mData.add(photograh);


            Log.d("ListActivity", "ID: " + cursor.getString(0));
            Log.d("ListActivity", "Description: " + cursor.getString(2));
        }

        cursor.close();


        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

}