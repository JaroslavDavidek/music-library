package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Genre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JaroslavDavidek
 */
@Repository
public class GenreDaoImplementation implements GenreDao {

    @PersistenceContext
    private EntityManager em;  
    
    @Override
    public Genre findById(Long id) {
        if(id < 0){
            throw new IllegalArgumentException("GenreDao - findById - invalid index param passed");
        }
        return em.find(Genre.class, id);
    }

    @Override
    public Genre findByTitle(String genreTitle) {
        if(genreTitle == null){
            throw new IllegalArgumentException("GenreDao - findByTitle - genreTitle must not be null");
        }
        return em.createQuery(
                "SELECT g FROM Genre g WHERE g.title= :genreTitle", Genre.class)
                .setParameter("genreTitle", genreTitle)
                .getSingleResult();
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery(
                "SELECT g FROM Genre g", Genre.class)
                .getResultList();
    }

    @Override
    public boolean create(Genre genre) {
        if(genre == null){
            throw new IllegalArgumentException("GenreDao - create - genre must not be null");
        }
        if(em.contains(genre)) {
            return false;
        }
        em.persist(genre);
        return true;
    }

    @Override
    public Genre update(Genre genre) {
        if(genre == null){
            throw new IllegalArgumentException("GenreDao - update - genre must not be null");
        }
        return em.merge(genre);
    }

    @Override
    public boolean delete(Genre genre) {
        if(genre == null){
            throw new IllegalArgumentException("GenreDao - delete - genre must not be null");
        }
        if(!em.contains(genre)) {
            return false;
        }
        em.remove(genre);
        return true;
    }
    
}
