/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Colonia;
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
public class ColoniaJPADAOImplementation implements ColoniaJPADAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetColoniaByIdMunicipio(int IdMunicipio) {
        Result result = new Result();
        result.objects = new ArrayList<>();
        try {
            TypedQuery<Colonia> coloniaQuery = entityManager.createQuery("FROM Colonia WHERE Municipio.IdMunicipio = : idmunicipio", Colonia.class);
            coloniaQuery.setParameter("idmunicipio", IdMunicipio);
            List<Colonia> coloniaJPA = coloniaQuery.getResultList();

            for (Colonia colonias : coloniaJPA) {

                Colonia colonia = new Colonia();
                colonia = colonias;
                result.objects.add(colonia);
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

    @Override
    public Result GetColoniaByCP(String CP) {
        Result result = new Result();
        result.objects = new ArrayList<>();
        try {
            TypedQuery<Colonia> coloniaQuery = entityManager.createQuery("FROM Colonia WHERE CodigoPostal = :codigopostal", Colonia.class);
            coloniaQuery.setParameter("codigopostal", CP);
            List<Colonia> coloniaJPA = coloniaQuery.getResultList();

            for (Colonia colonias : coloniaJPA) {
                Colonia colonia = new Colonia();
                colonia = colonias;

                
                result.objects.add(colonia);
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
