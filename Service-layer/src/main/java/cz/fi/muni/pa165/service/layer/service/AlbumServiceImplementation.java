/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.AlbumDao;
import cz.fi.muni.pa165.dao.SongDao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Peter Franek
 */
@Service
public class AlbumServiceImplementation implements AlbumService {

    @Inject
    private AlbumDao albumDao;
    
    @Inject
    private SongDao songDao;
    
    @Override
    public Album createAlbum(Album album) {
        if (albumDao.create(album)) {
            return album;
        }
        return null;
    }

    @Override
    public boolean deleteAlbum(Album album) {
        if (album != null) {
            return albumDao.delete(album);
        }
        return false;
    }

    @Override
    public Album updateAlbum(Album album) {
        if (album != null) {
            return albumDao.update(album);
        }
        return null;
    }

    @Override
    public Album updateAlbumTitle(Album album, String title) {
        if (album != null && title != null && !title.isEmpty()) {
            album.setTitle(title);
            return albumDao.update(album);
        }
        return null;
    }

    @Override
    public Album updateAlbumReleaseDate(Album album, Date date) {
        if (album != null && date != null) {
            album.setReleaseDate(date);
            return albumDao.update(album);
        }
        return null;
    }

    @Override
    public Album findById(Long id) {
        return albumDao.findById(id);
    }

    @Override
    public Album findByTitle(String title) {
        return albumDao.findByTitle(title);
    }

    @Override
    public List<Album> findAll() {
        return albumDao.findAll();
    }

    @Override
    public Album addSong(Album album, Song song) {
        return albumDao.addSong(album, song);
    }

    @Override
    public Album removeSong(Album album, Song song) {
        return albumDao.removeSong(album, song);
    }

    @Override
    public List<Album> findAllAlbumsFromYears(int from, int to) {
        List<Album> returnList = new ArrayList<>();
        for(Album a: findAll())
        {
            if(checkReleaseYearRange(a, from, to))
            {
                returnList.add(a);
            }
        }
        return returnList;
    }

    @Override
    public List<Album> findAllAlbumsOfMusician(Musician musician) {
        List<Album> returnList = new ArrayList<>();
        if (musician == null) return returnList;
        for(Album a: findAll())
        {
            if (a.getMusician().equals(musician)) {
                returnList.add(a);
            }
        }
        return returnList;
    }
    
    private boolean checkReleaseYearRange(Album album, int fromYear, int toYear)
    {
        if(album == null)
        {
            return false;
        }
        Date releaseDate = album.getReleaseDate();
        int year = extractYearFromSQLDate(releaseDate);
        return year >= fromYear && year <= toYear;
    }
    
    private int extractYearFromSQLDate(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    @Override
    public Album updateAlbumMusician(Album album, Musician musician) {
        if (album != null && musician != null) {
            album.setMusician(musician);
            return albumDao.update(album);
        }
        return null;
    }

    @Override
    public Album updateAlbumCommentary(Album album, String commentary) {
        if (album != null && commentary != null && !commentary.isEmpty()) {
            album.setCommentary(commentary);
            return albumDao.update(album);
        }
        return null;
    }
}
