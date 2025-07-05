/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Result;


public interface ColoniaJPADAO {
    Result GetColoniaByIdMunicipio(int IdMunicipio);
    Result GetColoniaByCP(String CP);
    
}
