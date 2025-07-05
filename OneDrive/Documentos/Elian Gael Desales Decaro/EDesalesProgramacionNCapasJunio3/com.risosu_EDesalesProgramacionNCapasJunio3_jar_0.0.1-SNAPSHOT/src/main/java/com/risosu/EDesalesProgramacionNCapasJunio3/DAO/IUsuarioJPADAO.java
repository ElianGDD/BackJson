/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Result;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Usuario;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.UsuarioDireccion;
import java.util.List;

/**
 *
 * @author Alien 13
 */
public interface IUsuarioJPADAO {

    Result GetAll();
    Result GetUsuarioDirecciones(int IdUsuario);
    Result GetUsuario(int IdUsuario);
    Result PostAll(UsuarioDireccion usuarioDireccion);
    Result PostCargaMasiva(List<UsuarioDireccion>usuariosDireccions);
    Result PutAll(UsuarioDireccion usuarioDireccion);
    Result DELETEALL(UsuarioDireccion usuarioDireccion);
    
    
}
