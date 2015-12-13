/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import cz.fi.muni.pa165.api.layer.facade.AlbumFacade;
import cz.fi.muni.pa165.api.layer.facade.GenreFacade;
import cz.fi.muni.pa165.api.layer.facade.MusicianFacade;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Peter Franek
 */
@Controller
@RequestMapping("/album")
public class AlbumController {
    
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
        model.addAttribute("albumDTO", new AlbumCreateDTO());      
        model.addAttribute("albums", albumFacade.findAll());
        model.addAttribute("musicians", musicianFacade.findAll());
        return "album/create";
    }
    
    /**
     * POST method of create will submit the data, validate it, and create new song
     * 
     * @param formBean
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @param uriBuilder
     * @return 
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("albumDTO") AlbumCreateDTO formBean,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "/album/create";
        }
        
        Long createdAlbumID = albumFacade.createAlbum(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Succesfully created " + formBean.getTitle() + " with id " + createdAlbumID);
        
        redirectAttributes.addFlashAttribute("alert_success", "Album " + createdAlbumID + " was created");
        return "redirect:" + uriBuilder.path("/album/listAsAdmin").toUriString();
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {    
        return "album/index";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        model.addAttribute("allAlbums", albumFacade.findAll());
        return "album/list";
    }
    
    @RequestMapping(value = "/listAsAdmin", method = RequestMethod.GET)
    public String listAsAdmin(Model model) {
        
        model.addAttribute("allAlbums", albumFacade.findAll());
        return "album/listAsAdmin";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        
        AlbumDTO foundAlbum = albumFacade.findById(id);;
        if(foundAlbum != null)
        {
            albumFacade.deleteAlbum(id);
            redirectAttributes.addFlashAttribute("alert_success", "Album with title: " + foundAlbum.getTitle() + " was deleted.");
            return "redirect:" + uriBuilder.path("/album/listAsAdmin").toUriString();
        }

        return "redirect:" + uriBuilder.path("/album/listAsAdmin").toUriString();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {

        model.addAttribute("album", albumFacade.findById(id));
        return "album/detail";
    }
    
    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.GET)
    public String detailAsAdmin(@PathVariable long id, Model model) {

        AlbumDTO storedAlbum = albumFacade.findById(id);
        AlbumCreateDTO albumToUpdate = new AlbumCreateDTO();
        albumToUpdate.setTitle(storedAlbum.getTitle());
        albumToUpdate.setReleaseDate(storedAlbum.getReleaseDate().toString());
        albumToUpdate.setCommentary(storedAlbum.getCommentary());
        albumToUpdate.setCover(storedAlbum.getCover());
        albumToUpdate.setMusicianId(storedAlbum.getMusician().getId());
        albumToUpdate.setSongs(storedAlbum.getSongs());
        
        model.addAttribute("albumDetail", albumToUpdate);
        model.addAttribute("musicians", musicianFacade.findAll());
        model.addAttribute("id", id);
        return "album/detailAsAdmin";
    }
 
    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("songDetail") AlbumCreateDTO formBean, BindingResult bindingResult, @PathVariable long id,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
        
        if (bindingResult.hasErrors()) {
            return "redirect:" + uriComponentsBuilder.path("/album/detailAsAdmin/{id}").buildAndExpand(id).encode().toUriString();
        }      
        
        albumFacade.updateAlbumTitle(id, formBean.getTitle());
        //albumFacade.updateAlbumReleaseDate(id, formBean.getReleaseDate());

        redirectAttributes.addFlashAttribute("alert_success", "Album " + formBean.getTitle() + " updated");
        return "redirect:" + uriComponentsBuilder.path("/album/listAsAdmin").buildAndExpand(id).encode().toUriString();
    }
    
}
