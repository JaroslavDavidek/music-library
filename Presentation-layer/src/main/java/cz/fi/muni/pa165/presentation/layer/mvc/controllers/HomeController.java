package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import java.sql.Date;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

/**
 *
 * @author JaroslavDavidek
 */
@Controller
@RequestMapping(value = {"", "/", "/home"})
public class HomeController {
    
    @Autowired
    private SongFacade songFacade;
    
    @Inject
    private MessageSource messageSource; 
    
     @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String home(Model model) {
        
        Genre hardRock = new Genre();
        hardRock.setTitle("Hard Rock");
        hardRock.setYearOfOrigin(1970); 
        
        Musician acdc = new Musician();
        acdc.setRealName("Brian Johnson");
        acdc.setArtistName("AC/DC");
        acdc.setDateOfBirth(Date.valueOf("1945-10-15"));
        
        
        Album backInBlackAlbum = new Album();
        backInBlackAlbum.setMusician(acdc);
        backInBlackAlbum.setReleaseDate(Date.valueOf("1980-7-25"));
        backInBlackAlbum.setTitle("Back In Black");
        
        
        Song shootToThrillSong = new Song();
        shootToThrillSong.setTitle("Shoot To Thrill");
        shootToThrillSong.setAlbum(backInBlackAlbum);      
        shootToThrillSong.setGenre(hardRock);
        shootToThrillSong.setMusician(acdc);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        
        model.addAttribute("title", "Music Library Project");
        model.addAttribute("songs", songFacade.findAll());
        model.addAttribute("single", shootToThrillSong);
        return "home/home";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        Genre hardRock = new Genre();
        hardRock.setTitle("Hard Rock");
        hardRock.setYearOfOrigin(1970); 
        
        Musician acdc = new Musician();
        acdc.setRealName("Brian Johnson");
        acdc.setArtistName("AC/DC");
        acdc.setDateOfBirth(Date.valueOf("1945-10-15"));
        
        
        Album backInBlackAlbum = new Album();
        backInBlackAlbum.setMusician(acdc);
        backInBlackAlbum.setReleaseDate(Date.valueOf("1980-7-25"));
        backInBlackAlbum.setTitle("Back In Black");
        
        
        Song shootToThrillSong = new Song();
        shootToThrillSong.setTitle("Shoot To Thrill");
        shootToThrillSong.setAlbum(backInBlackAlbum);      
        shootToThrillSong.setGenre(hardRock);
        shootToThrillSong.setMusician(acdc);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        
        model.addAttribute("title", "Music Library Project");
        model.addAttribute("songs", songFacade.findAll());
        model.addAttribute("single", shootToThrillSong);
        return "home";
    }
}
