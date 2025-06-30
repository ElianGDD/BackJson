package com.risosu.EDesalesProgramacionNCapasJunio3.RequestController;

public class CuerpoExperimento {
    
    private int numero1;
    private int numero2;
    private int [] arregloNumeros = new int[5];

    public int[] getArregloNumeros() {
        return arregloNumeros;
    }

    public void setArregloNumeros(int[] arregloNumeros) {
        this.arregloNumeros = arregloNumeros;
    }
    
    public int getNumero1() {
        return numero1;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }
    
    
}
