package com.example.tarea_foto.configuracion;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tarea_foto.R;

import java.util.List;

public class lista_adapter extends ArrayAdapter<foto> implements View.OnClickListener{
    private List<foto> mData;
    private Context context;

    SqliteConexion conexion;
    public static class ViewHolder{
        ImageView imageProfile;
        TextView txtDescription;
    }

    public lista_adapter (@NonNull Context context, List<foto> mData) {
        super(context, R.layout.activity_list, mData);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        foto photograh = mData.get(position);
        ViewHolder viewHolder;
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_activity,null);
        }
        ImageView imagen = view.findViewById(R.id.profile);
        TextView description = view.findViewById(R.id.itemDescription);

        imagen.setImageBitmap(obtenerImagen(photograh.getId()));
        description.setText(photograh.getDescripcion());

        return view;
    }


    private Bitmap obtenerImagen(String id) {
        conexion = new SqliteConexion(context, null);
        SQLiteDatabase db = conexion.getReadableDatabase();
        Bitmap bitmap;
        String selectQuery = "SELECT " + SqliteConexion.columnphoto +" FROM " + SqliteConexion.tableName + " WHERE id = ?";

        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {

            byte[] imageData = Base64.decode(cursor.getBlob(cursor.getColumnIndexOrThrow("Foto")), Base64.DEFAULT);


            bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        }
        else{
            bitmap = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.profiledefault);
        }

        cursor.close();
        db.close();
        return bitmap;
    }





}
