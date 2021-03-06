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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;


/**
 *
 * @author JaroslavDavidek
 */
@Service
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
        if(songDao.create(song))
        {
            return song;
        }
        return null;
    }

    @Override
    public boolean deleteSong(Song song) {
        if(song != null)
        {
            return songDao.delete(song);
        }
        return false;
    }
    
    @Override
    public Song updateSong(Song song) {
        if(song != null)
        {
            return songDao.update(song);
        }
        return null;
    }

    @Override
    public Song updateTitle(Song song, String newTitle) {
        if(song != null && newTitle != null && !newTitle.isEmpty())
        {
            song.setTitle(newTitle);
            return songDao.update(song);
        }
        return null;
    }

    @Override
    public Song updateBitrate(Song song, int newBitrate) {
        if(song != null)
        {
            song.setBitrate(newBitrate);
            return songDao.update(song);
        }
        return null;
    }

    @Override
    public Song updateAlbumPosition(Song song, int newAlbumPosition) {
        if(song != null)
        {
            song.setAlbumPosition(newAlbumPosition);
            return songDao.update(song);
        }
        return null;
    }

    @Override
    public Song updateCommentary(Song song, String newCommentary) {
        if(song != null)
        {
            song.setCommentary(newCommentary);
            return songDao.update(song);
        }
        return null;
    }

    @Override
    public Song updateMusician(Song song, Long musicianID) {
        Musician musician = musicianDao.findById(musicianID);
        if(song != null && musician != null)
        {
            song.setMusician(musician);
            return songDao.update(song);
        }
        return null;
    }

    @Override
    public Song updateGenre(Song song, Long genreID) {
        Genre genre = genreDao.findById(genreID);
        if(song != null && genre != null)
        {
            song.setGenre(genre);
            return songDao.update(song);
        }
        return null;
    }

    @Override
    public Song updateAlbum(Song song, Long albumID) {
        Album album = albumDao.findById(albumID);
        if(song != null && album != null)
        {
            song.setAlbum(album);
            return songDao.update(song);
        }
        return null;
    }

    @Override
    public Song findSongByID(Long songID) {
        return songDao.findById(songID);
    }
    
    @Override
    public Song findSongByTitle(String songTitle) {
        return songDao.findByTitle(songTitle);
    }
    
    @Override
    public List<Song> findAll() {
        return songDao.findAll();
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
    
    public String createLastFMSearchQuery(Long songID){      
        Song song = songDao.findById(songID);
        String query = "http://www.last.fm/search?q=";
        if(song == null){
            return query;
        }
        return createURLQueryFromSong(song, query);
    }

    
    public String createYouTubeSearchQuery(Long songID){      
        Song song = songDao.findById(songID);
        String query = "https://www.youtube.com/results?search_query=";
        if(song == null){
            return query;
        }       
        return createURLQueryFromSong(song, query);
    }
    
     private String createURLQueryFromSong(Song song, String query) {
        Musician musician = song.getMusician();
        if(musician!= null && musician.getArtistName() != null){
            try {
                query += URLEncoder.encode(musician.getArtistName(), "UTF-8") + "+";
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SongServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            query += URLEncoder.encode(song.getTitle(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SongServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return query;
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
        if(allSongsByMusician.isEmpty() || fromYear > toYear)
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
