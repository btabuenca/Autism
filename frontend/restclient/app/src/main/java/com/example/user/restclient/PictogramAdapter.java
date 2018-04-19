package com.example.user.restclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.user.restclient.R;
import com.squareup.picasso.Picasso;

/**
 * Created by User on 30/10/2017.
 */

public class PictogramAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Pictogram> datos;
    private int idResource;
    public PictogramAdapter(Context context, int idResource, ArrayList datos) {
        super(context, idResource, datos);
        this.context = context;
        this.datos = datos;
        this.idResource = idResource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(idResource, parent,false);

        TextView tvPu = (TextView) item.findViewById(R.id.txtName);
        tvPu.setText(datos.get(position).getCommon_name());

        TextView tvJu = (TextView) item.findViewById(R.id.txtDescription);
        tvJu.setText(datos.get(position).getNationality());


        //ImageView imagePlayer = (ImageView) item.findViewById(R.id.imagePlayer);
        //Picasso.with(context).
        //        load(datos.get(position).getImage_path()).
         //       resize(75,75).into(imagePlayer);



        return item;
    }
}
