package com.proyecto.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class AbstractRepository<T, ID> implements CrudRepository<T, ID> {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VuelosPU");

    protected final Class<T> claseEntidad;

    protected AbstractRepository(Class<T> claseEntidad) {
        this.claseEntidad = claseEntidad;
    }

    protected EntityManager getEntityManager() {return emf.createEntityManager();}

    @Override
    public void guardar(T entidad) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
        finally {
            em.close();
        }
    }

    @Override
    public T buscarPorId(ID id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(claseEntidad, id);
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<T> buscarTodos() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("FROM " + claseEntidad.getSimpleName(), claseEntidad).getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public void actualizar(T entidad) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        }
        catch (Exception e) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
        finally {
            em.close();
        }
    }

    @Override
    public void eliminar(ID id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            T entidad = em.find(claseEntidad, id);
            if (entidad != null) {
                em.remove(entidad);
            }
            em.getTransaction().commit();
        }
        catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        }
        finally {
            em.close();
        }
    }
}
