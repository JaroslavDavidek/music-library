/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Peter Franek
 */
public interface AlbumService {
    public Album createAlbum(Album album);
    public boolean deleteAlbum(Album album);
    public Album updateAlbum(Album album);
    public Album updateAlbumTitle(Album album, String title);
    public Album updateAlbumReleaseDate(Album album, Date date);
    public Album findById(Long id);
    public Album findByTitle(String title);
    public List<Album> findAll();
    public Album addSong(Album album, Song song);
    public Album removeSong(Album album, Song song);
    public List<Album> findAllAlbumsFromYears(int from, int to);
    public List<Album> findAllAlbumsOfMusician(Musician musician);
}
