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
    
    
}
