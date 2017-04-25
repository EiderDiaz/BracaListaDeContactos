package com.example.eider.bracalistadecontactos;

import android.app.Application;

import com.kinvey.android.Client;

/**
 * Created by Eider on 21/04/2017.
 */

public class mainApp extends Application {

    private Client cliente ;
    @Override
    public void onCreate(){
        super.onCreate();
        defineClient();

    }

    private void defineClient() {
         cliente = new Client.Builder("kid_r1jkR1q3x","9c81209423954eb88640eeb2a488cebb"
                , this.getApplicationContext()).build();
    }
    public Client getClient(){
        return cliente;
    }
}
