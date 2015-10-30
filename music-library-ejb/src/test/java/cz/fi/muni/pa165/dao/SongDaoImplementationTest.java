package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceAplicationContext;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;

/**
 *
 * @author JaroslavDavidek
 */
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@ContextConfiguration(classes = PersistenceAplicationContext.class)
public class SongDaoImplementationTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    public SongDao songDao;

    @Autowired
    public AlbumDao albumDao;
    
    @PersistenceContext
    private EntityManager em;

    private Song shootToThrillSong;
    
    private Song backInBlackSong;
    
    private Song hellBellsSong;
    
    private Song haveADrinkOnMeSong;
    
    private Album backInBlackAlbum;
    
    //private Genre hardRock;
    
    //private Musician acdc;
    
    @BeforeMethod
    public void setUpClass() {
        
        //acdc = new Musician();
        //acdc.setArtistName("AC/DC");
        
        backInBlackAlbum = new Album();
        //backInBlackAlbum.setMusician(acdc);
        backInBlackAlbum.setReleaseDate(Date.valueOf("1980-7-25"));
        backInBlackAlbum.setTitle("Back In Black");
        albumDao.create(backInBlackAlbum);
        
        //hardRock = new Genre();
        //hardRock.setTitle("Hard Rock");
        
        
        
        shootToThrillSong = new Song();
        shootToThrillSong.setTitle("Shoot To Thrill");
        shootToThrillSong.setAlbum(backInBlackAlbum);      
        //shootToThrillSong.setGenre(hardRock);
        //shootToThrillSong.setMusician(acdc);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        songDao.create(shootToThrillSong);
        
        haveADrinkOnMeSong = new Song();
        haveADrinkOnMeSong.setTitle("Have A Drink On Me");
        haveADrinkOnMeSong.setAlbum(backInBlackAlbum);      
        //haveADrinkOnMeSong.setGenre(hardRock);
        //haveADrinkOnMeSong.setMusician(acdc);
        haveADrinkOnMeSong.setAlbumPosition(8);
        haveADrinkOnMeSong.setBitrate(320);
        songDao.create(haveADrinkOnMeSong);
        
        backInBlackSong = new Song();
        backInBlackSong.setTitle("Back In Black");
        backInBlackSong.setAlbum(backInBlackAlbum);
        //backInBlackSong.setGenre(hardRock);
        //backInBlackSong.setMusician(acdc);
        backInBlackSong.setAlbumPosition(6);
        backInBlackSong.setBitrate(320);
        
        hellBellsSong = new Song();
        hellBellsSong.setTitle("Hell Bells");
        hellBellsSong.setAlbum(backInBlackAlbum);
        //hellBellsSong.setGenre(hardRock);
        //hellBellsSong.setMusician(acdc);
        hellBellsSong.setAlbumPosition(1);
        hellBellsSong.setBitrate(320);
        
    }

    @Transactional
    @Test
    public void testFindById() {      
        System.out.println("findById");
        Song foundSong = songDao.findById(shootToThrillSong.getId());
        boolean result = shootToThrillSong.equals(foundSong);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of findByTitle method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindByTitle() {
        System.out.println("findByTitle");
        
        String songTitle = shootToThrillSong.getTitle();
        Song foundSong = songDao.findByTitle(songTitle);
        boolean result = shootToThrillSong.equals(foundSong);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of findAll method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindAll() {
        System.out.println("findAll");
        
        List<Song> foundSongs = songDao.findAll();      
        List<Song> expectedResult = new ArrayList();
        expectedResult.add(shootToThrillSong);
        expectedResult.add(haveADrinkOnMeSong);
        
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }

    
    /**
     * Test of findAllByMusician method, of class SongDaoImplementation.
     */
    /*
    @Test
    @Transactional
    public void testFindAllByMusician() {
        System.out.println("findAllByMusician");
        Musician songAuthor = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllByMusician(songAuthor);
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of findAllByGenre method, of class SongDaoImplementation.
     */
    /*
    @Test
    @Transactional
    public void testFindAllByGenre() {
        System.out.println("findAllByGenre");
        Genre songGenre = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllByGenre(songGenre);
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByAlbum method, of class SongDaoImplementation.
     */  
    @Test
    @Transactional
    public void testFindAllByAlbum() {
        System.out.println("findAllByAlbum");
        List<Song> expectedResult = new ArrayList();
        expectedResult.add(shootToThrillSong);
        expectedResult.add(haveADrinkOnMeSong);      
        List<Song> foundSongs = songDao.findAllByAlbum(backInBlackAlbum);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }
    

    /**
     * Test of create method, of class SongDaoImplementation.
     */   
    @Transactional
    @Test
    public void testCreate() {    
        System.out.println("create");
        
        boolean result01 = songDao.create(this.shootToThrillSong);
        Assert.assertEquals(false, result01);
        
        boolean result02 = songDao.create(this.backInBlackSong);
        Assert.assertEquals(true, result02);
    }
    
    /**
     * Test of update method, of class GenreDaoImplementation.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        
        songDao.create(this.backInBlackSong);
        this.backInBlackSong.setBitrate(256);
        songDao.update(backInBlackSong);     
        Song mergedSong = songDao.findById(this.backInBlackSong.getId());
        boolean result = this.backInBlackSong.equals(mergedSong);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of delete method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testDelete() {       
        System.out.println("delete");
        
        boolean result01 = songDao.delete(this.shootToThrillSong);
        Assert.assertEquals(true, result01);
        
        boolean result02 = songDao.delete(this.hellBellsSong);
        Assert.assertEquals(false, result02);

    }
    
}
