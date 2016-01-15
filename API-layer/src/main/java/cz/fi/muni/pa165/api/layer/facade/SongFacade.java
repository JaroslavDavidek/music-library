package cz.fi.muni.pa165.api.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.SongCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import java.util.List;

/**
 *
 * @author JaroslavDavidek
 */
public interface SongFacade {  
    
    /**
     * Creates new song
     *
     * @param song song to be created
     * @return id of created song
     */
    public Long createSong(SongCreateDTO song);
    
     /**
     * Deletes song from database.
     * 
     * @param songID  id of song to delete
     */
    public boolean deleteSong(Long songID);
    
    /**
     * updates Song
     * 
     * @param newSongDTO song to update
     * @return 
     */
    public SongDTO updateSong(SongDTO newSongDTO);
    
    /**
     * updates title
     * 
     * @param songID
     * @param newTitle
     * @return 
     */
    public SongDTO updateTitle(Long songID, String newTitle);
    
    /**
     * updates bitrate
     * 
     * @param songID
     * @param newBitrate
     * @return 
     */
    public SongDTO updateBitrate(Long songID, int newBitrate);
    
    /**
     * updates album position
     * 
     * @param songID
     * @param newAlbumPosition
     * @return 
     */
    public SongDTO updateAlbumPosition(Long songID, int newAlbumPosition);
    
    /**
     * updates commentary
     * 
     * @param songID
     * @param newCommentary
     * @return 
     */
    public SongDTO updateCommentary(Long songID, String newCommentary);
    
    /**
     * updates musician
     * 
     * @param songID
     * @param musicianID
     * @return 
     */
    public SongDTO updateMusician(Long songID, Long musicianID);
    
    /**
     * updates genre
     * 
     * @param songID
     * @param genreID
     * @return 
     */
    public SongDTO updateGenre(Long songID, Long genreID);
    
    /**
     * updates album
     * 
     * @param songID
     * @param albumID
     * @return 
     */
    public SongDTO updateAlbum(Long songID, Long albumID);
    
    /**
     * finds song by ID
     * 
     * @param songID
     * @return 
     */
    public SongDTO findSongByID(Long songID);
    
    /**
     * finds song by title
     * 
     * @param songTitle
     * @return 
     */
    public SongDTO findSongByTitle(String songTitle);
    
    /**
     * finds all songs
     * 
     * @return 
     */
    public List<SongDTO> findAll();
    
    /**
     * finds all songs by musician
     * 
     * @param musicianID
     * @return 
     */
    public List<SongDTO> findAllSongsByMusician(Long musicianID);
    
    /**
     * finds all songs by genre
     * 
     * @param genreID
     * @return 
     */
    public List<SongDTO> findAllSongsByGenre(Long genreID);
    
    /**
     * finds all songs by album
     * 
     * @param albumID
     * @return 
     */
    public List<SongDTO> findAllSongsByAlbum(Long albumID);
    
    /**
     * finds all songs by album ordered
     * 
     * @param albumID
     * @param ascending
     * @return 
     */
    public List<SongDTO> findAllSongsByAlbumOrdered(Long albumID, boolean ascending);
    
    /**
     * finds all songs by musician and release year range
     * 
     * @param musicianID
     * @param fromYear
     * @param toYear
     * @return 
     */
    public List<SongDTO> findAllSongsByMusicianAndReleaseYearRange(Long musicianID, int fromYear, int toYear);
}
