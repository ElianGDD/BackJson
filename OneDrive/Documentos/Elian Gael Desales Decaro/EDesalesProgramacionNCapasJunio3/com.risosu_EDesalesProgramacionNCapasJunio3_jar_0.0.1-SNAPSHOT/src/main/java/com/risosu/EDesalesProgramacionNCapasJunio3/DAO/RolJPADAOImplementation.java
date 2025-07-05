package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Result;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Roll;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.record.chart.SeriesRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolJPADAOImplementation implements RollJPADAO{
    @Autowired
    EntityManager entityManager;

    @Override
    public Result GetAllRoll() 
    {
     Result result = new Result();
     result.objects = new ArrayList<>();
        try {
            TypedQuery<Roll> rollQuery = entityManager.createQuery("FROM Roll",Roll.class);
            List<Roll>rolesJPA = rollQuery.getResultList();
            for (Roll roles : rolesJPA) {
                Roll rol = new Roll();
                rol = roles;
                result.objects.add(rol);
                
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
