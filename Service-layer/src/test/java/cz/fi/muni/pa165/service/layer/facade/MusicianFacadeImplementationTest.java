package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.MusicianDTO;
import cz.fi.muni.pa165.api.layer.facade.MusicianFacade;
import cz.fi.muni.pa165.dao.MusicianDao;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import cz.fi.muni.pa165.service.layer.service.MusicianService;
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
 * @author Jergus Fasanek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class MusicianFacadeImplementationTest extends AbstractTestNGSpringContextTests {
    
    @Mock
    private MusicianDao musicianDao;
    
    @Autowired
    @InjectMocks
    private MusicianService musicianService;
    
    @Autowired
    private MappingService mappingService;
    
    @Autowired
    private MusicianFacade musicianFacade;
    
    private Musician curt;
    
    private Musician jimi;
    
    private Musician curt2;
    
    private MusicianDTO curtDTO;
    
    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        
        curt = new Musician();
        curt.setArtistName("Curt Cobain");
        curt.setRealName("Curt Donald Cobain");
        curt.setDateOfBirth(Date.valueOf("1967-2-20"));
        
        jimi = new Musician();
        jimi.setArtistName("Jimi Hendrix");
        jimi.setRealName("James Marshall Hendrix");
        jimi.setDateOfBirth(Date.valueOf("1942-11-27"));
        
        curtDTO = new MusicianDTO();
        curtDTO.setArtistName(curt.getArtistName());
        curtDTO.setRealName(curt.getRealName());
        curtDTO.setDateOfBirth(curt.getDateOfBirth());
        
        curt2 = curt;
    }

    /**
     * Test of createMusician method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testCreateMusician() {
        System.out.println("createMusician");
        
        // this test also checks that no exception is thrown during create call
        when(musicianDao.create(any(Musician.class))).thenReturn(true);
        when(musicianDao.findById(any(Long.class))).thenReturn(curt);
        Long createdMusicianID = musicianFacade.createMusician(curtDTO);
        // musicianID is null, since musician has not been stored because mock objects were used
        assertEquals(null, createdMusicianID); 
    }

    /**
     * Test of deleteMusician method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testDeleteMusician() {
        System.out.println("deleteMusician");
        
        when(musicianDao.findById(any(Long.class))).thenReturn(curt);
        when(musicianDao.delete(any(Musician.class))).thenReturn(true);
        boolean expectedResult = musicianFacade.deleteMusician(1l);
        assertEquals(true, expectedResult);
    }

    /**
     * Test of updateRealName method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testUpdateRealName() {
        System.out.println("updateRealName");
             
        String expectedRealName = "Curt Donald Cobain 2";
        curt2.setRealName(expectedRealName);
        when(musicianDao.update(any(Musician.class))).thenReturn(curt2);
        when(musicianDao.findById(any(Long.class))).thenReturn(curt);
        MusicianDTO updatedResult = musicianFacade.updateRealName(1l, expectedRealName);
        assertEquals(expectedRealName, updatedResult.getRealName());
    }

    /**
     * Test of updateArtistName method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testUpdateArtistName() {
        System.out.println("updateArtistName");
             
        String expectedArtistName = "Curt Donald Cobain 2";
        curt2.setArtistName(expectedArtistName);
        when(musicianDao.update(any(Musician.class))).thenReturn(curt2);
        when(musicianDao.findById(any(Long.class))).thenReturn(curt);
        MusicianDTO updatedResult = musicianFacade.updateArtistName(1l, expectedArtistName);
        assertEquals(expectedArtistName, updatedResult.getArtistName());
    }

    /**
     * Test of updateDateOfBirth method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testUpdateDateOfBirth() {
        System.out.println("updateDateOfBirth");
             
        Date expectedDate = Date.valueOf("2015-11-26");
        curt2.setDateOfBirth(expectedDate);
        when(musicianDao.update(any(Musician.class))).thenReturn(curt2);
        when(musicianDao.findById(any(Long.class))).thenReturn(curt);
        MusicianDTO updatedResult = musicianFacade.updateDateOfBirth(1l, expectedDate);
        assertEquals(expectedDate, updatedResult.getDateOfBirth());
    }

    /**
     * Test of findMusicianByID method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testFindMusicianByID() {
        System.out.println("findMusicianByID");
        
        when(musicianDao.findById(any(Long.class))).thenReturn(curt2);
        MusicianDTO result = musicianFacade.findMusicianByID(1l);
        assertEquals(mappingService.mapTo(curt2, MusicianDTO.class), result);
    }

    /**
     * Test of findMusicianByRealName method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testFindMusicianByRealName() {
        System.out.println("findMusicianByRealName");
        
        when(musicianDao.findByRealName(any(String.class))).thenReturn(curt2);
        MusicianDTO result = musicianFacade.findMusicianByRealName(curt2.getRealName());
        assertEquals(mappingService.mapTo(curt2, MusicianDTO.class), result);
    }

    /**
     * Test of findMusicianByArtistName method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testFindMusicianByArtistName() {
        System.out.println("findMusicianByArtistName");
        
        when(musicianDao.findByArtistName(any(String.class))).thenReturn(curt2);
        MusicianDTO result = musicianFacade.findMusicianByArtistName(curt2.getArtistName());
        assertEquals(mappingService.mapTo(curt2, MusicianDTO.class), result);
    }

    /**
     * Test of findAll method, of class MusicianFacadeImplementation.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
    
        List<Musician> expectedResult = new ArrayList<>();
        expectedResult.add(curt);
        expectedResult.add(curt2);
        
        when(musicianDao.findAll()).thenReturn(expectedResult);
        List<MusicianDTO> found = musicianFacade.findAll();
        Assert.assertEquals(expectedResult.size(), found.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), MusicianDTO.class), found.get(i));
        }
    }
    
}
