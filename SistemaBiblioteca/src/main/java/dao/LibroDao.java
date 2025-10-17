package dao;

import entities.Libro;
import interfac.ILibro;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LibroDao implements ILibro {
    private final EntityManager em;

    public LibroDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Libro guardar(Libro entities) {
        if(entities.getId()==0){
            em.getTransaction().begin();
            em.persist(entities);
            em.getTransaction().commit();
            return entities;
        }
        return em.merge(entities);
    }

    @Override
    public List<Libro> listar() {
        List<Libro> lista = em.createQuery("from Libro", Libro.class).getResultList();
        return lista;
    }
}
