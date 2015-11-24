package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.entity.Musician;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author Jergus Fasanek
 */
public interface MusicianService {
    public Musician createMusician(Musician musician);
    public boolean deleteMusician(Musician musician);
    public Musician updateRealName(Musician musician, String newName);
    public Musician updateArtistName(Musician musician, String newName);
    public Musician updateDateOfBirth(Musician musician, Date date);
    public Musician findMusicianByID(Long musicianID);
    public Musician findMusicianByRealName(String realName);
    public Musician findMusicianByArtistName(String artistName);
    public List<Musician> findAll();
}
