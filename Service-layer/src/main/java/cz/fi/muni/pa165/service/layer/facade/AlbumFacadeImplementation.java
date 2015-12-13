/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import cz.fi.muni.pa165.api.layer.facade.AlbumFacade;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.service.layer.service.AlbumService;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import cz.fi.muni.pa165.service.layer.service.MusicianService;
import cz.fi.muni.pa165.service.layer.service.SongService;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Franek
 */
@Service
@Transactional
public class AlbumFacadeImplementation implements AlbumFacade {

    @Inject
    private AlbumService albumService;

    @Inject
    private SongService songService;
    
    @Inject
    private MusicianService musicianService; 

    @Inject
    private MappingService mappingService;

    @Override
    public Long createAlbum(AlbumDTO albumDto) {
        Album album = new Album();
        album.setTitle(albumDto.getTitle());
        album.setCommentary(albumDto.getCommentary());
        album.setReleaseDate(albumDto.getReleaseDate());
        albumService.createAlbum(album);
        return album.getId();
    }
    
    @Override
    public long createAlbum(AlbumCreateDTO albumDto) {
        Album album = new Album();
        album.setTitle(albumDto.getTitle());
        album.setCommentary(albumDto.getCommentary());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            album.setReleaseDate(new java.sql.Date(format.parse(albumDto.getReleaseDate()).getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(AlbumFacadeImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        album.setMusician(musicianService.findMusicianByID(albumDto.getMusicianId()));
        albumService.createAlbum(album);
        return album.getId();
    }

    @Override
    public AlbumDTO addSong(Long albumId, Long songId) {
        albumService.addSong(albumService.findById(albumId), songService.findSongByID(songId));
        return mappingService.mapToEnforceID(albumService.findById(albumId), AlbumDTO.class);
    }

    @Override
    public AlbumDTO removeSong(Long albumId, Long songId) {
        albumService.removeSong(albumService.findById(albumId), songService.findSongByID(songId));
        return mappingService.mapToEnforceID(albumService.findById(albumId), AlbumDTO.class);
    }

    @Override
    public boolean deleteAlbum(Long albumId) {
        return albumService.deleteAlbum(albumService.findById(albumId));
    }

    @Override
    public List<AlbumDTO> findAll() {
        return mappingService.mapToCollectionEnforceID(albumService.findAll(), AlbumDTO.class);
    }

    @Override
    public AlbumDTO findById(Long id) {
        return mappingService.mapToEnforceID(albumService.findById(id), AlbumDTO.class);
    }

    @Override
    public AlbumDTO findByTitle(String title) {
        return mappingService.mapToEnforceID(albumService.findByTitle(title), AlbumDTO.class);
    }

    @Override
    public AlbumDTO updateAlbumTitle(Long albumId, String title) {
        return mappingService.mapToEnforceID(albumService.updateAlbumTitle(albumService.findById(albumId), title), AlbumDTO.class);
    }

    @Override
    public AlbumDTO updateAlbumReleaseDate(Long albumId, Date releasedate) {
        return mappingService.mapToEnforceID(albumService.updateAlbumReleaseDate(albumService.findById(albumId), releasedate), AlbumDTO.class);
    }
    
    
    
}
