package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
//import cz.fi.muni.pa165.entity.Genre;
//import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.exception.InvalidParamDataAccessExpection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JaroslavDavidek
 */
@Repository
@Transactional
public class SongDaoImplementation implements SongDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Song findById(Long id) {
        if(id < 0){
            throw new InvalidParamDataAccessExpection("SongDao - findById - invalid index param passed") {};
        }
        return em.find(Song.class, id);
    }

    @Override
    public Song findByTitle(String songTitle) {
        if(songTitle == null){
            throw new InvalidParamDataAccessExpection("SongDao - findByTitle - null songTitle param passed") {};
        }
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

    @Override
    public List<Song> findAllByMusician(Musician songAuthor) {
        if(songAuthor == null){
            throw new InvalidParamDataAccessExpection("SongDao - findAllByMusician - songAuthor must not be null") {};
        }
        return em.createQuery(
                "SELECT s FROM Song s WHERE s.musician= :songAuthor", Song.class)
                .setParameter("songAuthor", songAuthor)
                .getResultList();
    }

    @Override
    public List<Song> findAllByGenre(Genre songGenre) {
        if(songGenre == null){
            throw new InvalidParamDataAccessExpection("SongDao - findAllByGenre - songGenre must not be null") {};
        }
        return em.createQuery(
                "SELECT s FROM Song s WHERE s.genre= :songGenre", Song.class)
                .setParameter("songGenre", songGenre)
                .getResultList();
    }
    
    @Override
    public List<Song> findAllByAlbum(Album songAlbum) {
        if(songAlbum == null){
            throw new InvalidParamDataAccessExpection("SongDao - findAllByAlbum - songAlbum must not be null");
        }
        return em.createQuery(
                "SELECT s FROM Song s WHERE s.album= :songAlbum", Song.class)
                .setParameter("songAlbum", songAlbum)
                .getResultList();
    }
    
    @Override
    public boolean create(Song song) {
        if(song == null){
            throw new InvalidParamDataAccessExpection("SongDao - create - song must not be null");
        }
        if(em.contains(song)) {
            return false;
        }
        em.persist(song);
        return true;
    }
    
    @Override
    public Song update(Song song) {
        if(song == null){
            throw new InvalidParamDataAccessExpection("SongDao - update - song must not be null");
        }
        return em.merge(song);
    }

    @Override
    public boolean delete(Song song) {
        if(song == null){
            throw new InvalidParamDataAccessExpection("SongDao - delete - song must not be null");
        }
        if(!em.contains(song)) {
            return false;
        }
        em.remove(song);
        return true;
    }
    
}
