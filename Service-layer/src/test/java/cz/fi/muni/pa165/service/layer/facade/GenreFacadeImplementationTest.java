package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.GenreDTO;
import cz.fi.muni.pa165.api.layer.facade.GenreFacade;
import cz.fi.muni.pa165.dao.GenreDao;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
import cz.fi.muni.pa165.service.layer.service.GenreService;
import cz.fi.muni.pa165.service.layer.service.MappingService;
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
 * @author Jergus Fasanek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class GenreFacadeImplementationTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private GenreDao genreDao;
    
    @Autowired
    @InjectMocks
    private GenreService genreService;
    
    @Autowired
    private MappingService mappingService;
    
    @Autowired
    private GenreFacade genreFacade;
    
    private Genre rock;
    
    private Genre metal;
    
    private Genre hiphop;
    
    private Genre rock2;
    
    private GenreDTO rockDTO;
    
    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        
        rock = new Genre();
        rock.setTitle("rock");
        rock.setYearOfOrigin(1955);
        
        metal = new Genre();
        metal.setTitle("heavy metal");
        metal.setYearOfOrigin(1960);
        
        hiphop = new Genre();
        hiphop.setTitle("Hip hop");
        hiphop.setYearOfOrigin(1970);
        
        rockDTO = new GenreDTO();
        rockDTO.setTitle(rock.getTitle());
        rockDTO.setYearOfOrigin(rock.getYearOfOrigin());
        
        rock2 = rock;
    }
    

    /**
     * Test of createGenre method, of class GenreFacadeImplementation.
     */
    @Test
    public void testCreateGenre() {
        System.out.println("createGenre");
        
        // this test also checks that no exception is thrown during create call
        when(genreDao.create(any(Genre.class))).thenReturn(true);
        when(genreDao.findById(any(Long.class))).thenReturn(rock);
        Long createdGenreID = genreFacade.createGenre(rockDTO);
        // genreID is null, since genre has not been stored because mock objects were used
        assertEquals(null, createdGenreID); 
    }

    /**
     * Test of deleteGenre method, of class GenreFacadeImplementation.
     */
    @Test
    public void testDeleteGenre() {
        System.out.println("deleteGenre");
        
        when(genreDao.findById(any(Long.class))).thenReturn(rock);
        when(genreDao.delete(any(Genre.class))).thenReturn(true);
        boolean expectedResult = genreFacade.deleteGenre(1l);
        assertEquals(true, expectedResult);
    }

    /**
     * Test of updateTitle method, of class GenreFacadeImplementation.
     */
    @Test
    public void testUpdateTitle() {
        System.out.println("updateTitle");
             
        String expectedTitle = "Hard rock";
        rock2.setTitle(expectedTitle);
        when(genreDao.update(any(Genre.class))).thenReturn(rock2);
        when(genreDao.findById(any(Long.class))).thenReturn(rock);
        GenreDTO updatedResult = genreFacade.updateTitle(1l, expectedTitle);
        assertEquals(expectedTitle, updatedResult.getTitle());
    }

    /**
     * Test of updateYearOfOrigin method, of class GenreFacadeImplementation.
     */
    @Test
    public void testUpdateYearOfOrigin() {
        System.out.println("updateYearOfOrigin");
             
        int expectedYear = 1954;
        rock2.setYearOfOrigin(expectedYear);
        when(genreDao.update(any(Genre.class))).thenReturn(rock2);
        when(genreDao.findById(any(Long.class))).thenReturn(rock);
        GenreDTO updatedResult = genreFacade.updateYearOfOrigin(1l, expectedYear);
        assertEquals(expectedYear, updatedResult.getYearOfOrigin());
    }

    /**
     * Test of findGenreByID method, of class GenreFacadeImplementation.
     */
    @Test
    public void testFindGenreByID() {
        System.out.println("findGenreByID");
        
        when(genreDao.findById(any(Long.class))).thenReturn(rock2);
        GenreDTO result = genreFacade.findGenreByID(1l);
        assertEquals(mappingService.mapTo(rock2, GenreDTO.class), result);
    }

    /**
     * Test of findGenreByTitle method, of class GenreFacadeImplementation.
     */
    @Test
    public void testFindGenreByTitle() {
        System.out.println("findGenreByTitle");
        
        when(genreDao.findByTitle(any(String.class))).thenReturn(rock2);
        GenreDTO result = genreFacade.findGenreByTitle(rock2.getTitle());
        assertEquals(mappingService.mapTo(rock2, GenreDTO.class), result);
    }

    /**
     * Test of findAll method, of class GenreFacadeImplementation.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
    
        List<Genre> expectedResult = new ArrayList<>();
        expectedResult.add(rock);
        expectedResult.add(rock2);
        expectedResult.add(metal);
        expectedResult.add(hiphop);
        
        when(genreDao.findAll()).thenReturn(expectedResult);
        List<GenreDTO> found = genreFacade.findAll();
        Assert.assertEquals(expectedResult.size(), found.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), GenreDTO.class), found.get(i));
        }
    }
    
}
