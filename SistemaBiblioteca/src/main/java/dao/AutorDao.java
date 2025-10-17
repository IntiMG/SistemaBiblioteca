package dao;

import java.util.List;
import interfac.IAutor;
import jakarta.persistence.EntityManager;
import entities.Autor;

public class AutorDao implements IAutor {
    private final EntityManager em;

    public AutorDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Autor guardar(Autor entities) {
        if(entities.getId() == 0){
            em.getTransaction().begin();
            em.persist(entities);
            em.getTransaction().commit();
            return entities;
        }
        return em.merge(entities);
    }

    @Override
    public List<Autor> listar() {
        List<Autor> lista = em.createQuery("from Autor", Autor.class).getResultList();
        return lista;
    }
}
