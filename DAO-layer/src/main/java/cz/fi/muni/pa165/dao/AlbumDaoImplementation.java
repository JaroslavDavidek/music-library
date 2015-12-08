/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.exception.InvalidParamDataAccessExpection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Franek
 */
@Repository
@Transactional
public class AlbumDaoImplementation implements AlbumDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Album findById(Long id) {
        if (id < 0) {
            throw new InvalidParamDataAccessExpection("AlbumDao - find by id - wrong id parameter");
        }
        return em.find(Album.class, id);
    }

    @Override
    public List<Album> findAll() {
        return em.createQuery(
                "SELECT a FROM Album a", Album.class)
                .getResultList();
    }

    @Override
    public Album findByTitle(String title) {
        if (title == null) {
            throw new InvalidParamDataAccessExpection("AlbumDao - find by title - title must not be null");
        }
        return em.createQuery(
                "SELECT a FROM Album a WHERE a.title= :customTitle", Album.class)
                .setParameter("customTitle", title)
                .getSingleResult();
    }

    @Override
    public boolean create(Album album) {
        if (album == null) {
            throw new InvalidParamDataAccessExpection("AlbumDao - create album - album must not be null");
        }
        if (em.contains(album)) return false;
        em.persist(album);
        return true;
    }

    @Override
    public boolean delete(Album album) {
        if (album == null) {
            throw new InvalidParamDataAccessExpection("AlbumDao - delete album - album must not be null");
        }
        if (!em.contains(album)) return false;
        em.remove(album);
        return true;
    }
    
    @Override
    public Album update(Album album) {
        if (album == null) {
            throw new InvalidParamDataAccessExpection("AlbumDao - update album - album must not be null");
        }
        return em.merge(album);
    }

    @Override
    public Album addSong(Album album, Song song) {
        if (album == null || song == null) {
            throw new InvalidParamDataAccessExpection("AlbumDao - add song - album nor song must not be null");
        }
        album.addSong(song);
        return em.merge(album);
    }

    @Override
    public Album removeSong(Album album, Song song) {
        if (album == null || song == null) {
            throw new InvalidParamDataAccessExpection("AlbumDao - add song - album nor song must not be null");
        }
        album.removeSong(song);
        return em.merge(album);
    }
    
}
