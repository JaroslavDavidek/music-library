/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.GenreDao;
import cz.fi.muni.pa165.dao.SongDao;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.exception.InvalidParamDataAccessExpection;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
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
 * @author Jergus Fasanek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class GenreServiceImplementationTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private GenreDao genreDao;
    
    @Mock
    private SongDao songDao;
    
    @Autowired
    @InjectMocks
    private GenreService genreService;
    
    private Genre rock;
    private Genre spaceRock;
    private Genre folkMetal;
 
    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        
        rock = new Genre();
        rock.setTitle("Hard Rock");
        rock.setYearOfOrigin(1950); 
        
        spaceRock = new Genre();
        spaceRock.setTitle("Space Rock");
        spaceRock.setYearOfOrigin(1990); 
        
        folkMetal = new Genre();
        folkMetal.setTitle("Folk Metal");
        folkMetal.setYearOfOrigin(1980);
    }
    
    
    /**
     * Test of createGenre method, of class GenreServiceImplementation.
     */
    @Test
    public void testCreateGenre1() {
        System.out.println("createGenre1");
        
        when(genreDao.create(any(Genre.class))).thenReturn(true);
        Genre createdGenre = genreService.createGenre(rock);       
        assertEquals(rock, createdGenre);
    }
    
    @Test
    public void testCreateGenre2() {
        System.out.println("createGenre2");
        
        when(genreDao.create(any(Genre.class))).thenReturn(false);
        Genre createdGenre = genreService.createGenre(rock);       
        assertEquals(null, createdGenre);
    }
   
    @Test
    public void testDeleteGenre1() {
        System.out.println("deleteGenre1");
        
        when(genreDao.delete(any(Genre.class))).thenReturn(true);
        when(songDao.findAllByGenre(any(Genre.class))).thenReturn(new ArrayList<Song>());
        boolean result = genreService.deleteGenre(spaceRock);
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteGenre2() {
        System.out.println("deleteGenre2");
        
        when(genreDao.delete(any(Genre.class))).thenReturn(false);
        when(songDao.findAllByGenre(any(Genre.class))).thenReturn(new ArrayList<Song>());
        boolean result = genreService.deleteGenre(rock);
        assertEquals(false, result);
    }

    /**
     * Test of updateTitle method, of class GenreServiceImplementation.
     */
    @Test
    public void testUpdateTitle1() {
        System.out.println("updateTitle1");
        
        String expectedTitle = "Psychadelic rock";
        Genre rock2 = rock;
        rock.setTitle(expectedTitle);
        when(genreDao.update(rock)).thenReturn(rock2);
        Genre updatedResult = genreService.updateTitle(rock, expectedTitle);
        assertEquals(expectedTitle, updatedResult.getTitle());
    }
    
    @Test
    public void testUpdateTitle2() {
        System.out.println("updateTitle2");
        
        String expectedTitle = null;
        Genre rock2 = rock;
        rock.setTitle(expectedTitle);
        when(genreDao.update(rock)).thenReturn(rock2);
        Genre updatedResult = genreService.updateTitle(rock, expectedTitle);
        assertEquals(null, updatedResult);
    }

    /**
     * Test of updateYearOfOrigin method, of class GenreServiceImplementation.
     */
    @Test
    public void testUpdateYearOfOrigin() {
        System.out.println("updateYearOfOrigin");
        
        int expectedYear = 9;
        Genre rock2 = rock;
        rock2.setYearOfOrigin(expectedYear);
        when(genreDao.update(rock)).thenReturn(rock2);
        Genre updatedResult = genreService.updateYearOfOrigin(rock, expectedYear);
        assertEquals(expectedYear, updatedResult.getYearOfOrigin());
    }

    /**
     * Test of findGenreByID method, of class GenreServiceImplementation.
     */
    @Test
    public void testFindGenreByID1() {
        System.out.println("findGenreByID1");
      
        when(genreDao.findById(any(Long.class))).thenReturn(rock);
        Genre result = genreService.findGenreByID(1L);
        assertEquals(rock, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindGenreByID2() {
        System.out.println("findGenreByID2");
      
        when(genreDao.findById(any(Long.class))).thenThrow(new InvalidParamDataAccessExpection("GenreDao - find by id - wrong id parameter"));
        Genre result = genreDao.findById(-1l);
        assertEquals(null, result);
    }
    
    @Test
    public void testFindGenreByTitle1() {
        System.out.println("findGenreByTitle1");
      
        when(genreDao.findByTitle(any(String.class))).thenReturn(rock);
        Genre result = genreService.findGenreByTitle(rock.getTitle());
        assertEquals(rock, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindGenreByTitle2() {
        System.out.println("findGenreByTitle2");
      
        when(genreDao.findByTitle(null)).thenThrow(new InvalidParamDataAccessExpection("GenreDao - find by title - title must not be null"));
        Genre result = genreService.findGenreByTitle(null);
        assertEquals(null, result);
    }
    
    @Test
    public void testFindAll() {
        System.out.println("findAllGenres");
      
        List<Genre> expectedResult = new ArrayList<>();
        expectedResult.add(rock);
        expectedResult.add(folkMetal);
        when(genreDao.findAll()).thenReturn(expectedResult);
        List<Genre> foundGenres = genreService.findAll();
        Assert.assertEquals(expectedResult.size(), foundGenres.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundGenres.get(i));
        }
    }
    
    @Test
    public void testFindAllGenresInYearRange1() {
        System.out.println("findAllGenresInYearRange1");
        
        List<Genre> list = new ArrayList<>();
        list.add(folkMetal);
        list.add(rock);
        when(genreDao.findAll()).thenReturn(list);
        List<Genre> result = genreService.findAllGenresInYearRange(1950, 2015);
        assertEquals(result.size(), 2);
        for (int i = 0; i<list.size(); i++) {
            assertEquals(list.get(i), result.get(i));
        }
    }
    
    @Test
    public void testFindAllGenresInYearRange2() {
        System.out.println("findAllGenresInYearRange2");
        
        List<Genre> list = new ArrayList<>();
        list.add(folkMetal);
        list.add(rock);
        when(genreDao.findAll()).thenReturn(list);
        List<Genre> result = genreService.findAllGenresInYearRange(2015, 1900);
        assertEquals(result.size(), 0);
    }
    
    @Test
    public void testFindAllGenresInYearRange3() {
        System.out.println("findAllGenresInYearRange3");
        
        List<Genre> list = new ArrayList<>();
        list.add(folkMetal);
        list.add(rock);
        when(genreDao.findAll()).thenReturn(list);
        List<Genre> result = genreService.findAllGenresInYearRange(1980, 2015);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), folkMetal);
    }
    
    @Test
    public void testFindAllGenresInYearRange4() {
        System.out.println("findAllGenresInYearRange4");
        
        List<Genre> list = new ArrayList<>();
        list.add(folkMetal);
        list.add(rock);
        when(genreDao.findAll()).thenReturn(list);
        List<Genre> result = genreService.findAllGenresInYearRange(1900, 1940);
        assertEquals(result.size(), 0);
    }
    
    @Test
    public void testFindAllGenresByYearOfOriginOrderedAscending(){
        System.out.println("testFindAllGenresByYearOfOriginOrderedAscending");
        
        List<Genre> expectedResult = new ArrayList<>(); 
        expectedResult.add(folkMetal);      // 1980                 
        expectedResult.add(rock);           // 1950
        List<Genre> orderedGenres = genreService.findAllGenresByYearOfOriginOrdered(true);

        Assert.assertEquals(expectedResult.size(), orderedGenres.size());
        when(genreDao.findAll()).thenReturn(expectedResult);

        for(int i = 0; i < expectedResult.size() - 1; i++)
        {
            int prewYear = orderedGenres.get(i).getYearOfOrigin();
            int nextYear = orderedGenres.get(i + 1).getYearOfOrigin();
            Assert.assertTrue(prewYear <= nextYear);

        }
    }
    
    @Test
    public void testFindAllGenresByYearOfOriginOrderedDescending(){
        System.out.println("testFindAllGenresByYearOfOriginOrderedDescending");
        
        List<Genre> expectedResult = new ArrayList<>(); 
        expectedResult.add(rock);           // 1950
        expectedResult.add(folkMetal);      // 1980                 
        List<Genre> orderedGenres = genreService.findAllGenresByYearOfOriginOrdered(false);

        Assert.assertEquals(expectedResult.size(), orderedGenres.size());
        when(genreDao.findAll()).thenReturn(expectedResult);

        for(int i = 0; i < expectedResult.size() - 1; i++)
        {
            int prewYear = orderedGenres.get(i).getYearOfOrigin();
            int nextYear = orderedGenres.get(i + 1).getYearOfOrigin();
            Assert.assertTrue(prewYear >= nextYear);

        }
    }
}
