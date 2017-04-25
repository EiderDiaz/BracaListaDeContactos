package com.example.eider.bracalistadecontactos;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyPingCallback;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView lv;
    ImageView adduser;
    ArrayList<String> nose;
    ArrayList<contacto> contactos = new ArrayList<contacto>();
    Adaptador adapter = new Adaptador(this, contactos);
    Client mKinveyClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listview);
        adduser = (ImageView) findViewById(R.id.AddUser);
        //String [] contactos  = getResources().getStringArray(R.array.contactos);

        contactos.add(new contacto("ana laura", "6681642440", "correo"));
        contactos.add(new contacto("NOeider", "6681696325", "correo"));

        //________________menu contextual
        registerForContextMenu(lv);
        //________________menu contextual

        //_______________________ client builder __________________________________
        mKinveyClient = new Client.Builder("kid_r1jkR1q3x","9c81209423954eb88640eeb2a488cebb"
                , this.getApplicationContext()).build();

        //_______________________ client builder __________________________________



        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("DATO", "yi");
                startActivityForResult(i, 1);

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre = contactos.get(position).getNombre();
                Toast.makeText(MainActivity.this, nombre, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //________________menu contextual _______________________________________
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listview) {

            AdapterView.AdapterContextMenuInfo infoContacto = (AdapterView.AdapterContextMenuInfo) menuInfo;
            String[] elementosMenu = getResources().getStringArray(R.array.elementosMenuConteztual);
            menu.setHeaderIcon(R.drawable.add);
            menu.setHeaderTitle(contactos.get(infoContacto.position).getNombre());
            for (int i = 0; i < elementosMenu.length; i++) {
                menu.add(Menu.NONE, i, i, elementosMenu[i]);

            }
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo infoContacto = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String[] elementosMenu = getResources().getStringArray(R.array.elementosMenuConteztual);
        int posicionSeleccionado = item.getItemId();
        String opcionSeleccionada = elementosMenu[posicionSeleccionado];
        String concactoselecionado = contactos.get(infoContacto.position).getNombre();
        Toast.makeText(this, "opcion:" + opcionSeleccionada + " index:" + infoContacto.position, Toast.LENGTH_SHORT).show();

        switch (opcionSeleccionada) {
            case "Editar":

                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                String cadena = String.valueOf(infoContacto.position);
                i.putExtra("DATO", cadena);

                i.putExtra("nombre", contactos.get(infoContacto.position).getNombre());
                i.putExtra("numero", contactos.get(infoContacto.position).getTelefono());
                i.putExtra("correo", contactos.get(infoContacto.position).getCorreo());
                startActivityForResult(i, 0);

                // Toast.makeText(this, "voy a mandar"+infoContacto.position+"y"+ contactos.get(infoContacto.position).getNombre(), Toast.LENGTH_SHORT).show();
                break;
            case "Eliminar":
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);

                dialog1.setTitle("importante");
                dialog1.setMessage("estas seguro de eliminar este contato?");
                dialog1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        contactos.remove(infoContacto.position);
                        lv.setAdapter(adapter);
                    }
                });
                dialog1.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
             dialog1.show();
                break;
            case "llamar":
                String numero = contactos.get(infoContacto.position).getTelefono();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return false;
                }
                startActivity(intent);
                break;

            case "mensaje de texto":
                Intent intenttexto = new Intent(MainActivity.this,llamar.class);
                intenttexto.putExtra("nombre",contactos.get(infoContacto.position).getNombre());
                intenttexto.putExtra("numero",contactos.get(infoContacto.position).getTelefono());
                intenttexto.putExtra("correo",contactos.get(infoContacto.position).getCorreo());
                startActivity(intenttexto);
                break;

         }


        return true;

    }
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {//lo que mandas de aqui
            if(resultCode == Activity.RESULT_OK){//lo que resulta de la otra actividad

                String nombre=data.getStringExtra("nombre");
                String numero=data.getStringExtra("numero");
                String correo=data.getStringExtra("correo");
                contactos.add(new contacto(nombre,numero,correo));
                lv.setAdapter(adapter);



            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==0){
            if(resultCode == Activity.RESULT_OK){

                String indice=data.getStringExtra("indice");
                String nombre=data.getStringExtra("nombre");
                String numero=data.getStringExtra("numero");
                String correo=data.getStringExtra("correo");
                int indiceint = Integer.valueOf(indice);
                contactos.set(indiceint ,new contacto(nombre,numero,correo));
                lv.setAdapter(adapter);
            }
        }
    }
}