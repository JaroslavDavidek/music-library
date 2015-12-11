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

    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO getSong(@PathVariable("id") long id) throws Exception {

        SongDTO foundSongDTO = songFacade.findSongByID(id);
        if (foundSongDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongDTO;
    }
}
