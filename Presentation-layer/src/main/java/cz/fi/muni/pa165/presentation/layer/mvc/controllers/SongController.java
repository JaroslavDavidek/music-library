package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.dto.SongCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.api.layer.facade.AlbumFacade;
import cz.fi.muni.pa165.api.layer.facade.GenreFacade;
import cz.fi.muni.pa165.api.layer.facade.MusicianFacade;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.service.layer.facade.SongFacadeImplementation;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author JaroslavDavidek
 */
@Controller
@RequestMapping("/song")
public class SongController {
    
    @Inject
    private MessageSource messageSource; 
    
    @Autowired
    private SongFacade songFacade;
    
    @Autowired
    private MusicianFacade musicianFacade;
    
    @Autowired
    private GenreFacade genreFacade;
    
    @Autowired
    private AlbumFacade albumFacade;

    /**
     * GET method of create will set up a new form 
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("songCreateDTO", new SongCreateDTO());      
        model.addAttribute("albums", albumFacade.findAll());
        model.addAttribute("musicians", musicianFacade.findAll());
        model.addAttribute("genres", genreFacade.findAll());
        return "song/create";
    }
    
    /**
     * POST method of create will submit the data, validate it, and create new song
     * 
     * @param formBean
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @param uriComponentsBuilder
     * @return 
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("songCreateDTO") SongCreateDTO formBean,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "/song/create";
        }
        
        Long createdSongID = songFacade.createSong(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Succesfully created " + formBean.getTitle() + " with id " + createdSongID);
        
        redirectAttributes.addFlashAttribute("alert_success", "Category " + createdSongID + " was created");
        return "redirect:" + uriBuilder.path("/song/listAsAdmin").toUriString();
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        
        return "song/index";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        model.addAttribute("allSongs", songFacade.findAll());
        return "song/list";
    }
    
    @RequestMapping(value = "/listAsAdmin", method = RequestMethod.GET)
    public String listAsAdmin(Model model) {
        
        model.addAttribute("allSongs", songFacade.findAll());
        return "song/listAsAdmin";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        
        SongDTO foundSong = songFacade.findSongByID(id);
        if(foundSong != null)
        {
            songFacade.deleteSong(id);
            redirectAttributes.addFlashAttribute("alert_success", "Song with title: " + foundSong.getTitle() + " was deleted.");
            return "redirect:" + uriBuilder.path("/song/list").toUriString();
        }

        return "redirect:" + uriBuilder.path("/song/list").toUriString();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {

        model.addAttribute("song", songFacade.findSongByID(id));
        return "song/detail";
    }
    
    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.GET)
    public String detailAsAdmin(@PathVariable long id, Model model) {

        model.addAttribute("songDetail", songFacade.findSongByID(id));
        return "song/detailAsAdmin";
    }
    
}
