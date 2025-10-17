package run;

import config.JPAUtil;
import dao.AutorDao;
import dao.CategoriaDao;
import entities.Autor;
import entities.Categoria;
import entities.Libro;
import dao.LibroDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        LibroDao libroDao = new LibroDao(em);

        try {
            Autor autor = new Autor();
            autor.setNombre("Gabriel García Márquez");
            autor.setNacionalidad("Colombiana");
            autor.setFechaNac("1927-03-06");

            Autor autor2 = new Autor();
            autor2.setNombre("Jose Daniel Miranda");
            autor2.setNacionalidad("Nicaraguense");
            autor2.setFechaNac("2006-09-14");


            Categoria categoria1 = new Categoria();
            categoria1.setNombre("Realismo mágico");

            Categoria categoria2 = new Categoria();
            categoria2.setNombre("Literatura Latinoamericana");

            Libro libro = new Libro();
            libro.setTitulo("Cien años de soledad");
            libro.setAnioPublicacion(1967);
            libro.setAutor(autor);
            libro.setCategorias(Arrays.asList(categoria1, categoria2));

            Libro libro2 = new Libro();
            libro.setTitulo("Mil Dias Soleados");
            libro.setAnioPublicacion(2000);
            libro.setAutor(autor);
            libro.setCategorias(Arrays.asList(categoria2));

            Libro libro3 = new Libro();
            libro.setTitulo("Juniter");
            libro.setAnioPublicacion(2010);
            libro.setAutor(autor);
            libro.setCategorias(Arrays.asList(categoria1));

            AutorDao autorDao = new AutorDao(em);
            autorDao.guardar(autor);
            autorDao.guardar(autor2);

            CategoriaDao categoriaDao = new CategoriaDao(em);
            categoriaDao.guardar(categoria1);
            categoriaDao.guardar(categoria2);

            libroDao.guardar(libro);
            libroDao.guardar(libro2);
            libroDao.guardar(libro3);

            List<Libro> libros = libroDao.listar();
            System.out.println("\nLibros guardados en la base de datos:");
            for (Libro l : libros) {
                System.out.println(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

