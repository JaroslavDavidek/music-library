package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.entity.Song;
import java.util.List;

/**
 *
 * @author JaroslavDavidek
 */
public interface SongService {  
    public Song createSong(Song song);
    public boolean deleteSong(Song song);
    public Song updateSong(Song song);
    public Song updateTitle(Song song, String newTitle);
    public Song updateBitrate(Song song, int newBitrate);
    public Song updateAlbumPosition(Song song, int newAlbumPosition);
    public Song updateCommentary(Song song, String  newCommentary);
    public Song updateMusician(Song song, Long musicianID);
    public Song updateGenre(Song song, Long genreID);
    public Song updateAlbum(Song song, Long albumID);
    public Song findSongByID(Long songID);
    public Song findSongByTitle(String songTitle);
    public List<Song> findAll();
    public List<Song> findAllSongsByMusician(Long musicianID);
    public List<Song> findAllSongsByGenre(Long genreID);
    public List<Song> findAllSongsByAlbum(Long albumID);
    public List<Song> findAllSongsByAlbumOrdered(Long albumID, boolean ascending);
    public List<Song> findAllSongsByMusicianAndReleaseYearRange(Long musicianID, int fromYear, int toYear);
}
