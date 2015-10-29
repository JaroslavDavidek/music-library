/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        /*
        System.out.println("findById");
        Long id = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        Song expResult = null;
        Song result = instance.findById(id);
        Assert.assertEquals(expResult, result);
        */
    }

    /**
     * Test of findByTitle method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindByTitle() {
        /*
        System.out.println("findByTitle");
        String songTitle = "";
        SongDaoImplementation instance = new SongDaoImplementation();
        Song expResult = null;
        Song result = instance.findByTitle(songTitle);
        Assert.assertEquals(expResult, result);
        */
    }

    /**
     * Test of findAll method, of class SongDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindAll() {
        /*
        System.out.println("findAll");
        SongDaoImplementation instance = new SongDaoImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAll();
        Assert.assertEquals(expResult, result);
        */
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
        /*
        System.out.println("findAllByAlbum");
        Album songAlbum = null;
        SongDaoImplementation instance = new SongDaoImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllByAlbum(songAlbum);
        Assert.assertEquals(expResult, result);
        */
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
    
    @Transactional
    @Test
    public void testCreate() {        
        boolean result01 = songDao.create(this.shootToThrillSong);
        Assert.assertEquals(false, result01);
        
        boolean result02 = songDao.create(this.backInBlackSong);
        Assert.assertEquals(true, result02);
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
