package cz.fi.muni.pa165.presentation.layer.mvc.controllers;

import cz.fi.muni.pa165.api.layer.dto.SongCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.api.layer.dto.SongSearchDTO;
import cz.fi.muni.pa165.api.layer.facade.AlbumFacade;
import cz.fi.muni.pa165.api.layer.facade.GenreFacade;
import cz.fi.muni.pa165.api.layer.facade.MusicianFacade;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.service.layer.facade.SongFacadeImplementation;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
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
     * @param uriBuilder
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
        
        redirectAttributes.addFlashAttribute("alert_success", "Song " + createdSongID + " was created");
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
            return "redirect:" + uriBuilder.path("/song/listAsAdmin").toUriString();
        }

        return "redirect:" + uriBuilder.path("/song/listAsAdmin").toUriString();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {

        model.addAttribute("song", songFacade.findSongByID(id));
        return "song/detail";
    }
    
    @RequestMapping(value = "/lastFM/{id}", method = RequestMethod.GET)
    public String lastFM(@PathVariable long id, Model model) {

        String query = songFacade.createLastFMSearchQuery(id);
        return "redirect:" + query;
    }
    
    @RequestMapping(value = "/youtube/{id}", method = RequestMethod.GET)
    public String youtube(@PathVariable long id, Model model) {

        String query = songFacade.createYoutubeSearchQuery(id);
        return "redirect:" + query;
    }
    
    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.GET)
    public String detailAsAdmin(@PathVariable long id, Model model) {

        SongDTO storedSong = songFacade.findSongByID(id);
        SongCreateDTO songToUpdate = new SongCreateDTO();
        songToUpdate.setTitle(storedSong.getTitle());
        songToUpdate.setCommentary(storedSong.getCommentary());
        songToUpdate.setBitrate(storedSong.getBitrate());
        songToUpdate.setAlbumPosition(storedSong.getAlbumPosition());
        songToUpdate.setAlbumId(storedSong.getAlbum().getId());
        songToUpdate.setMusicianId(storedSong.getMusician().getId());
        songToUpdate.setGenreId(storedSong.getGenre().getId());
        
        model.addAttribute("songDetail", songToUpdate);
        model.addAttribute("albums", albumFacade.findAll());
        model.addAttribute("musicians", musicianFacade.findAll());
        model.addAttribute("genres", genreFacade.findAll());
        model.addAttribute("id", id);
        return "song/detailAsAdmin";
    }
 
    @RequestMapping(value = "/detailAsAdmin/{id}", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("songDetail") SongCreateDTO formBean, BindingResult bindingResult, @PathVariable long id,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
        
        if (bindingResult.hasErrors()) {
            return "redirect:" + uriComponentsBuilder.path("/song/detailAsAdmin/{id}").buildAndExpand(id).encode().toUriString();
        }      
        songFacade.updateAlbum(id, formBean.getAlbumId());
        songFacade.updateGenre(id, formBean.getGenreId());
        songFacade.updateMusician(id, formBean.getMusicianId());
        songFacade.updateTitle(id, formBean.getTitle());
        songFacade.updateBitrate(id, formBean.getBitrate());
        songFacade.updateAlbumPosition(id, formBean.getAlbumPosition());
        songFacade.updateCommentary(id, formBean.getCommentary());
        redirectAttributes.addFlashAttribute("alert_success", "Song " + formBean.getTitle() + " updated");
        return "redirect:" + uriComponentsBuilder.path("/song/listAsAdmin").buildAndExpand(id).encode().toUriString();
    }
    
    @RequestMapping(value = {"/find"}, method = RequestMethod.GET)
    public String find(Model model) {
        model.addAttribute("searchDTO", new SongSearchDTO());      
        model.addAttribute("albums", albumFacade.findAll());
        model.addAttribute("musicians", musicianFacade.findAll());
        model.addAttribute("genres", genreFacade.findAll());
        return "song/find";
    }
    
    @RequestMapping(value = {"/findByID"}, method = RequestMethod.POST)
    public String findByID(@ModelAttribute("searchDTO") SongSearchDTO formBean, BindingResult bindingResult, 
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
         if (bindingResult.hasErrors()) {   
            model.addAttribute("searchDTO", new SongSearchDTO());   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "song/find";
        }
        
        List<SongDTO> foundSongs = new ArrayList<SongDTO>();
        try {
            SongDTO foundSong = songFacade.findSongByID(formBean.getSongId());
            if(foundSong != null){
                foundSongs.add(foundSong);
            }
        } catch (NoResultException e) {
            e.printStackTrace();      
        } finally {
            model.addAttribute("allSongs", foundSongs); 
            return "song/list";
        }
    }
    
    @RequestMapping(value = {"/findByTitle"}, method = RequestMethod.POST)
    public String findByTitle(@ModelAttribute("searchDTO") SongSearchDTO formBean, BindingResult bindingResult, 
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
         if (bindingResult.hasErrors()) {   
            model.addAttribute("searchDTO", new SongSearchDTO());   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "song/find";
        }
         
        List<SongDTO> foundSongs = new ArrayList<SongDTO>(); 
        try {
            SongDTO foundSong = songFacade.findSongByTitle(formBean.getTitle());
            if(foundSong != null){
                foundSongs.add(foundSong);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            model.addAttribute("allSongs", foundSongs); 
            return "song/list";
        }
    }
    
    @RequestMapping(value = {"/findByMusician"}, method = RequestMethod.POST)
    public String findByMusician(@ModelAttribute("searchDTO") SongSearchDTO formBean, BindingResult bindingResult, 
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
         if (bindingResult.hasErrors()) {   
            model.addAttribute("searchDTO", new SongSearchDTO());   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "song/find";
        }
        
        model.addAttribute("allSongs", songFacade.findAllSongsByMusician(formBean.getMusicianId())); 
        return "song/list";
    }
    
    @RequestMapping(value = {"/findByGenre"}, method = RequestMethod.POST)
    public String findByGenre(@ModelAttribute("searchDTO") SongSearchDTO formBean, BindingResult bindingResult, 
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
         if (bindingResult.hasErrors()) {   
            model.addAttribute("searchDTO", new SongSearchDTO());   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "song/find";
        }
        
        model.addAttribute("allSongs", songFacade.findAllSongsByGenre(formBean.getGenreId())); 
        return "song/list";
    }
    
    @RequestMapping(value = {"/findByAlbum"}, method = RequestMethod.POST)
    public String findByAlbum(@ModelAttribute("searchDTO") SongSearchDTO formBean, BindingResult bindingResult, 
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
         if (bindingResult.hasErrors()) {   
            model.addAttribute("searchDTO", new SongSearchDTO());   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "song/find";
        }
        
        model.addAttribute("allSongs", songFacade.findAllSongsByAlbum(formBean.getAlbumId())); 
        return "song/list";
    }
    
    @RequestMapping(value = {"/findByMusicianAndReleaseYearRange"}, method = RequestMethod.POST)
    public String findByMusicianAndReleaseYearRange(@ModelAttribute("searchDTO") SongSearchDTO formBean, BindingResult bindingResult, 
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
         if (bindingResult.hasErrors()) {   
            model.addAttribute("searchDTO", new SongSearchDTO());   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "song/find";
        }
        
        model.addAttribute("allSongs", songFacade.findAllSongsByMusicianAndReleaseYearRange(formBean.getMusicianId(), formBean.getFromYear(), formBean.getToYear())); 
        return "song/list";
    }
    
    @RequestMapping(value = {"/findByAlbumOrdered"}, method = RequestMethod.POST)
    public String findByAlbumOrdered(@ModelAttribute("searchDTO") SongSearchDTO formBean, BindingResult bindingResult, 
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
         if (bindingResult.hasErrors()) {   
            model.addAttribute("searchDTO", new SongSearchDTO());   
            model.addAttribute("albums", albumFacade.findAll());
            model.addAttribute("musicians", musicianFacade.findAll());
            model.addAttribute("genres", genreFacade.findAll());
            return "song/find";
        }
        
        model.addAttribute("allSongs", songFacade.findAllSongsByAlbumOrdered(formBean.getAlbumId(), formBean.getSortASC())); 
        return "song/list";
    }
    
    
}
