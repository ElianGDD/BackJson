/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Pais;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaisJPADAOImplementatio implements PaisJPADAO{
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAllPais() {
        Result result = new Result ();
        result.objects = new ArrayList<>();
        try {
            TypedQuery<Pais> paisQuery = entityManager.createQuery("FROM Pais",Pais.class);
            List<Pais> paisJPA = paisQuery.getResultList();
            for(Pais paises : paisJPA)
            {
                Pais pais = new Pais();
                pais = paises;
                result.objects.add(pais);
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
