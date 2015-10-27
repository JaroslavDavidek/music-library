/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Album;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Peter Franek
 */
public class AlbumDaoImplementation implements AlbumDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Album findById(Long id) {
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
        return em.createQuery(
                "SELECT a FROM Album a WHERE a.title= :customTitle", Album.class)
                .setParameter("customTitle", title)
                .getSingleResult();
    }

    @Override
    public void create(Album album) {
        em.persist(album);
    }

    @Override
    public void delete(Album album) {
        em.remove(album);
    }
    
}
