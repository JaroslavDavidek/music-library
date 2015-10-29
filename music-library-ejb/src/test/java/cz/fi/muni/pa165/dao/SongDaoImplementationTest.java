package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.MusicLibraryPersistenceContext;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import java.sql.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author JaroslavDavidek
 */

@TestExecutionListeners(TransactionalTestExecutionListener.class)
@ContextConfiguration(classes = MusicLibraryPersistenceContext.class)
public class SongDaoImplementationTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    public SongDao songDao;
    
    private Song shootToThrillSong;
    
    private Song backInBlackSong;
    
    private Album backInBlackAlbum;
    
    private Genre hardRock;
    
    private Musician acdc;
    
    @BeforeMethod
    public void setUpClass() {
        acdc = new Musician();
        acdc.setArtistName("AC/DC");
        
        backInBlackAlbum = new Album();
        backInBlackAlbum.setMusician(acdc);
        backInBlackAlbum.setReleaseDate(Date.valueOf("1980-7-25"));
        backInBlackAlbum.setTitle("Back In Black");
        
        hardRock = new Genre();
        hardRock.setTitle("Hard Rock");
        
        
        
        shootToThrillSong = new Song();
        shootToThrillSong.setAlbum(backInBlackAlbum);
        shootToThrillSong.setGenre(hardRock);
        shootToThrillSong.setMusician(acdc);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        songDao.create(shootToThrillSong);
        
        backInBlackSong = new Song();
        backInBlackSong.setAlbum(backInBlackAlbum);
        backInBlackSong.setGenre(hardRock);
        backInBlackSong.setMusician(acdc);
        backInBlackSong.setAlbumPosition(6);
        backInBlackSong.setBitrate(320);
        
    }
    
    @AfterMethod
    public static void tearDownClass() {
             
    }
    
    
    

    @Transactional
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        Song expResult = null;
        Song result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByTitle method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindByTitle() {
        System.out.println("findByTitle");
        String songTitle = "";
        SongDaoImplementation instance = new SongDaoImplementation();
        Song expResult = null;
        Song result = instance.findByTitle(songTitle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindAll() {
        System.out.println("findAll");
        SongDaoImplementation instance = new SongDaoImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByMusician method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindAllByMusician() {
        System.out.println("findAllByMusician");
        Musician songAuthor = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllByMusician(songAuthor);
        assertEquals(expResult, result);
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
        Album songAlbum = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllByAlbum(songAlbum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByGenre method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindAllByGenre() {
        System.out.println("findAllByGenre");
        Genre songGenre = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllByGenre(songGenre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class SongDaoImplementation.
     * 
     * First assert expects create method to return false, since parameter is null
     * 
     * Second assert expects create method to return false, since shootToThrill was already persisted
     * 
     * Third assert expects create method to return true, since backInBlack was not persisted yet
     */
    @Test
    @Transactional
    public void testCreate() {
        System.out.println("create");
        Song song = null;
        
        boolean expectedNegativeResult = false;
        boolean expectedPositiveResult = true;
        boolean result01 = songDao.create(song);
        assertEquals(expectedNegativeResult, result01);
        
        boolean result02 = songDao.create(this.shootToThrillSong);
        assertEquals(expectedNegativeResult, result02);
        
        boolean result03 = songDao.create(this.backInBlackSong);
        assertEquals(expectedPositiveResult, result03);
    }

    /**
     * Test of delete method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testDelete() {
        System.out.println("delete");
        Song song = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        boolean expResult = false;
        boolean result = instance.delete(song);
        assertEquals(expResult, result);
        
        Song justAnotherSong = new Song();
        justAnotherSong.setAlbum(backInBlackAlbum);
        justAnotherSong.setAlbumPosition(5);
        justAnotherSong.setBitrate(256);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
