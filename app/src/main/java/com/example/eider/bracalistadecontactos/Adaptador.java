package com.example.eider.bracalistadecontactos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Eider on 09/03/2017.
 */

public class Adaptador extends BaseAdapter{

Context contexto;
    ArrayList<contacto> listacontacto;


    public Adaptador(Context contexto, ArrayList<contacto> listacontacto) {
        this.contexto = contexto;
        this.listacontacto = listacontacto;
    }

    @Override
    public int getCount() {
        return listacontacto.size();
    }

    @Override
    public Object getItem(int position) {
        return listacontacto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View renglon =convertView;
        if(convertView==null){
            LayoutInflater inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            renglon=inflador.inflate(R.layout.renglon_de_3,parent,false);


        }

        TextView nombre= (TextView) renglon.findViewById(R.id.nombre);
        TextView numero= (TextView) renglon.findViewById(R.id.numero);
        TextView correo= (TextView) renglon.findViewById(R.id.correo);

        nombre.setText(listacontacto.get(position).getNombre());
        numero.setText(listacontacto.get(position).getTelefono());
        correo.setText(listacontacto.get(position).getCorreo());

        return renglon;
    }


}
