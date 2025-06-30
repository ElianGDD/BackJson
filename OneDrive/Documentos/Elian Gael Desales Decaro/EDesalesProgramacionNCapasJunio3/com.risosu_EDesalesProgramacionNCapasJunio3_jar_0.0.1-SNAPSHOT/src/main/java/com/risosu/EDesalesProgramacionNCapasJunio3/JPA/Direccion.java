package com.risosu.EDesalesProgramacionNCapasJunio3.JPA;

import com.risosu.EDesalesProgramacionNCapasJunio3.ML.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddireccion")
    private int IdDireccion;
    @Column(name = "calle")
    private String Calle;
    @Column(name = "numerointerior")
    private String NumeroInterior;
    @Column(name = "numeroexterior")
    private String NumeroExterior;
    @JoinColumn(name = "idcolonia")
    @ManyToOne
    public Colonia Colonia;
    @JoinColumn(name = "idusuario")
    @ManyToOne
    public Usuario Usuario;
    //public Usuario Usuario;

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

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
