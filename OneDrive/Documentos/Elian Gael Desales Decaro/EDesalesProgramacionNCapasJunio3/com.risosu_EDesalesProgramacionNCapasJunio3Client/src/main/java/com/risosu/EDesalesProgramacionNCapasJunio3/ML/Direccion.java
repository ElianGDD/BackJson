package com.risosu.EDesalesProgramacionNCapasJunio3.ML;

public class Direccion {

    private int IdDireccion;
    private String Calle;
    private String NumeroInterior;
    private String NumeroExterior;

    public Colonia Colonia;
    //public Usuario Usuario;

    //Getters
    public Colonia getColonia() {
        return Colonia;
    }

    public int getIdDireccion() {
        return IdDireccion;
    }

    public String getCalle() {
        return Calle;
    }

    public String getNumeroInterior() {
        return NumeroInterior;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    //Setters
    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public void setNumeroInterior(String NumeroInterior) {
        this.NumeroInterior = NumeroInterior;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }

}
