package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Album;
//import cz.fi.muni.pa165.entity.Genre;
//import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JaroslavDavidek
 */
public class SongDaoImplementation implements SongDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Song findById(Long id) {
        return em.find(Song.class, id);
    }

    @Override
    public Song findByTitle(String songTitle) {
        return em.createQuery(
                "SELECT s FROM Song s WHERE s.title= :songTitle", Song.class)
                .setParameter("songTitle", songTitle)
                .getSingleResult();
    }

    @Override
    public List<Song> findAll() {
        return em.createQuery(
                "SELECT s FROM Song s", Song.class)
                .getResultList();
    }
/*
    @Override
    public List<Song> findAllByMusician(Musician songAuthor) {
        return em.createQuery(
                "SELECT s FROM Song s WHERE s.musician= :songAuthor", Song.class)
                .setParameter("songAuthor", songAuthor)
                .getResultList();
    }

    @Override
    public List<Song> findAllByGenre(Genre songGenre) {
        return em.createQuery(
                "SELECT s FROM Song s WHERE s.genre= :songGenre", Song.class)
                .setParameter("songGenre", songGenre)
                .getResultList();
    }
    */
    
    @Override
    public List<Song> findAllByAlbum(Album songAlbum) {
        return em.createQuery(
                "SELECT s FROM Song s WHERE s.album= :songAlbum", Song.class)
                .setParameter("songAlbum", songAlbum)
                .getResultList();
    }
    
    @Override
    public boolean create(Song song) {
        if(em.contains(song)) {
            return false;
        }
        em.persist(song);
        return true;
    }

    @Override
    public boolean delete(Song song) {
        if(!em.contains(song)) {
            return false;
        }
        em.remove(song);
        return true;
    }
    
}
