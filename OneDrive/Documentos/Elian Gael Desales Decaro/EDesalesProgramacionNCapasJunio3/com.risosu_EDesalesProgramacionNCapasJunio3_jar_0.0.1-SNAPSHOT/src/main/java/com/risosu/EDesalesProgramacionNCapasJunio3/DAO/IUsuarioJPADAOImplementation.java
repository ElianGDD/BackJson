package com.risosu.EDesalesProgramacionNCapasJunio3.DAO;

import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Colonia;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Direccion;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Roll;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Usuario;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Result;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.UsuarioDireccion;
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

    @Override
    public Result GetAll() {
        Result result = new Result();
        result.objects = new ArrayList<>();
        try {
            TypedQuery<Usuario> usuarioQuery = entityManager.createQuery("FROM Usuario", Usuario.class);
            List<Usuario> usariosJPA = usuarioQuery.getResultList();

            for (Usuario usuario : usariosJPA) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = usuario;

                TypedQuery<Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.idUsuario = :idusuario", Direccion.class);
                queryDireccion.setParameter("idusuario", usuario.getIdUsuario());
                List<Direccion> direccionesUsuarios = queryDireccion.getResultList();

                if (direccionesUsuarios.size() != 0) {

                    usuarioDireccion.Direcciones = direccionesUsuarios;
                }
                result.objects.add(usuarioDireccion);
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
    public Result GetUsuarioDirecciones(int IdUsuario) {
        Result result = new Result();
        try {
            TypedQuery<Usuario> usarioQuery = entityManager.createQuery("FROM Usuario WHERE idUsuario =: idusuario", Usuario.class);
            usarioQuery.setParameter("idusuario", IdUsuario);
            Usuario usuarioJPA = usarioQuery.getSingleResult();

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = usuarioJPA;

            TypedQuery<Direccion> queryDireccion = entityManager.createQuery("FROM Direccion WHERE Usuario.idUsuario = :idusuario", Direccion.class);
            queryDireccion.setParameter("idusuario", usuarioJPA.getIdUsuario());
            List<Direccion> direccionesUsuarios = queryDireccion.getResultList();

            if (direccionesUsuarios.size() != 0) {
                usuarioDireccion.Direcciones = direccionesUsuarios;

            }
            result.object = usuarioDireccion;

            result.correct = true;
        } catch (Exception ex) {
            result.object = null;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }
        return result;
    }

    @Override
    public Result GetUsuario(int IdUsuario) {
        Result result = new Result();
        try {
            TypedQuery<Usuario> usarioQuery = entityManager.createQuery("FROM Usuario WHERE idUsuario =: idusuario", Usuario.class);
            usarioQuery.setParameter("idusuario", IdUsuario);
            Usuario usarioJPA = usarioQuery.getSingleResult();

            UsuarioDireccion usarioDireccion = new UsuarioDireccion();
            usarioDireccion.Usuario = usarioJPA;
            result.object = usarioDireccion;

            result.correct = true;
        } catch (Exception ex) {
            result.object = null;
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @Override
    public Result PostAll(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {

            entityManager.persist(usuarioDireccion.Usuario);
            Direccion direccion = usuarioDireccion.getDireccion();
            direccion.setUsuario(usuarioDireccion.getUsuario());
            entityManager.persist(direccion);

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
    public Result PostCargaMasiva(List<UsuarioDireccion> usuariosDireccions) {
        Result result = new Result();
        try {

            for (UsuarioDireccion usuarioDireccion : usuariosDireccions) {
                this.PostAll(usuarioDireccion);

            }

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
    public Result PutAll(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {

            entityManager.merge(usuarioDireccion.Usuario);
            usuarioDireccion.Direccion.Usuario = new Usuario();
            usuarioDireccion.Direccion.Usuario.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());
            entityManager.merge(usuarioDireccion.Direccion);

            result.correct = true;
        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }

        return result;

    }

    @Override
    public Result DELETEALL(UsuarioDireccion usuarioDireccion) {
        Result result = new Result();
        try {
            entityManager.persist(usuarioDireccion.Usuario);
            usuarioDireccion.Direccion.Usuario.setIdUsuario(usuarioDireccion.Usuario.getIdUsuario());
            entityManager.persist(usuarioDireccion.Direccion);

            result.correct = true;
        } catch (Exception ex) {
            result.errorMessage = ex.getLocalizedMessage();
            result.correct = false;
            result.ex = ex;
        }
        return result;
    }

}
