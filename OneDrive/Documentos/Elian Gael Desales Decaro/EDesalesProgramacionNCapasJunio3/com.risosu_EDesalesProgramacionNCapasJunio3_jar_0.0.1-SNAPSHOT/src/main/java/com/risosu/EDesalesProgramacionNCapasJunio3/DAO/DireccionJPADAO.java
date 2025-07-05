
package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Result;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.UsuarioDireccion;


public interface DireccionJPADAO {
    Result GetDireccionesByIdUsuario(int IdUsuario);
    Result POSTDireccionByIdUsuario( UsuarioDireccion usuarioDireccion);
    Result PUTDireccionByUsuario(UsuarioDireccion usuarioDireccion);
    Result DELETEDireccionByUsuario(UsuarioDireccion usuarioDireccion);
    
}

