package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.MusicianDao;
import cz.fi.muni.pa165.entity.Musician;
import java.sql.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Jergus Fasanek
 */
public class MusicianServiceImplementation implements MusicianService{
    
    @Inject
    private MusicianDao musicianDao;
    
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
            return musicianDao.delete(musician);
        }
        return false;
    }

    @Override
    public Musician updateRealName(Musician musician, String newName) {
        if(musician != null){
            musician.setRealName(newName);
            return musicianDao.update(musician);
        }
        return null;
    }

    @Override
    public Musician updateArtistName(Musician musician, String newName) {
        if(musician != null){
            musician.setArtistName(newName);
            return musicianDao.update(musician);
        }
        return null;
    }

    @Override
    public Musician updateDateOfBirth(Musician musician, Date date) {
        if(musician != null){
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
    
}
