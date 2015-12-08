package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

/**
 *
 * @author JaroslavDavidek
 */
@Controller
public class HomeController {
    
    @Autowired
    private SongFacade songFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        
        
        model.addAttribute("songs", songFacade.findAll());
        return "song/list";
    }
}
