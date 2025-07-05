package com.risosu.EDesalesProgramacionNCapasJunio3.ML;

import jakarta.validation.Valid;
import java.util.List;

public class UsuarioDireccion {
    
    @Valid 
    public Usuario Usuario;
    public Direccion Direccion;
    public List<Direccion> Direcciones;
    

    public Usuario getUsuario() {
        return Usuario;
    }

    public Direccion getDireccion() {
        return Direccion;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public void setDireccion(Direccion Direccion) {
        this.Direccion = Direccion;
    }

    
    
}
