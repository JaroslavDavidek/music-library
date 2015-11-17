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
    public boolean deleteSong(Long songID);
    public SongDTO updateTitle(Long songID, String newTitle);
    public SongDTO updateBitrate(Long songID, int newBitrate);
    public SongDTO updateAlbumPosition(Long songID, int newAlbumPosition);
    public SongDTO updateCommentary(Long songID, String newCommentary);
    public SongDTO updateMusician(Long songID, Long musicianID);
    public SongDTO updateGenre(Long songID, Long genreID);
    public SongDTO updateAlbum(Long songID, Long albumID);
    public SongDTO findSongByID(Long songID);
    public SongDTO findSongByTitle(String songTitle);
    public List<SongDTO> findAll();
    public List<SongDTO> findAllSongsByMusician(Long musicianID);
    public List<SongDTO> findAllSongsByGenre(Long genreID);
    public List<SongDTO> findAllSongsByAlbum(Long albumID);
    public List<SongDTO> findAllSongsByAlbumOrdered(Long albumID, boolean ascending);
    public List<SongDTO> findAllSongsByMusicianAndReleaseYearRange(Long musicianID, int fromYear, int toYear);
}
