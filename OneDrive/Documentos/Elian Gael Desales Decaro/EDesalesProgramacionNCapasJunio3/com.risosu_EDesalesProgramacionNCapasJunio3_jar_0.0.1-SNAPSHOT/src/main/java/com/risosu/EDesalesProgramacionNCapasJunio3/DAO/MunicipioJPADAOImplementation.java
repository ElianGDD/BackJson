/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Municipio;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alien 13
 */
@Repository
public class MunicipioJPADAOImplementation implements MunicipioJPADAO{
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetMunicipioByEstado(int IdEstado) {
        Result result = new Result();
        result.objects = new ArrayList<>();
        try {
            TypedQuery<Municipio> municipioQuery = entityManager.createQuery("From Municipio WHERE Estado.IdEstado = :idestado", Municipio.class);
            municipioQuery.setParameter("idestado", IdEstado);
            List<Municipio> municipioJPA = municipioQuery.getResultList();
            
            for(Municipio municipios : municipioJPA){
                Municipio municipio = new Municipio();
                municipio = municipios;
                
                result.objects.add(municipio);
            }
            
            result.correct = true;
        } catch (Exception ex) {
            result.objects = null;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }
        return result;
    }
    
    
}
