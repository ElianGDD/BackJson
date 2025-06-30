/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Result;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.UsuarioDireccion;

/**
 *
 * @author Alien 13
 */
public interface IUsuarioJPADAO {

    Result Add(UsuarioDireccion usuarioDireccion);

    Result UpdateDatosUsuario(UsuarioDireccion usuarioDireccion);

    Result GetAll();

    Result AddNuevaDireccionByIdUsuarioJPA(UsuarioDireccion usuarioDireccion);

    Result UpdateAlumnoDatosDireccionJPA(UsuarioDireccion usuarioDireccion);
    
    Result DeleteAlumno(int idAlumno);
    
    Result DeleteDireccionByIdDireccion (int idDireccion);
    
    Result GetDatosAlumnoPDByIdAlumnoJPA(int IdUsuario);
    
    Result GetDatosBasicosUsuarioByIdUsuarioJPA(int IdUsuario);
}
