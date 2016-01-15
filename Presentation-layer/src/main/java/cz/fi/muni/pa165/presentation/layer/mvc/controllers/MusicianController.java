
package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.dto.MusicianDTO;
import cz.fi.muni.pa165.api.layer.dto.MusicianSearchDTO;
import cz.fi.muni.pa165.api.layer.facade.MusicianFacade;
import java.util.ArrayList;
import java.util.List;
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
 * @author Jergus Fasanek
 */
@Controller
@RequestMapping("/musician")
public class MusicianController {
    
    @Autowired
    private MusicianFacade musicianFacade;
    
    /**
     * GET method of create will set up a new form 
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("musicianDTO", new MusicianDTO());      
        return "musician/create";
    }
    
    /**
     * POST method of create will submit the data, validate it, and create new musician
     * 
     * @param formBean
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @param uriBuilder
     * @return 
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("musicianDTO") MusicianDTO formBean,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder){
        if (bindingResult.hasErrors()) {
            return "/musician/create";
        }
        Long createdID = musicianFacade.createMusician(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Succesfully created " + formBean.getArtistName()+ " with id " + createdID);
        redirectAttributes.addFlashAttribute("alert_success", "Musician " + createdID + " was created");
        
        return "redirect:" + uriBuilder.path("/musician/listAsAdmin").toUriString();
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        
        return "musician/index";
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        
        model.addAttribute("allMusicians", musicianFacade.findAll());
        return "musician/list";
    }
    
    @RequestMapping(value = "/listAsAdmin", method = RequestMethod.GET)
    public String listAsAdmin(Model model) {
        
        model.addAttribute("allMusicians", musicianFacade.findAll());
        return "musician/listAsAdmin";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        
        MusicianDTO foundMusician = musicianFacade.findMusicianByID(id);
        if(foundMusician != null)
        {
            musicianFacade.deleteMusician(id);
            redirectAttributes.addFlashAttribute("alert_success", "Musician " + foundMusician.getArtistName()+ " was deleted.");
            return "redirect:" + uriBuilder.path("/musician/listAsAdmin").toUriString();
        }

        return "redirect:" + uriBuilder.path("/musician/listAsAdmin").toUriString();
    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {

        model.addAttribute("musician", musicianFacade.findMusicianByID(id));
        return "musician/detail";
    }
    
    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.GET)
    public String detailAsAdmin(@PathVariable long id, Model model) {

        MusicianDTO stored = musicianFacade.findMusicianByID(id);
        MusicianDTO update = new MusicianDTO();
        update.setRealName(stored.getRealName());
        update.setArtistName(stored.getArtistName());
        update.setDateOfBirth(stored.getDateOfBirth());

        model.addAttribute("musicianDetail", update);
        model.addAttribute("id", id);
        return "musician/detailAsAdmin";
    }
    
    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("musicianDetail") MusicianDTO formBean, BindingResult bindingResult, @PathVariable long id,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors()) {
            return "redirect:" + uriComponentsBuilder.path("/musician/detailAsAdmin/{id}").buildAndExpand(id).encode().toUriString();
        }
        
        musicianFacade.updateRealName(id, formBean.getRealName());
        musicianFacade.updateArtistName(id, formBean.getArtistName());
        musicianFacade.updateDateOfBirth(id, formBean.getDateOfBirth());

        redirectAttributes.addFlashAttribute("alert_success", "Musician " + formBean.getArtistName()+ " updated");
        return "redirect:" + uriComponentsBuilder.path("/musician/listAsAdmin").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = {"/find"}, method = RequestMethod.GET)
    public String find(Model model) {
        model.addAttribute("searchDTO", new MusicianSearchDTO());
        return "musician/find";
    }

    @RequestMapping(value = {"/findByID"}, method = RequestMethod.POST)
    public String findByID(@ModelAttribute("searchDTO") MusicianSearchDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", new MusicianSearchDTO());
            return "musician/find";
        }

        List<MusicianDTO> foundMusicians = new ArrayList<MusicianDTO>();
        try {
            MusicianDTO found = musicianFacade.findMusicianByID(formBean.getMusicianId());
            if(found != null){
                foundMusicians.add(found);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("allMusicians", foundMusicians);
            return "musician/list";
        }
    }
    
    @RequestMapping(value = {"/findByRealName"}, method = RequestMethod.POST)
    public String findByRealName(@ModelAttribute("searchDTO") MusicianSearchDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", new MusicianSearchDTO());
            return "musician/find";
        }

        List<MusicianDTO> foundMusicians = new ArrayList<MusicianDTO>();
        try {
            MusicianDTO found = musicianFacade.findMusicianByRealName(formBean.getRealName());
            if(found != null){
                foundMusicians.add(found);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("allMusicians", foundMusicians);
            return "musician/list";
        }
    }
    
    @RequestMapping(value = {"/findByArtistName"}, method = RequestMethod.POST)
    public String findByArtistName(@ModelAttribute("searchDTO") MusicianSearchDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", new MusicianSearchDTO());
            return "musician/find";
        }

        List<MusicianDTO> foundMusicians = new ArrayList<MusicianDTO>();
        try {
            MusicianDTO found = musicianFacade.findMusicianByArtistName(formBean.getArtistName());
            if(found != null){
                foundMusicians.add(found);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("allMusicians", foundMusicians);
            return "musician/list";
        }
    }
    
    @RequestMapping(value = {"/findByYearRange"}, method = RequestMethod.POST)
    public String findByMusician(@ModelAttribute("searchDTO") MusicianSearchDTO formBean, BindingResult bindingResult,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("searchDTO", new MusicianSearchDTO());
        }

        model.addAttribute("allMusicians", musicianFacade.findAllMusiciansInYearRange(formBean.getFromYear(), formBean.getToYear()));
        return "musician/list";
    }
}
