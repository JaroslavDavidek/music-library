package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author JaroslavDavidek
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        
        return "admin/index";
    }
    
    @RequestMapping(value = "/songAdministration/index", method = RequestMethod.GET)
    public String songIndex(Model model) {
        
        return "admin/songAdministration/index";
    }
    
    @RequestMapping(value = "/albumAdministration/index", method = RequestMethod.GET)
    public String albumIndex(Model model) {
        
        return "admin/albumAdministration/index";
    }
    
    @RequestMapping(value = "/musicianAdministration/index", method = RequestMethod.GET)
    public String musicianIndex(Model model) {
        
        return "admin/musicianAdministration/index";
    }
    
    @RequestMapping(value = "/genreAdministration/index", method = RequestMethod.GET)
    public String genreIndex(Model model) {
        
        return "admin/genreAdministration/index";
    }
    
    
}
