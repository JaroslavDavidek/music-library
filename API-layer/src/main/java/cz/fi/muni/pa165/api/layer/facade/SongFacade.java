package cz.fi.muni.pa165.api.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.SongCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import java.util.List;

/**
 *
 * @author JaroslavDavidek
 */
public interface SongFacade {   
    public Long createSong(SongCreateDTO song);
    public void deleteSong(Long songID);
    public void updateTitle(Long songID, String newTitle);
    public void updateBitrate(Long songID, int newBitrate);
    public void updateAlbumPosition(Long songID, int newAlbumPosition);
    public void updateCommentary(Long songID, String newCommentary);
    public void updateMusician(Long songID, Long musicianID);
    public void updateGenre(Long songID, Long genreID);
    public void updateAlbum(Long songID, Long albumID);
    public SongDTO getSongByID(Long songID);
    public List<SongDTO> getAllSongsByMusician(Long musicianID);
    public List<SongDTO> getAllSongsByGenre(Long genreID);
    public List<SongDTO> getAllSongsByAlbum(Long albumID);
}
