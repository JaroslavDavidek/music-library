package cz.fi.muni.pa165.rest.layer.rest.controllers;

import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import java.util.List;
import org.springframework.http.MediaType;
import javax.inject.Inject;
import cz.fi.muni.pa165.rest.layer.rest.ApiUris;
import cz.fi.muni.pa165.rest.layer.rest.exceptions.NotFound404Exception;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author JaroslavDavidek
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_SONGS)
public class SongsController {
    
    @Inject
    private SongFacade songFacade;
  
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongs() {

        return songFacade.findAll();
    }
    
    @RequestMapping(value = "/{id}/listByAlbum", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongsByAlbum(@PathVariable("id") long albumId) {
        
        List<SongDTO> foundSongsDTO = songFacade.findAllSongsByAlbum(albumId);
        if (foundSongsDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongsDTO;
    }
    
    @RequestMapping(value = "/{id}/listByMusician", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongsByMusician(@PathVariable("id") long musicianId) {

        List<SongDTO> foundSongsDTO = songFacade.findAllSongsByMusician(musicianId);
        if (foundSongsDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongsDTO;
    }
    
    @RequestMapping(value = "/{id}/listByGenre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongsByGenre(@PathVariable("id") long genreId) {

        List<SongDTO> foundSongsDTO = songFacade.findAllSongsByMusician(genreId);
        if (foundSongsDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongsDTO;
    }

    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO getSong(@PathVariable("id") long id) throws Exception {

        SongDTO foundSongDTO = songFacade.findSongByID(id);
        if (foundSongDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongDTO;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteSong(@PathVariable("id") long id) throws Exception {

        if(!songFacade.deleteSong(id)){
            throw new NotFound404Exception();
        }
    }
}
