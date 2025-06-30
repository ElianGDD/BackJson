
package com.risosu.EDesalesProgramacionNCapasJunio3.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Roll {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idroll")
    private int IdRoll;
    @Column(name = "nombre")
    private String Nombre;

    public int getIdRoll() {
        return IdRoll;
    }

    public void setIdRoll(int IdRoll) {
        this.IdRoll = IdRoll;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
 
    
    
}
