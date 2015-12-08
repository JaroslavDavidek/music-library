package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import cz.fi.muni.pa165.service.layer.service.MappingServiceImplementation;
import cz.fi.muni.pa165.service.layer.service.SongService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;;
import org.springframework.stereotype.Service;


/**
 *
 * @author JaroslavDavidek
 */
@Service
@Component
@ComponentScan(basePackageClasses={MappingServiceImplementation.class})
@Transactional
public class SongFacadeImplementation implements SongFacade {

    @Inject
    private MappingService mappingService;
    
    @Inject
    private SongService songService;
   
    @Override
    public Long createSong(SongDTO songCreateDTO) {
        Song mappedSong = mappingService.mapTo(songCreateDTO, Song.class);      
        return songService.createSong(mappedSong).getId();
    }

    @Override
    public boolean deleteSong(Long songID) {
        return songService.deleteSong(songService.findSongByID(songID));
    }

    @Override
    public SongDTO updateTitle(Long songID, String newTitle) {
        return mappingService.mapTo(songService.updateTitle(songService.findSongByID(songID), newTitle), SongDTO.class);
    }

    @Override
    public SongDTO updateBitrate(Long songID, int newBitrate) {
        return mappingService.mapTo(songService.updateBitrate(songService.findSongByID(songID), newBitrate), SongDTO.class);
    }

    @Override
    public SongDTO updateAlbumPosition(Long songID, int newAlbumPosition) {
        return mappingService.mapTo(songService.updateAlbumPosition(songService.findSongByID(songID), newAlbumPosition), SongDTO.class);
    }

    @Override
    public SongDTO updateCommentary(Long songID, String newCommentary) {
        return mappingService.mapTo(songService.updateCommentary(songService.findSongByID(songID), newCommentary), SongDTO.class);
    }

    @Override
    public SongDTO updateMusician(Long songID, Long musicianID) {
        return mappingService.mapTo(songService.updateMusician(songService.findSongByID(songID), musicianID), SongDTO.class);
    }

    @Override
    public SongDTO updateGenre(Long songID, Long genreID) {
        return mappingService.mapTo(songService.updateGenre(songService.findSongByID(songID), genreID), SongDTO.class);
    }

    @Override
    public SongDTO updateAlbum(Long songID, Long albumID) {
        return mappingService.mapTo(songService.updateAlbum(songService.findSongByID(songID), albumID), SongDTO.class);
    }

    @Override
    public SongDTO findSongByID(Long songID) {
        return mappingService.mapTo(songService.findSongByID(songID), SongDTO.class);
    }
    
    @Override
    public SongDTO findSongByTitle(String songTitle) {
        return mappingService.mapTo(songService.findSongByTitle(songTitle), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAll() {
        return mappingService.mapToCollection(songService.findAll(), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByMusician(Long musicianID) {
        return mappingService.mapToCollection(songService.findAllSongsByMusician(musicianID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByGenre(Long genreID) {
        return mappingService.mapToCollection(songService.findAllSongsByGenre(genreID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByAlbum(Long albumID) {
        return mappingService.mapToCollection(songService.findAllSongsByAlbum(albumID), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByAlbumOrdered(Long albumID, boolean ascending) {
        return mappingService.mapToCollection(songService.findAllSongsByAlbumOrdered(albumID, ascending), SongDTO.class);
    }

    @Override
    public List<SongDTO> findAllSongsByMusicianAndReleaseYearRange(Long musicianID, int fromYear, int toYear) {
        return mappingService.mapToCollection(songService.findAllSongsByMusicianAndReleaseYearRange(musicianID, fromYear, toYear), SongDTO.class);
    }

    

}
