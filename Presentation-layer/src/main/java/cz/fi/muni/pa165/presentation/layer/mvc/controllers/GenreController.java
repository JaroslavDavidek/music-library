/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import cz.fi.muni.pa165.api.layer.dto.AlbumSearchDTO;
import cz.fi.muni.pa165.api.layer.dto.GenreDTO;
import cz.fi.muni.pa165.api.layer.dto.GenreSearchDTO;
import cz.fi.muni.pa165.api.layer.facade.AlbumFacade;
import cz.fi.muni.pa165.api.layer.facade.GenreFacade;
import cz.fi.muni.pa165.api.layer.facade.MusicianFacade;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.service.layer.facade.AlbumFacadeImplementation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
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
@RequestMapping("/genre")
public class GenreController {
    
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
        model.addAttribute("genreDTO", new GenreDTO());
        return "genre/create";
    }

    /**
     * POST method of create will submit the data, validate it, and create new
     * song
     *
     * @param formBean
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @param uriBuilder
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("genreDTO") GenreDTO formBean,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            return "/genre/create";
        }

        Long createdID = genreFacade.createGenre(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Succesfully created " + formBean.getTitle() + " with id " + createdID);
        redirectAttributes.addFlashAttribute("alert_success", "Album " + createdID + " was created");
        return "redirect:" + uriBuilder.path("/genre/listAsAdmin").toUriString();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "genre/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("allGenres", genreFacade.findAll());
        return "genre/list";
    }

    @RequestMapping(value = "/listAsAdmin", method = RequestMethod.GET)
    public String listAsAdmin(Model model) {

        model.addAttribute("allGenres", genreFacade.findAll());
        return "genre/listAsAdmin";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {

        GenreDTO found = genreFacade.findGenreByID(id);
        if (found != null) {
            genreFacade.deleteGenre(id);
            redirectAttributes.addFlashAttribute("alert_success", "Genre with title: " + found.getTitle() + " was deleted.");
            return "redirect:" + uriBuilder.path("/genre/listAsAdmin").toUriString();
        }

        return "redirect:" + uriBuilder.path("/genre/listAsAdmin").toUriString();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {

        model.addAttribute("genre", genreFacade.findGenreByID(id));
        return "genre/detail";
    }

    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.GET)
    public String detailAsAdmin(@PathVariable long id, Model model) {

        GenreDTO stored = genreFacade.findGenreByID(id);
        GenreDTO update = new GenreDTO();
        update.setTitle(stored.getTitle());
        update.setYearOfOrigin(stored.getYearOfOrigin());

        model.addAttribute("genreDetail", update);
        model.addAttribute("id", id);
        return "genre/detailAsAdmin";
    }

    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("genreDetail") GenreDTO formBean, BindingResult bindingResult, @PathVariable long id,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors()) {
            return "redirect:" + uriComponentsBuilder.path("/genre/detailAsAdmin/{id}").buildAndExpand(id).encode().toUriString();
        }
        
        genreFacade.updateTitle(id, formBean.getTitle());
        genreFacade.updateYearOfOrigin(id, formBean.getYearOfOrigin());

        redirectAttributes.addFlashAttribute("alert_success", "Genre " + formBean.getTitle() + " updated");
        return "redirect:" + uriComponentsBuilder.path("/genre/listAsAdmin").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = {"/find"}, method = RequestMethod.GET)
    public String find(Model model) {
        model.addAttribute("searchDTO", new GenreSearchDTO());
        return "genre/find";
    }

    @RequestMapping(value = {"/findByID"}, method = RequestMethod.POST)
    public String findByID(@ModelAttribute("searchDTO") GenreSearchDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", new GenreSearchDTO());
            return "genre/find";
        }

        List<GenreDTO> foundGenres = new ArrayList<GenreDTO>();
        try {
            GenreDTO found = genreFacade.findGenreByID(formBean.getGenreId());
            foundGenres.add(found);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        model.addAttribute("allGenres", foundGenres);
        return "genre/list";
    }

    @RequestMapping(value = {"/findByTitle"}, method = RequestMethod.POST)
    public String findByTitle(@ModelAttribute("searchDTO") GenreSearchDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", new GenreSearchDTO());
            return "genre/find";
        }

        List<GenreDTO> foundGenres = new ArrayList<GenreDTO>();
        try {
            GenreDTO found = genreFacade.findGenreByTitle(formBean.getTitle());
            foundGenres.add(found);
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        model.addAttribute("allGenres", foundGenres);
        return "genre/list";
    }

    @RequestMapping(value = {"/findByYearRange"}, method = RequestMethod.POST)
    public String findByMusician(@ModelAttribute("searchDTO") GenreSearchDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", new GenreSearchDTO());
        }

        model.addAttribute("allGenres", genreFacade.findAllGenresInYearRange(formBean.getFromYear(), formBean.getToYear()));
        return "genre/list";
    }

    @RequestMapping(value = {"/findOrdered"}, method = RequestMethod.POST)
    public String findByYearRange(@ModelAttribute("searchDTO") GenreSearchDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", new GenreSearchDTO());
            return "genre/find";
        }

        model.addAttribute("allGenres", genreFacade.findAllGenresByYearOfOriginOrdered(formBean.isSortASC()));
        return "genre/list";
    }
}
