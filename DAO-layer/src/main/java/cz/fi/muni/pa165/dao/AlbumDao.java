/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Song;
import java.util.List;

/**
 *
 * @author Peter Franek
 */
public interface AlbumDao {
    /**
     * Find an Album by its ID in database
     * 
     * @param id ID of the searched Album
     * @return 
     */
    public Album findById(Long id);
    
    /**
     * Find and return all albums currently stored in the DB
     * 
     * @return all albums in DB
     */
    public List<Album> findAll();
    
    /**
     * Find an Album by its title
     * 
     * @param title of the searched album   
     * @return album with corresponding title
     */
    public Album findByTitle(String title);
    
    /**
     * Create a new album in DB
     * 
     * @param album that is being added/created in db 
     * @return if creation was success  
     */
    public boolean create(Album album);
    
    /**
     * Delete corresponding album from db
     * 
     * @param album that is being deleted from db
     * @return if deletion was success
     */
    public boolean delete(Album album);
    
    /**
     * Update given album
     * 
     * @param album that is being updated in db
     * @return updated album 
     */
    public Album update(Album album);
    /**
     * 
     * @param album to add song to
     * @param song to add
     * @return album
     */
    public Album addSong(Album album, Song song);
    
    /**
     * 
     * @param album to remove song from
     * @param song to remove
     * @return album
     */
    public Album removeSong(Album album, Song song);
}
