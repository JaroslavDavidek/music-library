package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.AlbumDao;
import cz.fi.muni.pa165.dao.GenreDao;
import cz.fi.muni.pa165.dao.MusicianDao;
import cz.fi.muni.pa165.dao.SongDao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.util.comparator.SongPositionASCComparator;
import cz.fi.muni.pa165.service.layer.util.comparator.SongPositionDSCComparator;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;


/**
 *
 * @author JaroslavDavidek
 */
public class SongServiceImplementation implements SongService{

    @Inject
    private SongDao songDao;
    
    @Inject
    private MusicianDao musicianDao;
    
    @Inject
    private GenreDao genreDao;
    
    @Inject
    private AlbumDao albumDao;
    
    
    @Override
    public Song createSong(Song song) {
        songDao.create(song);
        return song;
    }

    @Override
    public void deleteSong(Song song) {
        songDao.delete(song);
    }

    @Override
    public void updateTitle(Song song, String newTitle) {
        if(newTitle != null && !newTitle.isEmpty())
        {
            song.setTitle(newTitle);
        }
        songDao.update(song);
    }

    @Override
    public void updateBitrate(Song song, int newBitrate) {
        song.setBitrate(newBitrate);
        songDao.update(song);
    }

    @Override
    public void updateAlbumPosition(Song song, int newAlbumPosition) {
        song.setAlbumPosition(newAlbumPosition);
        songDao.update(song);
    }

    @Override
    public void updateCommentary(Song song, String newCommentary) {
        song.setCommentary(newCommentary);
        songDao.update(song);
    }

    @Override
    public void updateMusician(Song song, Long musicianID) {
        Musician musician = musicianDao.findById(musicianID);
        if(musician != null)
        {
            song.setMusician(musician);
            songDao.update(song);
        }
    }

    @Override
    public void updateGenre(Song song, Long genreID) {
        Genre genre = genreDao.findById(genreID);
        if(genre != null)
        {
            song.setGenre(genre);
            songDao.update(song);
        }
    }

    @Override
    public void updateAlbum(Song song, Long albumID) {
        Album album = albumDao.findById(albumID);
        if(album != null)
        {
            song.setAlbum(album);
            songDao.update(song);
        }
    }

    @Override
    public Song findSongByID(Long songID) {
        return songDao.findById(songID);
    }

    @Override
    public List<Song> findAllSongsByMusician(Long musicianID) {
        Musician musician = musicianDao.findById(musicianID);
        if(musician != null)
        {
            return songDao.findAllByMusician(musician);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Song> findAllSongsByGenre(Long genreID) {
        Genre genre = genreDao.findById(genreID);
        if(genre != null)
        {
            return songDao.findAllByGenre(genre);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Song> findAllSongsByAlbum(Long albumID) {
        Album album = albumDao.findById(albumID);
        if(album != null)
        {
            return songDao.findAllByAlbum(album);
        }
        return new ArrayList<>();
    }
    
    /**
     * Lists all songs from respective album in ordered way
     * 
     * @param albumID album according to which the songs should be sorted
     * @param ascending sorts songs in ascending manner if set to true, otherwise descending order will be used
     * @return
     */
    @Override
    public List<Song> findAllSongsByAlbumOrdered(Long albumID, boolean ascending){
        List<Song> foundSongs = this.findAllSongsByAlbum(albumID);
        if(foundSongs.isEmpty()) 
        {
            return new ArrayList<>();
        }
        Comparator songPositionComparator;
        if(ascending)
        {
            songPositionComparator = new SongPositionASCComparator();
        }
        else
        {
            songPositionComparator = new SongPositionDSCComparator();
        }
        Collections.sort(foundSongs, songPositionComparator);
        return foundSongs;
    }
    
    @Override
    public List<Song> findAllSongsByMusicianAndReleaseYearRange(Long musicianID, int fromYear, int toYear) {
        /* this can be implemented more efficiently as a JPQL query in Dao,
        but since the nontrivial functionality must be placed within the service layer,
        following implementation is used */
        List<Song> filteredSongs = new ArrayList<>();
        List<Song> allSongsByMusician = this.findAllSongsByMusician(musicianID);
        if(allSongsByMusician.isEmpty())
        {
            return filteredSongs;
        }
        for(Song songByMusician : allSongsByMusician)
        {
            if(checkReleaseYearRange(songByMusician, fromYear, toYear))
            {
                filteredSongs.add(songByMusician);
            }
        }
        return filteredSongs;
    }
    
    private boolean checkReleaseYearRange(Song song, int fromYear, int toYear)
    {
        if(song == null)
        {
            return false;
        }
        Date releaseDate = song.getAlbum().getReleaseDate();
        int year = extractYearFromSQLDate(releaseDate);
        return year >= fromYear && year <= toYear;
    }
    
    private int extractYearFromSQLDate(Date date)
    {
        // getYear is depricated
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

}
