package com.example.eider.bracalistadecontactos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class llamar extends AppCompatActivity {

    TextView numero,correo,nombre;
    ImageView llamar,sms;
    EditText etsms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamar);
         nombre = (TextView)findViewById(R.id.nombre);
         numero = (TextView)findViewById(R.id.numero);
         correo = (TextView)findViewById(R.id.correo);
        llamar = (ImageView)findViewById(R.id.llamar);
        sms = (ImageView)findViewById(R.id.sms);
        etsms = (EditText)findViewById(R.id.editTextsms);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(!extras.equals(null)){
            nombre.setText(extras.getString("nombre"));
            correo.setText(extras.getString("correo"));
            numero.setText(extras.getString("telefono"));

        }


        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numero.getText().toString(), null)));

            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!etsms.getText().equals("")){
                try {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(numero.getText().toString(), null, etsms.getText().toString(), null, null);
                    Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos."+e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
            }
        });

    }
}
