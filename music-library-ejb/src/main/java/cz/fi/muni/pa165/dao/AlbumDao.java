/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Album;
import java.util.List;

/**
 *
 * @author Peter Franek
 */
public interface AlbumDao {
    public Album findById(Long id);
    public List<Album> findAll();
    public Album findByTitle(String title);
    public void create(Album album);
    public void delete(Album album);
}
