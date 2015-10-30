/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Musician;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jergus Fasanek
 */
public class MusicianDaoImplementation implements MusicianDao{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Musician findById(Long id) {
        if(id < 0){
            throw new IllegalArgumentException("MusicianDao - findById - invalid index param passed");
        }
        return em.find(Musician.class, id);
    }

    @Override
    public List<Musician> findAll() {
        return em.createQuery(
                "SELECT m FROM Musician m", Musician.class)
                .getResultList();
    }

    @Override
    public Musician findByRealName(String realName) {
        return em.createQuery(
                "SELECT m FROM Musician m WHERE m.realName= :realName", Musician.class)
                .setParameter("realName", realName)
                .getSingleResult();
    }

    @Override
    public Musician findByArtistName(String artistName) {
        return em.createQuery(
                "SELECT m FROM Musician m WHERE m.artistName= :artistName", Musician.class)
                .setParameter("artistName", artistName)
                .getSingleResult();

    }

    @Override
    public boolean create(Musician musician) {
        if(musician == null){
            throw new IllegalArgumentException("MusicianDao - create - musician must not be null");
        }
        if(em.contains(musician)) {
            return false;
        }
        em.persist(musician);
        return true;
    }

    @Override
    public boolean delete(Musician musician) {
        if(musician == null){
            throw new IllegalArgumentException("MusicianDao - delete - musician must not be null");
        }
        if(!em.contains(musician)) {
            return false;
        }
        em.remove(musician);
        return true;
    }

    @Override
    public Musician update(Musician musician) {
        if(musician == null){
            throw new IllegalArgumentException("MusicianDao - update - musician must not be null");
        }
        return em.merge(musician);
    }
    
}
