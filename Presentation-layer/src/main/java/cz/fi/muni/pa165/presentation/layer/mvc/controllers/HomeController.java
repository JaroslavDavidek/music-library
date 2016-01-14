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
        
        return "home/home";
    }
}
