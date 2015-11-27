/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import cz.fi.muni.pa165.api.layer.dto.MusicianDTO;
import cz.fi.muni.pa165.api.layer.facade.AlbumFacade;
import cz.fi.muni.pa165.dao.AlbumDao;
import cz.fi.muni.pa165.dao.SongDao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
import cz.fi.muni.pa165.service.layer.service.AlbumService;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import cz.fi.muni.pa165.service.layer.service.SongService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Peter Franek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class AlbumFacadeImplementationTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private AlbumDao albumDao;
    
    @Mock
    private SongDao songDao;
    
    @Autowired
    @InjectMocks
    private AlbumService albumService;
    
    @Autowired
    private MappingService mappingService;
    
    @Autowired
    private AlbumFacade albumFacade;
    
    private Musician acdc;  
    
    private Genre progressiveRock;
    
    private Album backInBlackAlbum;
    
    private Song shootToThrillSong;
    
    private AlbumDTO albumDTO;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
      
        acdc = new Musician();
        acdc.setRealName("Brian Johnson");
        acdc.setArtistName("AC/DC");
        acdc.setDateOfBirth(Date.valueOf("1945-10-15"));

        backInBlackAlbum = new Album();
        backInBlackAlbum.setMusician(acdc);
        backInBlackAlbum.setReleaseDate(Date.valueOf("1980-7-25"));
        backInBlackAlbum.setTitle("Back In Black");
        
        shootToThrillSong = new Song();
        shootToThrillSong.setTitle("Shoot To Thrill");
        shootToThrillSong.setAlbum(backInBlackAlbum);      
        shootToThrillSong.setGenre(progressiveRock);
        shootToThrillSong.setMusician(acdc);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        
        albumDTO = new AlbumDTO();
        albumDTO.setTitle(backInBlackAlbum.getTitle());
        albumDTO.setReleaseDate(backInBlackAlbum.getReleaseDate());
        albumDTO.setMusician(new MusicianDTO());
        albumDTO.setCover(backInBlackAlbum.getCover());
       
        progressiveRock = new Genre();
        progressiveRock.setTitle("Progressive Rock");
        progressiveRock.setYearOfOrigin(1960);   
    }
    
    @Test
    public void testCreateAlbum() {       
        System.out.println("createAlbum");
        
        when(albumDao.create(any(Album.class))).thenReturn(true);
        when(albumDao.findById(any(Long.class))).thenReturn(backInBlackAlbum);
        Long createdID = albumFacade.createAlbum(albumDTO);
        assertEquals(null, createdID);  
    }

  
    @Test
    public void testDeleteAlbum() {       
        System.out.println("deleteAlbum");
        
        when(albumDao.findById(any(Long.class))).thenReturn(backInBlackAlbum);
        when(albumDao.delete(any(Album.class))).thenReturn(true);
        boolean expectedResult = albumFacade.deleteAlbum(1l);
        assertEquals(true, expectedResult);        
    }

    @Test
    public void testFindAll() {
        System.out.println("findAll");
    
        List<Album> expectedResult = new ArrayList<>();
        expectedResult.add(backInBlackAlbum);
        
        when(albumDao.findAll()).thenReturn(expectedResult);
        List<AlbumDTO> found = albumFacade.findAll();
        Assert.assertEquals(expectedResult.size(), found.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), AlbumDTO.class), found.get(i));
        }
    }
    
    @Test
    public void testFindAlbumByID() {
        System.out.println("findAlbumByID");
        
        when(albumDao.findById(any(Long.class))).thenReturn(backInBlackAlbum);
        AlbumDTO result = albumFacade.findById(1l);
        assertEquals(mappingService.mapTo(backInBlackAlbum, AlbumDTO.class), result);
    }
    
    @Test
    public void testFindAlbumByTitle() {
        System.out.println("findAlbumByTitle");
        
        when(albumDao.findByTitle(any(String.class))).thenReturn(backInBlackAlbum);
        AlbumDTO result = albumFacade.findByTitle(backInBlackAlbum.getTitle());
        assertEquals(mappingService.mapTo(backInBlackAlbum, AlbumDTO.class), result);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void addSong() {
        System.out.println("addSong");
        when(albumDao.addSong(backInBlackAlbum, shootToThrillSong)).thenReturn(backInBlackAlbum);
        AlbumDTO result = albumFacade.addSong(backInBlackAlbum.getId(), shootToThrillSong.getId());
        assertEquals(mappingService.mapTo(backInBlackAlbum, AlbumDTO.class), result);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void removeSong() {
        System.out.println("removeSong");
        when(albumDao.removeSong(backInBlackAlbum, shootToThrillSong)).thenReturn(backInBlackAlbum);
        AlbumDTO result = albumFacade.removeSong(backInBlackAlbum.getId(), shootToThrillSong.getId());
        assertEquals(mappingService.mapTo(backInBlackAlbum, AlbumDTO.class), result);
    }
    
    @Test
    public void updateTitle() {
        System.out.println("updateTitle");
        when(albumDao.update(backInBlackAlbum)).thenReturn(backInBlackAlbum);
        when(albumDao.findById(any(Long.class))).thenReturn(backInBlackAlbum);
        AlbumDTO result = albumFacade.updateAlbumTitle(1l, "new");
        assertEquals(mappingService.mapTo(backInBlackAlbum, AlbumDTO.class), result);
    }
    
    @Test
    public void updateReleaseDate() {
        System.out.println("updateRelease");
        when(albumDao.update(backInBlackAlbum)).thenReturn(backInBlackAlbum);
        when(albumDao.findById(any(Long.class))).thenReturn(backInBlackAlbum);
        AlbumDTO result = albumFacade.updateAlbumReleaseDate(1l, Date.valueOf("1945-10-15"));
        assertEquals(mappingService.mapTo(backInBlackAlbum, AlbumDTO.class), result);
    }
}
