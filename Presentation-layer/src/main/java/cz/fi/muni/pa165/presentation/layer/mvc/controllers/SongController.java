package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private SongFacade songFacade;
    
    private final String onSuccess = "TBA";
    private final String onFailure = "TBA";
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("songCreate") SongDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        if (bindingResult.hasErrors()) {
            return "song/new";
        }

        Long createdSongId = songFacade.createSong(formBean);
        redirectAttributes.addFlashAttribute(onSuccess, "Song with id: " + createdSongId + " was created");
        return "redirect:" + uriBuilder.path("/song/list").toUriString();
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSong(Model model) {
        
        model.addAttribute("songCreate", new SongDTO());
        return "song/new";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        model.addAttribute("songs", songFacade.findAll());
        return "song/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        
        SongDTO foundSong = songFacade.findSongByID(id);
        if(foundSong != null)
        {
            songFacade.deleteSong(id);
            redirectAttributes.addFlashAttribute(onSuccess, "Song with title: " + foundSong.getTitle() + " was deleted.");
            return "redirect:" + uriBuilder.path("/song/list").toUriString();
        }
        return "TBA";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {

        model.addAttribute("song", songFacade.findSongByID(id));
        return "song/view";
    }
}
