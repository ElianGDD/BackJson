package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Colonia;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Direccion;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Roll;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Usuario;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Result;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.UsuarioDireccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IUsuarioJPADAOImplementation implements IUsuarioJPADAO {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Result Add(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {

            Usuario usuarioJPA = new Usuario();
            usuarioJPA.setNombre(usuarioDireccion.Usuario.getNombre());
            usuarioJPA.setFechaNacimiento(usuarioDireccion.Usuario.getFechaNacimiento());
            usuarioJPA.setUserName(usuarioDireccion.Usuario.getUserName());
            usuarioJPA.setApellidoPaterno(usuarioDireccion.Usuario.getApellidoPaterno());
            usuarioJPA.setApellidoMaterno(usuarioDireccion.Usuario.getApellidoMaterno());
            usuarioJPA.setPassword(usuarioDireccion.Usuario.getPassword());
            usuarioJPA.setSexo(usuarioDireccion.Usuario.getSexo());
            usuarioJPA.setTelefono(usuarioDireccion.Usuario.getTelefono());
            usuarioJPA.setCelular(usuarioDireccion.Usuario.getCelular());
            usuarioJPA.setCurp(usuarioDireccion.Usuario.getCurp());
            usuarioJPA.setEmail(usuarioDireccion.Usuario.getEmail());
            usuarioJPA.Roll = new Roll();
            usuarioJPA.Roll.setIdRoll(usuarioDireccion.Usuario.Roll.getIdRoll());
            entityManager.persist(usuarioJPA);
            /*persistir direccion*/
            //direccionJPA = new DIreccionJPA
            //direccionjpa.alumno = alumnoJPA

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;

    }

    @Transactional
    @Override
    public Result UpdateDatosUsuario(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            Usuario usuarioJPA = new Usuario();

            usuarioJPA.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());

            usuarioJPA.setNombre(usuarioDireccion.Usuario.getNombre());
            usuarioJPA.setFechaNacimiento(usuarioDireccion.Usuario.getFechaNacimiento());
            usuarioJPA.setUserName(usuarioDireccion.Usuario.getUserName());
            usuarioJPA.setApellidoPaterno(usuarioDireccion.Usuario.getApellidoPaterno());
            usuarioJPA.setApellidoMaterno(usuarioDireccion.Usuario.getApellidoMaterno());
            usuarioJPA.setPassword(usuarioDireccion.Usuario.getPassword());
            usuarioJPA.setSexo(usuarioDireccion.Usuario.getSexo());
            usuarioJPA.setTelefono(usuarioDireccion.Usuario.getTelefono());
            usuarioJPA.setCelular(usuarioDireccion.Usuario.getCelular());
            usuarioJPA.setCurp(usuarioDireccion.Usuario.getCurp());
            usuarioJPA.setEmail(usuarioDireccion.Usuario.getEmail());
            usuarioJPA.setImagenPerfil(usuarioDireccion.Usuario.getImagenPerfil());
            Roll roll = new Roll();
            roll.setIdRoll(usuarioDireccion.Usuario.getRoll().getIdRoll());
            // Roll managedRoll = entityManager.merge(roll);
            // usuarioJPA.setRoll(managedRoll);

            entityManager.merge(usuarioJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.errorMessage = ex.getMessage();
            result.correct = false;
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetAll() {
        Result result = new Result();
        result.objects = new ArrayList<>();

        try {
            TypedQuery<Usuario> alumnosQuery = entityManager.createQuery("FROM Usuario ORDER BY idUsuario ASC", Usuario.class);

            List<Usuario> usuarios = alumnosQuery.getResultList();

            for (Usuario usuarioJPA : usuarios) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = new com.risosu.EDesalesProgramacionNCapasJunio3.ML.Usuario();
                usuarioDireccion.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
                usuarioDireccion.Usuario.setNombre(usuarioJPA.getNombre());
                usuarioDireccion.Usuario.setFechaNacimiento(usuarioJPA.getFechaNacimiento());
                usuarioDireccion.Usuario.setUserName(usuarioJPA.getUserName());
                usuarioDireccion.Usuario.setApellidoPaterno(usuarioJPA.getApellidoPaterno());
                usuarioDireccion.Usuario.setApellidoMaterno(usuarioJPA.getApellidoMaterno());
                usuarioDireccion.Usuario.setPassword(usuarioJPA.getPassword());
                usuarioDireccion.Usuario.setSexo(usuarioJPA.getSexo());
                usuarioDireccion.Usuario.setTelefono(usuarioJPA.getTelefono());
                usuarioDireccion.Usuario.setCelular(usuarioJPA.getCelular());
                usuarioDireccion.Usuario.setCurp(usuarioJPA.getCurp());
                usuarioDireccion.Usuario.setEmail(usuarioJPA.getEmail());
                TypedQuery<Direccion> direccionesQuery = entityManager.createQuery("FROM Direccion WHERE Usuario.idUsuario = :idusuario", Direccion.class);
                direccionesQuery.setParameter("idusuario", usuarioJPA.getIdUsuario());
                List<Direccion> direccionesJPA = direccionesQuery.getResultList();
                if (direccionesJPA.size() != 0) {
                    usuarioDireccion.Direcciones = new ArrayList<>();
                    for (Direccion direccionJPA : direccionesJPA) {
                        com.risosu.EDesalesProgramacionNCapasJunio3.ML.Direccion direccion = new com.risosu.EDesalesProgramacionNCapasJunio3.ML.Direccion();
                        direccion.setCalle(direccionJPA.getCalle());
                        direccion.setNumeroExterior(direccionJPA.getNumeroExterior());
                        direccion.setNumeroInterior(direccionJPA.getNumeroInterior());
                        usuarioDireccion.Direcciones.add(direccion);
                    }
                }
                result.objects.add(usuarioDireccion);
            }
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;

    }

    @Transactional
    @Override
    public Result AddNuevaDireccionByIdUsuarioJPA(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            Direccion direccionJPA = new Direccion();
            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
            direccionJPA.Usuario = new Usuario();
            direccionJPA.Usuario.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());

            entityManager.persist(direccionJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;

        }

        return result;
    }

    @Transactional
    @Override
    public Result UpdateAlumnoDatosDireccionJPA(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();

        try {
            Direccion direccionJPA = new Direccion();
            direccionJPA.setIdDireccion(usuarioDireccion.Direccion.getIdDireccion());
            direccionJPA.setCalle(usuarioDireccion.Direccion.getCalle());
            direccionJPA.setNumeroInterior(usuarioDireccion.Direccion.getNumeroInterior());
            direccionJPA.setNumeroExterior(usuarioDireccion.Direccion.getNumeroExterior());
            direccionJPA.Colonia = new Colonia();
            direccionJPA.Colonia.setIdColonia(usuarioDireccion.Direccion.Colonia.getIdColonia());

            entityManager.merge(direccionJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;

        }

        return result;
    }

    @Transactional
    @Override
    public Result DeleteAlumno(int idUsuario) {
        Result result = new Result();
        try {

            TypedQuery<Direccion> direccionesQuery = entityManager.createQuery("FROM Direccion WHERE Usuario.idUsuario = :idusuario", Direccion.class);
            direccionesQuery.setParameter("idusuario", idUsuario);
            List<Direccion> direccionesJPA = direccionesQuery.getResultList();

            for (Direccion direccionJPA : direccionesJPA) {
                entityManager.remove(direccionJPA);
            }

            Usuario usuarioJPA = entityManager.find(Usuario.class, idUsuario);

            entityManager.remove(usuarioJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }

        return result;
    }

    @Transactional
    @Override
    public Result DeleteDireccionByIdDireccion(int idDireccion) {
        Result result = new Result();

        try {
            Direccion direccionJPA = entityManager.find(Direccion.class, idDireccion);
            entityManager.remove(direccionJPA);

            result.correct = true;

        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;

        }

        return result;

    }

    @Override
    public Result GetDatosAlumnoPDByIdAlumnoJPA(int IdUsuario) {
        Result result = new Result();

        try {
            TypedQuery<Usuario> usuarioQuery = entityManager.createQuery("FROM Usuario WHERE idUsuario = :idusuario", Usuario.class);
            usuarioQuery.setParameter("idusuario", IdUsuario);
            Usuario usuarioJPA = usuarioQuery.getSingleResult();

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new com.risosu.EDesalesProgramacionNCapasJunio3.ML.Usuario();
            usuarioDireccion.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
            usuarioDireccion.Usuario.setNombre(usuarioJPA.getNombre());
            usuarioDireccion.Usuario.setFechaNacimiento(usuarioJPA.getFechaNacimiento());
            usuarioDireccion.Usuario.setUserName(usuarioJPA.getUserName());
            usuarioDireccion.Usuario.setApellidoPaterno(usuarioJPA.getApellidoPaterno());
            usuarioDireccion.Usuario.setApellidoMaterno(usuarioJPA.getApellidoMaterno());
            usuarioDireccion.Usuario.setPassword(usuarioJPA.getPassword());
            usuarioDireccion.Usuario.setSexo(usuarioJPA.getSexo());
            usuarioDireccion.Usuario.setTelefono(usuarioJPA.getTelefono());
            usuarioDireccion.Usuario.setCelular(usuarioJPA.getCelular());
            usuarioDireccion.Usuario.setCurp(usuarioJPA.getCurp());
            usuarioDireccion.Usuario.setEmail(usuarioJPA.getEmail());
            TypedQuery<Direccion> direccionesQuery = entityManager.createQuery("FROM Direccion WHERE Usuario.idUsuario = :idusuario", Direccion.class);
            direccionesQuery.setParameter("idusuario", usuarioJPA.getIdUsuario());
            List<Direccion> direccionesJPA = direccionesQuery.getResultList();
            if (direccionesJPA.size() != 0) {
                usuarioDireccion.Direcciones = new ArrayList<>();
                for (Direccion direccionJPA : direccionesJPA) {
                    com.risosu.EDesalesProgramacionNCapasJunio3.ML.Direccion direccion = new com.risosu.EDesalesProgramacionNCapasJunio3.ML.Direccion();
                    direccion.setIdDireccion(direccionJPA.getIdDireccion());
                    direccion.setCalle(direccionJPA.getCalle());
                    direccion.setNumeroExterior(direccionJPA.getNumeroExterior());
                    direccion.setNumeroInterior(direccionJPA.getNumeroInterior());
                    usuarioDireccion.Direcciones.add(direccion);
                }
            }
            result.object = usuarioDireccion;

            result.correct = true;

        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;

        }

        return result;
    }

    @Override
    public Result GetDatosBasicosUsuarioByIdUsuarioJPA(int IdUsuario) {
        Result result = new Result();

        try {
            TypedQuery<Usuario> usuarioQuery = entityManager.createQuery("FROM Usuario WHERE idUsuario = :idusuario", Usuario.class);
            usuarioQuery.setParameter("idusuario", IdUsuario);
            Usuario usuarioJPA = usuarioQuery.getSingleResult();

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            
            usuarioDireccion.Usuario = new com.risosu.EDesalesProgramacionNCapasJunio3.ML.Usuario();
            usuarioDireccion.Usuario.setIdUsuario(usuarioJPA.getIdUsuario());
            usuarioDireccion.Usuario.setNombre(usuarioJPA.getNombre());
            usuarioDireccion.Usuario.setFechaNacimiento(usuarioJPA.getFechaNacimiento());
            usuarioDireccion.Usuario.setUserName(usuarioJPA.getUserName());
            usuarioDireccion.Usuario.setApellidoPaterno(usuarioJPA.getApellidoPaterno());
            usuarioDireccion.Usuario.setApellidoMaterno(usuarioJPA.getApellidoMaterno());
            usuarioDireccion.Usuario.setPassword(usuarioJPA.getPassword());
            usuarioDireccion.Usuario.setSexo(usuarioJPA.getSexo());
            usuarioDireccion.Usuario.setTelefono(usuarioJPA.getTelefono());
            usuarioDireccion.Usuario.setCelular(usuarioJPA.getCelular());
            usuarioDireccion.Usuario.setCurp(usuarioJPA.getCurp());
            usuarioDireccion.Usuario.setEmail(usuarioJPA.getEmail());

            result.object = usuarioDireccion;

            result.correct = true;

        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;

        }

        return result;

    }

}
