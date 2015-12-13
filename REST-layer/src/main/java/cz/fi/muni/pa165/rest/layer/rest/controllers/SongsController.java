package cz.fi.muni.pa165.rest.layer.rest.controllers;

import cz.fi.muni.pa165.api.layer.dto.*;
import cz.fi.muni.pa165.api.layer.dto.SongCreateDTO;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import java.util.List;
import org.springframework.http.MediaType;
import javax.inject.Inject;
import cz.fi.muni.pa165.rest.layer.rest.ApiUris;
import cz.fi.muni.pa165.rest.layer.rest.exceptions.AlreadyExists422Exception;
import cz.fi.muni.pa165.rest.layer.rest.exceptions.NotAcceptable406Exception;
import cz.fi.muni.pa165.rest.layer.rest.exceptions.NotFound404Exception;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
  
    /**
     * GET song by ID
     * 
     * @param id
     * @return
     * @throws NotFound404Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO getSongByID(@PathVariable("id") long id) throws NotFound404Exception {

        SongDTO foundSongDTO = songFacade.findSongByID(id);
        if (foundSongDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongDTO;
    }
    
    /**
     * GET song by title
     * 
     * @param title
     * @return
     * @throws NotFound404Exception 
     */
    @RequestMapping(value = "/title={title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO getSongByTitle(@PathVariable("title") String title) throws NotFound404Exception {

        SongDTO foundSongDTO = songFacade.findSongByTitle(title);
        if (foundSongDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongDTO;
    }
    
    /**
     * GET all stored songs
     * 
     * @return 
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongs() {

        return songFacade.findAll();
    }
    
    /**
     * GET all songs within the album
     * 
     * @param albumId
     * @return
     * @throws NotFound404Exception 
     */
    @RequestMapping(value = "/{id}/listByAlbum", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongsByAlbum(@PathVariable("id") long albumId) throws NotFound404Exception {
        
        List<SongDTO> foundSongsDTO = songFacade.findAllSongsByAlbum(albumId);
        if (foundSongsDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongsDTO;
    }
    
    /**
     * GET songs within the album in an ordered way
     * 
     * @param albumId the id of a album
     * @param type can be either of value 'asc' - use ascending order, or 'dsc' - descending
     * @return all ordered songs for respective album
     */
    @RequestMapping(value = "/{id}/{type}/listByAlbum", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongsByAlbum(@PathVariable("id") long albumId, @PathVariable("type") String type) throws NotFound404Exception {
        
        if(type != null && !type.isEmpty()){
            if(type.equals("asc")){
                
                List<SongDTO> foundSongsDTO = songFacade.findAllSongsByAlbumOrdered(albumId, true);
                if (foundSongsDTO == null) {
                    throw new NotFound404Exception();
                }
                return foundSongsDTO;
            }
            if(type.equals("dsc")){
                
                List<SongDTO> foundSongsDTO = songFacade.findAllSongsByAlbumOrdered(albumId, false);
                if (foundSongsDTO == null) {
                    throw new NotFound404Exception();
                }
                return foundSongsDTO;
            }
        }
        List<SongDTO> foundSongsDTO = songFacade.findAllSongsByAlbum(albumId);
        if (foundSongsDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongsDTO;
    }
    
    /**
     * GET all songs according to Musician
     * 
     * @param musicianId
     * @return
     * @throws NotFound404Exception 
     */
    @RequestMapping(value = "/{id}/listByMusician", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongsByMusician(@PathVariable("id") long musicianId) throws NotFound404Exception {

        List<SongDTO> foundSongsDTO = songFacade.findAllSongsByMusician(musicianId);
        if (foundSongsDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongsDTO;
    }
    
    /**
     * GET all songs according to Musician from specific time span
     * 
     * @param musicianId id of the corresponding author
     * @param fromYear lower bound of the time span (songs released within this year are also included in the result)
     * @param toYear upper bound of the time span (songs released within this year are also included in the result)
     * @return all songs by specified author which were released in respective time span
     * @throws NotFound404Exception 
     */
    @RequestMapping(value = "/{id}/from={fromYear}to={toYear}/listByMusician", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongsByMusicianAndReleaseYearRange(@PathVariable("id") long musicianId,
            @PathVariable("fromYear") int fromYear, @PathVariable("toYear") int toYear) throws NotFound404Exception {

        List<SongDTO> foundSongsDTO = songFacade.findAllSongsByMusicianAndReleaseYearRange(musicianId, fromYear, toYear);
        if (foundSongsDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongsDTO;
    }
    
    /**
     * GET all songs according to Genre
     * 
     * @param genreId
     * @return 
     */
    @RequestMapping(value = "/{id}/listByGenre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<SongDTO> getAllSongsByGenre(@PathVariable("id") long genreId) {

        List<SongDTO> foundSongsDTO = songFacade.findAllSongsByMusician(genreId);
        if (foundSongsDTO == null) {
            throw new NotFound404Exception();
        }
        return foundSongsDTO;
    }

    /**
     * CREATE new song from SongCreateDTO body
     * 
     * @param song data of type SongCreateDTO from which the song should be created
     * @return created song
     * @throws AlreadyExists422Exception 
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO createSong(@RequestBody SongCreateDTO song) throws AlreadyExists422Exception {

        try {           
            Long id = songFacade.createSong(song);
            return songFacade.findSongByID(id);
        } catch (Exception ex) {
            throw new AlreadyExists422Exception();
        }
    }
    
    /**
     * DELETE song with the corresponding ID
     * 
     * @param id id of song which should be deleted
     * @throws NotFound404Exception 
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteSong(@PathVariable("id") long id) throws NotFound404Exception {

        if(!songFacade.deleteSong(id)){
            throw new NotFound404Exception();
        }
    }
    
    /**
     * PUT (updates) song
     * 
     * @param id id of song which should be updated
     * @param title parameter to update
     * @return updated Song
     * @throws NotAcceptable406Exception 
     */
    @RequestMapping(value = "/{id}/title", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO updateTitle(@PathVariable("id") long id, @RequestBody String title) throws NotAcceptable406Exception {

        SongDTO updatedSong = songFacade.updateTitle(id, title);      
        if(updatedSong == null){
            throw new NotAcceptable406Exception();
        }
        return updatedSong;
    }
    
    /**
     * PUT (updates) song
     * 
     * @param id id of song which should be updated
     * @param bitrate parameter to update
     * @return updated Song
     * @throws NotAcceptable406Exception 
     */
    @RequestMapping(value = "/{id}/bitrate", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO updateBitrate(@PathVariable("id") long id, @RequestBody int bitrate) throws NotAcceptable406Exception {

        SongDTO updatedSong = songFacade.updateBitrate(id, bitrate);      
        if(updatedSong == null){
            throw new NotAcceptable406Exception();
        }
        return updatedSong;
    }
    
    /**
     * PUT (updates) song
     * 
     * @param id id of song which should be updated
     * @param albumPosition parameter to update
     * @return updated Song
     * @throws NotAcceptable406Exception 
     */
    @RequestMapping(value = "/{id}/albumPosition", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO updateAlbumPosition(@PathVariable("id") long id, @RequestBody int albumPosition) throws NotAcceptable406Exception {

        SongDTO updatedSong = songFacade.updateAlbumPosition(id, albumPosition);      
        if(updatedSong == null){
            throw new NotAcceptable406Exception();
        }
        return updatedSong;
    }
    
    /**
     * PUT (updates) song
     * 
     * @param id id of song which should be updated
     * @param commentary parameter to update
     * @return updated Song
     * @throws NotAcceptable406Exception 
     */
    @RequestMapping(value = "/{id}/commentary", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO updateCommentary(@PathVariable("id") long id, @RequestBody String commentary) throws NotAcceptable406Exception {

        SongDTO updatedSong = songFacade.updateCommentary(id, commentary);      
        if(updatedSong == null){
            throw new NotAcceptable406Exception();
        }
        return updatedSong;
    }
    
    /**
     * PUT (updates) song
     * 
     * @param id id of song which should be updated
     * @param album parameter to update
     * @return updated Song
     * @throws NotAcceptable406Exception 
     */
    @RequestMapping(value = "/{id}/album", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO updateAlbum(@PathVariable("id") long id, @RequestBody long albumId) throws NotAcceptable406Exception {

        SongDTO updatedSong = songFacade.updateAlbum(id, albumId);      
        if(updatedSong == null){
            throw new NotAcceptable406Exception();
        }
        return updatedSong;
    }
    
    /**
     * PUT (updates) song
     * 
     * @param id id of song which should be updated
     * @param musician parameter to update
     * @return updated Song
     * @throws NotAcceptable406Exception 
     */
    @RequestMapping(value = "/{id}/musician", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO updateMusician(@PathVariable("id") long id, @RequestBody long musicianId) throws NotAcceptable406Exception {

        SongDTO updatedSong = songFacade.updateMusician(id, musicianId);      
        if(updatedSong == null){
            throw new NotAcceptable406Exception();
        }
        return updatedSong;
    }
    
    /**
     * PUT (updates) song
     * 
     * @param id id of song which should be updated
     * @param genre parameter to update
     * @return updated Song
     * @throws NotAcceptable406Exception 
     */
    @RequestMapping(value = "/{id}/genre", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final SongDTO updateGenre(@PathVariable("id") long id, @RequestBody long genreId) throws NotAcceptable406Exception {

        SongDTO updatedSong = songFacade.updateGenre(id, genreId);      
        if(updatedSong == null){
            throw new NotAcceptable406Exception();
        }
        return updatedSong;
    }
}
