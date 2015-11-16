package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.SongCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.dao.AlbumDao;
import cz.fi.muni.pa165.dao.GenreDao;
import cz.fi.muni.pa165.dao.MusicianDao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import cz.fi.muni.pa165.service.layer.service.SongService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;;
import org.springframework.stereotype.Service;


/**
 *
 * @author JaroslavDavidek
 */
@Service
@Transactional
public class SongFacadeImplementation implements SongFacade {

    @Inject
    private MappingService mappingService;
    
    @Inject
    private SongService songService;
    
    /// region - following DAOs should be replaced by Services after they are created
    
    @Inject
    private AlbumDao albumDao;
    
    @Inject
    private MusicianDao musicianDao;
    
    @Inject
    private GenreDao genreDao;
    
    /// endregion
    
    @Override
    public Long createSong(SongCreateDTO song) {
        Song mappedSong = mappingService.mapTo(song, Song.class);
        
        Genre genre = new Genre();
        genre.setTitle(song.getGenreTitle());
        genre.setYearOfOrigin(song.getGenreYearOfOrigin());
        genreDao.create(genre);
        mappedSong.setGenre(genre);
        
        Musician musician = new Musician();
        musician.setArtistName(song.getMusicianArtistName());
        musician.setRealName(song.getMusicianRealName());
        musician.setDateOfBirth(new java.sql.Date(song.getMusicianDateOfBirth().getTime()));
        musicianDao.create(musician);
        mappedSong.setMusician(musician);       
        
        Album album = new Album();
        album.setTitle(song.getAlbumTitle());
        album.setMusician(musician);
        album.setReleaseDate(new java.sql.Date(song.getAlbumReleaseDate().getTime()));
        album.setCover(song.getAlbumCover());
        album.setCommentary(song.getAlbumCommentary());
        albumDao.create(album);
        mappedSong.setAlbum(album);
        
        return songService.createSong(mappedSong).getId();
    }

    @Override
    public void deleteSong(Long songID) {
        songService.deleteSong(songService.findSongByID(songID));
    }

    @Override
    public void updateTitle(Long songID, String newTitle) {
        songService.updateTitle(songService.findSongByID(songID), newTitle);
    }

    @Override
    public void updateBitrate(Long songID, int newBitrate) {
        songService.updateBitrate(songService.findSongByID(songID), newBitrate);
    }

    @Override
    public void updateAlbumPosition(Long songID, int newAlbumPosition) {
        songService.updateAlbumPosition(songService.findSongByID(songID), newAlbumPosition);
    }

    @Override
    public void updateCommentary(Long songID, String newCommentary) {
        songService.updateCommentary(songService.findSongByID(songID), newCommentary);
    }

    @Override
    public void updateMusician(Long songID, Long musicianID) {
        songService.updateMusician(songService.findSongByID(songID), musicianID);
    }

    @Override
    public void updateGenre(Long songID, Long genreID) {
        songService.updateGenre(songService.findSongByID(songID), genreID);
    }

    @Override
    public void updateAlbum(Long songID, Long albumID) {
        songService.updateAlbum(songService.findSongByID(songID), albumID);
    }

    @Override
    public SongDTO findSongByID(Long songID) {
        return mappingService.mapTo(songService.findSongByID(songID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByMusician(Long musicianID) {
        return mappingService.mapTo(songService.findAllSongsByMusician(musicianID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByGenre(Long genreID) {
        return mappingService.mapTo(songService.findAllSongsByGenre(genreID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByAlbum(Long albumID) {
        return mappingService.mapTo(songService.findAllSongsByAlbum(albumID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByAlbumOrdered(Long albumID, boolean ascending) {
        return mappingService.mapTo(songService.findAllSongsByAlbumOrdered(albumID, ascending), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByMusicianAndReleaseYearRange(Long musicianID, int fromYear, int toYear) {
        return mappingService.mapTo(songService.findAllSongsByMusicianAndReleaseYearRange(musicianID, fromYear, toYear), SongDTO.class);
    }

}
