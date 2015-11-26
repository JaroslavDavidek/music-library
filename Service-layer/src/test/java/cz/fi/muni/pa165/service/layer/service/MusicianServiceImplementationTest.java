package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.MusicianDao;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.exception.InvalidParamDataAccessExpection;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/**
 *
 * @author Jergus Fasanek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class MusicianServiceImplementationTest  extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private MusicianDao musicianDao;
    
    @Autowired
    @InjectMocks
    private MusicianService musicianService;
    
    private Musician prince;
    
    private Musician madonna;

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
        
        prince = new Musician();
        madonna = new Musician();
        
        prince.setRealName("Prince Rogers Nelson");
        madonna.setRealName("Louise Ciccone");
        
        prince.setArtistName("Prince");
        madonna.setArtistName("Madonna");
        
        prince.setDateOfBirth(new Date(1));
        madonna.setDateOfBirth(new Date(3));
        
    }
    
    @Test
    public void testCreateMusician1() {
        System.out.println("testCreateMusician1");
   
       
        when(musicianDao.create(any(Musician.class))).thenReturn(true);
        Musician musician = musicianService.createMusician(prince);       
        assertEquals(musician, prince);
    }
    
    @Test
    public void testCreateMusician2() {
        System.out.println("testCreateMusician2");
        
        when(musicianDao.create(any(Musician.class))).thenReturn(false);
        Musician createdMusician = musicianService.createMusician(prince);       
        assertEquals(null, createdMusician);
    } 
    
    @Test
    public void testDeleteMusician1() {
        System.out.println("testDeleteMusician1");
        
        when(musicianDao.delete(any(Musician.class))).thenReturn(true);
        boolean result = musicianService.deleteMusician(prince);
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteMusician2() {
        System.out.println("testDeleteMusician2");
        
        when(musicianDao.delete(any(Musician.class))).thenReturn(false);
        boolean result = musicianService.deleteMusician(prince);
        assertEquals(false, result);
    }
    
    @Test
    public void testUpdateRealName1() {
        System.out.println("testUpdateRealName1");
        
        String expectedRealName = "Prince";
        Musician prince2 = prince;
        prince2.setRealName(expectedRealName);
        when(musicianDao.update(prince)).thenReturn(prince2);
        Musician updatedResult = musicianService.updateRealName(prince, expectedRealName);
        assertEquals(expectedRealName, updatedResult.getRealName());
    }
    
    @Test
    public void testUpdateRealName2() {
        System.out.println("testUpdateRealName2");
        
        String expectedRealName = null;
        Musician prince2 = prince;
        prince2.setRealName(expectedRealName);
        when(musicianDao.update(prince)).thenReturn(prince2);
        Musician updatedResult = musicianService.updateRealName(prince, expectedRealName);
        assertEquals(null, updatedResult);
    }
    
    @Test
    public void testUpdateArtistName1() {
        System.out.println("testUpdateArtistName1");
        
        String expectedArtistName = "Prince Rogers Nelson";
        Musician prince2 = prince;
        prince2.setArtistName(expectedArtistName);
        when(musicianDao.update(prince)).thenReturn(prince2);
        Musician updatedResult = musicianService.updateArtistName(prince, expectedArtistName);
        assertEquals(expectedArtistName, updatedResult.getArtistName());
    }
    
    @Test
    public void testUpdateArtistName2() {
        System.out.println("testUpdateArtistName2");
        
        String expectedArtistName = null;
        Musician prince2 = prince;
        prince2.setArtistName(expectedArtistName);
        when(musicianDao.update(prince)).thenReturn(prince2);
        Musician updatedResult = musicianService.updateArtistName(prince, expectedArtistName);
        assertEquals(null, updatedResult);
    }
    
    @Test
    public void testUpdateDateOfBirth1() {
        System.out.println("testUpdateDate1");
        
        Date date = Date.valueOf("1958-6-7");
        Musician princeMusician2 = prince;
        princeMusician2.setDateOfBirth(date);
        when(musicianDao.update(prince)).thenReturn(princeMusician2);
        Musician updatedResult = musicianService.updateDateOfBirth(prince, date);
        assertEquals(date, updatedResult.getDateOfBirth());
    }
    
    @Test
    public void testUpdateDateOfBirth2() {
        System.out.println("testUpdateDate2");
        
        Date date = null;
        Musician princeMusician2 = prince;
        princeMusician2.setDateOfBirth(date);
        when(musicianDao.update(prince)).thenReturn(princeMusician2);
        Musician updatedResult = musicianService.updateDateOfBirth(prince, date);
        assertEquals(null, updatedResult);
    }
    
    @Test
    public void testFindMusicianByID() {
        System.out.println("findMusicianByID");
        
        when(musicianDao.findById(any(Long.class))).thenReturn(prince);
        Musician findResult = musicianService.findMusicianByID(1L);
        assertEquals(prince, findResult);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindMusicianByID2() {
        System.out.println("testFindMusicianByID2");
        
        when(musicianDao.findById(any(Long.class))).thenThrow(new InvalidParamDataAccessExpection("MusicianDao - find by id - wrong id parameter"));
        Musician findResult = musicianService.findMusicianByID(-1l);
        assertEquals(null, findResult);
    }
    
    @Test
    public void testFindMusicianByArtistName() {
        System.out.println("testFindMusicianByArtistName");
        
        when(musicianDao.findByArtistName(any(String.class))).thenReturn(prince);
        Musician result = musicianService.findMusicianByArtistName(prince.getArtistName());
        assertEquals(prince, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindMusicianByByArtistName2() {
        System.out.println("testFindMusicianByArtistName2");
        
        when(musicianDao.findByArtistName(null)).thenThrow(new InvalidParamDataAccessExpection("MusicianDao - find by Artist name - wrong Artist name parameter"));
        Musician result = musicianService.findMusicianByArtistName(null);
        assertEquals(null, result);
    }

    @Test
    public void testFindMusicianByRealName() {
        System.out.println("testFindMusicianByRealName");
        
        when(musicianDao.findByRealName(any(String.class))).thenReturn(prince);
        Musician result = musicianService.findMusicianByRealName(prince.getRealName());
        assertEquals(prince, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindMusicianByByRealName2() {
        System.out.println("testFindMusicianByRealName2");
        
        when(musicianDao.findByRealName(null)).thenThrow(new InvalidParamDataAccessExpection("MusicianDao - find by Real name - wrong Real name parameter"));
        Musician result = musicianService.findMusicianByRealName(null);
        assertEquals(null, result);
    }

    @Test
    public void testFindAll() {
        System.out.println("testSindAll");
      
        List<Musician> expectedResult = new ArrayList<>();
        expectedResult.add(prince);
        expectedResult.add(madonna);
        when(musicianDao.findAll()).thenReturn(expectedResult);
        List<Musician> foundMusician = musicianService.findAll();
        Assert.assertEquals(expectedResult.size(), foundMusician.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundMusician.get(i));
        }
    }
}
