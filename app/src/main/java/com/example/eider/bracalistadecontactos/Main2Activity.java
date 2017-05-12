package com.example.eider.bracalistadecontactos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity  implements Validator.ValidationListener{

    @NotEmpty(message = "debes de llenar este campo")
   private  EditText nombre;

    @Digits(message = "tiene que ser un numero telefonico de 10 digitos",integer = 10)
    private EditText numero;

    @Email(message = "esto no es un correo valido")
    private EditText correo;

    Button Agregar, Cancelar;
    String dato ="0";
    Validator validator;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Agregar = (Button)findViewById(R.id.agregar);
        Cancelar = (Button)findViewById(R.id.cancelar);
        nombre = (EditText)findViewById(R.id.nombre);
        numero = (EditText)findViewById(R.id.numero);
        correo = (EditText)findViewById(R.id.correo);


        intent = getIntent();
        Bundle extras = intent.getExtras();

        if(!extras.equals(null)){

            dato = extras.getString("DATO");
            String nombre = extras.getString("nombre");
            String tel = extras.getString("numero");
            String correo = extras.getString("correo");
          this.nombre.setText(nombre);
            this.numero.setText(tel);
            this.correo.setText(correo);

            //_____________ objetos de validators_____________
            validator= new Validator(this);
            validator.setValidationListener(this);
            //_____________ objetos de validators_____________
        }

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            validator.validate();



            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });






    }
    public void Loggin(View view){
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "todos los datos son correctos macho", Toast.LENGTH_SHORT).show();
        intent.putExtra("indice",dato);
        intent.putExtra("nombre",nombre.getText().toString());
        intent.putExtra("numero",numero.getText().toString());
        intent.putExtra("correo",correo.getText().toString());
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }


    }
}
