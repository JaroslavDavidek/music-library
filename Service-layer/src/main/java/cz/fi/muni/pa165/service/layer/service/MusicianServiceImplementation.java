package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.AlbumDao;
import cz.fi.muni.pa165.dao.MusicianDao;
import cz.fi.muni.pa165.dao.SongDao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.util.comparator.MusicianPositionASCComparator;
import cz.fi.muni.pa165.service.layer.util.comparator.MusicianPositionDSCComparator;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jergus Fasanek
 */
@Service
public class MusicianServiceImplementation implements MusicianService{
    
    @Inject
    private MusicianDao musicianDao;
    
    @Inject
    private SongDao songDao;
    
    @Inject
    private AlbumDao albumDao;
    
    @Override
    public Musician createMusician(Musician musician) {
        if(musicianDao.create(musician)){
            return musician;
        }
        return null;
    }

    @Override
    public boolean deleteMusician(Musician musician) {
        if(musician != null){
            
            // find all albums by Musician
            List<Album> musicianAlbums = new ArrayList<>();
            for(Song song : songDao.findAllByMusician(musician)) {
                
                Album songParentAlbum = song.getAlbum();
                if(songParentAlbum != null && !musicianAlbums.contains(songParentAlbum)){
                    
                    if(songParentAlbum.getSongs() != null){
                        songParentAlbum.getSongs().clear();
                    }
                    musicianAlbums.add(songParentAlbum);
                }
                songDao.delete(song);
            }
            for(Album album : musicianAlbums) {
                
                if(album.getSongs() != null){
                    album.getSongs().clear();
                }             
                albumDao.delete(album);
            }
            return musicianDao.delete(musician);
        }
        return false;
    }

    @Override
    public Musician updateRealName(Musician musician, String newName) {
        if(musician != null && newName != null){
            musician.setRealName(newName);
            return musicianDao.update(musician);
        }
        return null;
    }

    @Override
    public Musician updateArtistName(Musician musician, String newName) {
        if(musician != null && newName != null){
            musician.setArtistName(newName);
            return musicianDao.update(musician);
        }
        return null;
    }

    @Override
    public Musician updateDateOfBirth(Musician musician, Date date) {
        if(musician != null && date != null){
            musician.setDateOfBirth(date);
            return musicianDao.update(musician);
        }
        return null;
    }

    @Override
    public Musician findMusicianByID(Long musicianID) {
        return musicianDao.findById(musicianID);
    }

    @Override
    public Musician findMusicianByRealName(String realName) {
        return musicianDao.findByRealName(realName);
    }

    @Override
    public Musician findMusicianByArtistName(String artistName) {
        return musicianDao.findByArtistName(artistName);
    }

    @Override
    public List<Musician> findAll() {
        return musicianDao.findAll();
    }
    
    @Override
    public List<Musician> findAllMusiciansInYearRange(int from, int to){
        List<Musician> filteredMusicians = new ArrayList<>();
        List<Musician> allMusicians = this.findAll();
        if(from > to || allMusicians.isEmpty()){
            return filteredMusicians;
        }
        for(Musician musician : allMusicians){
            Date dateOfBirth = musician.getDateOfBirth();
            Calendar calendar  = Calendar.getInstance();
            calendar.setTime(dateOfBirth);
            int year = calendar.get(Calendar.YEAR);
            if(year >= from && year <= to){
                filteredMusicians.add(musician);
            }
        }
        return filteredMusicians;
    }
    
    @Override
    public List<Musician> findAllMusiciansByDateOfBirthOrdered(boolean ascending){    
        List<Musician> allMusicians = this.findAll();
        if(allMusicians.isEmpty()){
            return new ArrayList<>();
        }
        Comparator comparator;
        if(ascending){
            comparator = new MusicianPositionASCComparator();
        } else {
            comparator = new MusicianPositionDSCComparator();
        }
        Collections.sort(allMusicians, comparator);
        return allMusicians;
    }
    
}
