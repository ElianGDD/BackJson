/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Estado;
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
public class EstadoJPADAOImplementation implements EstadoJPADAO{
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetEstadoByIdPais(int IdPais) 
    {
        Result result = new Result();
        result.objects = new ArrayList<>();
        try {
            TypedQuery<Estado> estadoQuery = entityManager.createQuery("FROM Estado WHERE Pais.IdPais = :idpais",Estado.class);
            estadoQuery.setParameter("idpais", IdPais);
            List<Estado>estadoJPA = estadoQuery.getResultList();
            
            for(Estado estados: estadoJPA){
                Estado estado = new Estado();
                estado = estados;
                
                result.objects.add(estado);
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
