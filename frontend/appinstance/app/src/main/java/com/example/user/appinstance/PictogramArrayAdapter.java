package com.example.user.appinstance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

/**
 * Created by User on 30/10/2017.
 */

public class PictogramArrayAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Pictogram> datos;
    private int idResource;
    public PictogramArrayAdapter(Context context, int idResource, ArrayList datos) {
        super(context, idResource, datos);
        // Guardamos los parámetros en variables de clase.
        this.context = context;
        this.datos = datos;
        this.idResource = idResource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView. Para ello primero creamos el
        // inflater, y después inflamos la vista.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(idResource, parent,false);

        TextView tvId = (TextView) item.findViewById(R.id.txtId);
        tvId.setText(Integer.toString(datos.get(position).getId()));

        TextView tvPu = (TextView) item.findViewById(R.id.txtName);
        tvPu.setText(datos.get(position).getCommon_name());

        TextView tvJu = (TextView) item.findViewById(R.id.txtNacionalidad);
        tvJu.setText(datos.get(position).getNationality());

        ImageView imagePlayer = (ImageView) item.findViewById(R.id.imagePlayer);
        Picasso.with(context).
                load(datos.get(position).getImage_path()).
                resize(75,75).into(imagePlayer);

        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }
}
