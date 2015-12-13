package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.SongCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.service.AlbumService;
import cz.fi.muni.pa165.service.layer.service.GenreService;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import cz.fi.muni.pa165.service.layer.service.MusicianService;
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
    
    @Inject
    private AlbumService albumService;
   
    @Inject
    private GenreService genreService;
    
    @Inject 
    private MusicianService musicianService;
    
    @Override
    public Long createSong(SongCreateDTO songCreateDTO) {
        Song song = new Song();
        song.setAlbum(albumService.findById(songCreateDTO.getAlbumId()));
        song.setMusician(musicianService.findMusicianByID(songCreateDTO.getMusicianId()));
        song.setGenre(genreService.findGenreByID(songCreateDTO.getGenreId()));
        song.setTitle(songCreateDTO.getTitle());
        song.setAlbumPosition(songCreateDTO.getAlbumPosition());
        song.setBitrate(songCreateDTO.getBitrate());
        song.setCommentary(songCreateDTO.getCommentary()); 
        songService.createSong(song);
        return song.getId();
    }

    @Override
    public boolean deleteSong(Long songID) {
        return songService.deleteSong(songService.findSongByID(songID));
    }

    @Override
    public SongDTO updateTitle(Long songID, String newTitle) {
        return mappingService.mapToEnforceID(songService.updateTitle(songService.findSongByID(songID), newTitle), SongDTO.class);
    }

    @Override
    public SongDTO updateBitrate(Long songID, int newBitrate) {
        return mappingService.mapToEnforceID(songService.updateBitrate(songService.findSongByID(songID), newBitrate), SongDTO.class);
    }

    @Override
    public SongDTO updateAlbumPosition(Long songID, int newAlbumPosition) {
        return mappingService.mapToEnforceID(songService.updateAlbumPosition(songService.findSongByID(songID), newAlbumPosition), SongDTO.class);
    }

    @Override
    public SongDTO updateCommentary(Long songID, String newCommentary) {
        return mappingService.mapToEnforceID(songService.updateCommentary(songService.findSongByID(songID), newCommentary), SongDTO.class);
    }

    @Override
    public SongDTO updateMusician(Long songID, Long musicianID) {
        return mappingService.mapToEnforceID(songService.updateMusician(songService.findSongByID(songID), musicianID), SongDTO.class);
    }

    @Override
    public SongDTO updateGenre(Long songID, Long genreID) {
        return mappingService.mapToEnforceID(songService.updateGenre(songService.findSongByID(songID), genreID), SongDTO.class);
    }

    @Override
    public SongDTO updateAlbum(Long songID, Long albumID) {
        return mappingService.mapToEnforceID(songService.updateAlbum(songService.findSongByID(songID), albumID), SongDTO.class);
    }

    @Override
    public SongDTO findSongByID(Long songID) {
        return mappingService.mapToEnforceID(songService.findSongByID(songID), SongDTO.class);
    }
    
    @Override
    public SongDTO findSongByTitle(String songTitle) {
        return mappingService.mapToEnforceID(songService.findSongByTitle(songTitle), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAll() {
        return mappingService.mapToCollectionEnforceID(songService.findAll(), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByMusician(Long musicianID) {
        return mappingService.mapToCollectionEnforceID(songService.findAllSongsByMusician(musicianID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByGenre(Long genreID) {
        return mappingService.mapToCollectionEnforceID(songService.findAllSongsByGenre(genreID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByAlbum(Long albumID) {
        return mappingService.mapToCollectionEnforceID(songService.findAllSongsByAlbum(albumID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByAlbumOrdered(Long albumID, boolean ascending) {
        return mappingService.mapToCollectionEnforceID(songService.findAllSongsByAlbumOrdered(albumID, ascending), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByMusicianAndReleaseYearRange(Long musicianID, int fromYear, int toYear) {
        return mappingService.mapToCollectionEnforceID(songService.findAllSongsByMusicianAndReleaseYearRange(musicianID, fromYear, toYear), SongDTO.class);
    }

    

}
