package com.example.eider.bracalistadecontactos;

/**
 * Created by Eider on 08/03/2017.
 */

public class contacto {
    private String nombre;
    private  String  telefono;
    private  String correo;

    public contacto() {

    }

    public contacto(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

