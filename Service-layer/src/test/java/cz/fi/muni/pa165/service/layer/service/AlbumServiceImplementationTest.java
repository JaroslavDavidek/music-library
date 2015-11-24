/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.AlbumDao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.exception.InvalidParamDataAccessExpection;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
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
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Peter Franek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class AlbumServiceImplementationTest extends AbstractTransactionalTestNGSpringContextTests {
   
    @Mock
    private AlbumDao albumDao;
    
    @Autowired
    @InjectMocks
    private AlbumService albumService;
    
    private Album brothersInArmsAlbum;
    private Album onEveryStreetAlbum;
    
 
    private Musician direStraits;
    
    private Song shootToThrillSong;    
    private Song moneyForNothingSong;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Genre rock = new Genre();
        rock.setTitle("Hard Rock");
        rock.setYearOfOrigin(1950); 
        
        direStraits = new Musician();
        direStraits.setRealName("Mark Knopfler");
        direStraits.setArtistName("Dire Straits");
        direStraits.setDateOfBirth(Date.valueOf("1949-12-8"));
        
        brothersInArmsAlbum = new Album();
        brothersInArmsAlbum.setMusician(direStraits);
        brothersInArmsAlbum.setReleaseDate(Date.valueOf("1985-6-13"));
        brothersInArmsAlbum.setTitle("Brothers in Arms");
        
        onEveryStreetAlbum = new Album();
        onEveryStreetAlbum.setMusician(direStraits);
        onEveryStreetAlbum.setReleaseDate(Date.valueOf("1991-10-11"));
        onEveryStreetAlbum.setTitle("On Every Street");
        
        shootToThrillSong = new Song();
        shootToThrillSong.setTitle("Shoot To Thrill");
        shootToThrillSong.setAlbum(brothersInArmsAlbum);      
        shootToThrillSong.setGenre(rock);
        shootToThrillSong.setMusician(direStraits);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        
        moneyForNothingSong = new Song();
        moneyForNothingSong.setTitle("Money For Nothing");
        moneyForNothingSong.setAlbum(brothersInArmsAlbum);      
        moneyForNothingSong.setGenre(rock);
        moneyForNothingSong.setMusician(direStraits);
        moneyForNothingSong.setAlbumPosition(2);
        moneyForNothingSong.setBitrate(320);     
    }
    
    @Test
    public void testCreateAlbum1() {
        System.out.println("createAlbum1");
        
        when(albumDao.create(any(Album.class))).thenReturn(true);
        Album album = albumService.createAlbum(brothersInArmsAlbum);       
        assertEquals(album, brothersInArmsAlbum);
    }
    
    @Test
    public void testCreateAlbum2() {
        System.out.println("createAlbum2");
        
        when(albumDao.create(any(Album.class))).thenReturn(false);
        Album album = albumService.createAlbum(brothersInArmsAlbum);       
        assertEquals(null, album);
    }
   
    @Test
    public void testDeleteAlbum1() {
        System.out.println("deleteAlbum1");
        
        when(albumDao.delete(any(Album.class))).thenReturn(true);
        boolean result = albumService.deleteAlbum(brothersInArmsAlbum);
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteAlbum2() {
        System.out.println("deleteAlbum2");
        
        when(albumDao.delete(any(Album.class))).thenReturn(false);
        boolean result = albumService.deleteAlbum(brothersInArmsAlbum);
        assertEquals(false, result);
    }
    
    @Test
    public void testUpdateTitle1() {
        System.out.println("updateTitle1");
        
        String expectedTitle = "Brothers in Arms";
        Album brothersInArmsAlbum2 = brothersInArmsAlbum;
        brothersInArmsAlbum2.setTitle(expectedTitle);
        when(albumDao.update(brothersInArmsAlbum)).thenReturn(brothersInArmsAlbum2);
        Album updatedResult = albumService.updateAlbumTitle(brothersInArmsAlbum, expectedTitle);
        assertEquals(expectedTitle, updatedResult.getTitle());
    }
    
    @Test
    public void testUpdateTitle2() {
        System.out.println("updateTitle2");
        
        String expectedTitle = null;
        Album brothersInArmsAlbum2 = brothersInArmsAlbum;
        brothersInArmsAlbum2.setTitle(expectedTitle);
        when(albumDao.update(brothersInArmsAlbum)).thenReturn(brothersInArmsAlbum2);
        Album updatedResult = albumService.updateAlbumTitle(brothersInArmsAlbum, expectedTitle);
        assertEquals(null, updatedResult);
    }
    
    @Test
    public void testUpdateReleaseDate1() {
        System.out.println("updateDate1");
        
        Date date = Date.valueOf("1991-10-11");
        Album brothersInArmsAlbum2 = brothersInArmsAlbum;
        brothersInArmsAlbum2.setReleaseDate(date);
        when(albumDao.update(brothersInArmsAlbum)).thenReturn(brothersInArmsAlbum2);
        Album updatedResult = albumService.updateAlbumReleaseDate(brothersInArmsAlbum, date);
        assertEquals(date, updatedResult.getReleaseDate());
    }
    
    @Test
    public void testUpdateReleaseDate2() {
        System.out.println("updateDate2");
        
        Date date = null;
        Album brothersInArmsAlbum2 = brothersInArmsAlbum;
        brothersInArmsAlbum2.setReleaseDate(date);
        when(albumDao.update(brothersInArmsAlbum)).thenReturn(brothersInArmsAlbum2);
        Album updatedResult = albumService.updateAlbumReleaseDate(brothersInArmsAlbum, date);
        assertEquals(null, updatedResult);
    }
    
    @Test
    public void testFindAlbumByID1() {
        System.out.println("findAlbumByID1");
      
        when(albumDao.findById(any(Long.class))).thenReturn(brothersInArmsAlbum);
        Album result = albumService.findById(1L);
        assertEquals(brothersInArmsAlbum, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindAlbumByID2() {
        System.out.println("findAlbumByID2");
      
        when(albumDao.findById(any(Long.class))).thenThrow(new InvalidParamDataAccessExpection("AlbumDao - find by id - wrong id parameter"));
        Album result = albumDao.findById(-1l);
        assertEquals(null, result);
    }
    
    @Test
    public void testFindAlbumByTitle1() {
        System.out.println("findAlbumByTitle1");
      
        when(albumDao.findByTitle(any(String.class))).thenReturn(brothersInArmsAlbum);
        Album result = albumService.findByTitle(brothersInArmsAlbum.getTitle());
        assertEquals(brothersInArmsAlbum, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindAlbumByTitle2() {
        System.out.println("findAlbumByTitle2");
      
        when(albumDao.findByTitle(null)).thenThrow(new InvalidParamDataAccessExpection("AlbumDao - find by title - title must not be null"));
        Album result = albumService.findByTitle(null);
        assertEquals(null, result);
    }
    
    @Test
    public void testFindAll() {
        System.out.println("findAllAlbums");
      
        List<Album> expectedResult = new ArrayList<>();
        expectedResult.add(brothersInArmsAlbum);
        expectedResult.add(onEveryStreetAlbum);
        when(albumDao.findAll()).thenReturn(expectedResult);
        List<Album> foundAlbums = albumService.findAll();
        Assert.assertEquals(expectedResult.size(), foundAlbums.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundAlbums.get(i));
        }
    }
    
    @Test
    public void testAddSong1() {
        System.out.println("addSong1");
       
        when(albumDao.addSong(brothersInArmsAlbum, shootToThrillSong)).thenReturn(brothersInArmsAlbum);
        Album result = albumService.addSong(brothersInArmsAlbum, shootToThrillSong);
        assertEquals(brothersInArmsAlbum, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testAddSong2() {
        System.out.println("addSong2");
       
        when(albumDao.addSong(null, null)).thenThrow(new InvalidParamDataAccessExpection("AlbumDao - add song - album nor song must not be null"));
        Album result = albumService.addSong(null, null);
        assertEquals(null, result);
    }
    
    @Test
    public void testRemoveSong1() {
        System.out.println("removeSong1");
       
        when(albumDao.removeSong(brothersInArmsAlbum, shootToThrillSong)).thenReturn(brothersInArmsAlbum);
        Album result = albumService.removeSong(brothersInArmsAlbum, shootToThrillSong);
        assertEquals(brothersInArmsAlbum, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testRemoveSong2() {
        System.out.println("removeSong2");
       
        when(albumDao.removeSong(null, null)).thenThrow(new InvalidParamDataAccessExpection("AlbumDao - remove song - album nor song must not be null"));
        Album result = albumService.removeSong(null, null);
        assertEquals(null, result);
    }
    
    @Test
    public void testFindAllAlbumsFromYears1() {
        System.out.println("findAllAlbumsFromYears1");
        
        List<Album> list = new ArrayList<>();
        list.add(onEveryStreetAlbum);
        list.add(brothersInArmsAlbum);
        when(albumDao.findAll()).thenReturn(list);
        List<Album> result = albumService.findAllAlbumsFromYears(1980, 2000);
        assertEquals(result.size(), 2);
        for (int i = 0; i<list.size(); i++) {
            assertEquals(list.get(i), result.get(i));
        }
    }
    
    @Test
    public void testFindAllAlbumsFromYears2() {
        System.out.println("findAllAlbumsFromYears2");
        
        List<Album> list = new ArrayList<>();
        list.add(onEveryStreetAlbum);
        list.add(brothersInArmsAlbum);
        when(albumDao.findAll()).thenReturn(list);
        List<Album> result = albumService.findAllAlbumsFromYears(2000, 1900);
        assertEquals(result.size(), 0);
    }
    
    @Test
    public void testFindAllAlbumsFromYears3() {
        System.out.println("findAllAlbumsFromYears3");
        
        List<Album> list = new ArrayList<>();
        list.add(onEveryStreetAlbum);
        list.add(brothersInArmsAlbum);
        when(albumDao.findAll()).thenReturn(list);
        List<Album> result = albumService.findAllAlbumsFromYears(1990, 2000);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), onEveryStreetAlbum);
    }
    
    @Test
    public void testFindAllAlbumsFromYears4() {
        System.out.println("findAllAlbumsFromYears4");
        
        List<Album> list = new ArrayList<>();
        list.add(onEveryStreetAlbum);
        list.add(brothersInArmsAlbum);
        when(albumDao.findAll()).thenReturn(list);
        List<Album> result = albumService.findAllAlbumsFromYears(1900, 1940);
        assertEquals(result.size(), 0);
    }
    
    @Test
    public void testFindAllAlbumsOfMusician1() {
        System.out.println("findAllAlbumsOfMusician1");
        
        List<Album> list = new ArrayList<>();
        list.add(onEveryStreetAlbum);
        list.add(brothersInArmsAlbum);
        when(albumDao.findAll()).thenReturn(list);
        List<Album> result = albumService.findAllAlbumsOfMusician(direStraits);
        assertEquals(result.size(), 2);
        for (int i = 0; i<list.size(); i++) {
            assertEquals(list.get(i), result.get(i));
        }
    }
    
    @Test
    public void testFindAllAlbumsOfMusician2() {
        System.out.println("findAllAlbumsOfMusician2");
        
        List<Album> list = new ArrayList<>();
        list.add(onEveryStreetAlbum);
        list.add(brothersInArmsAlbum);
        when(albumDao.findAll()).thenReturn(list);
        List<Album> result = albumService.findAllAlbumsOfMusician(null);
        assertEquals(result.size(), 0);
    }
}
