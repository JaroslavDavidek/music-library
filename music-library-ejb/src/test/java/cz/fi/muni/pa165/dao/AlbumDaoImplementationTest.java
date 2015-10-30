/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceAplicationContext;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Musician;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Peter Franek
 */
@ContextConfiguration(classes = PersistenceAplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AlbumDaoImplementationTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private AlbumDao albumDao;
    
    Album albumOne;
    Album albumTwo;
    
    @Autowired
    private MusicianDao musicianDao;
    
    private Musician metallica;
    
    private Musician acdc;
    
    private Musician avicii;
    
    private Musician dio;

    @PersistenceContext
    private EntityManager em;
    
    @BeforeMethod
    public void setUpClass() {
        metallica = new Musician();
        acdc = new Musician();
        avicii = new Musician();
        dio = new Musician();
        
        metallica.setRealName("James Hetfield");
        metallica.setArtistName("Metallica");
        metallica.setDateOfBirth(new Date(123));
        
        acdc.setRealName("Brian Johnson");
        acdc.setArtistName("AC-DC");
        acdc.setDateOfBirth(new Date(456));
        
        avicii.setRealName("Tim Bergling");
        avicii.setArtistName("Avicii");
        avicii.setDateOfBirth(new Date(789));
        
        dio.setRealName("Ronnie James Dio");
        dio.setArtistName("Dio");
        dio.setDateOfBirth(new Date(666));
        
        musicianDao.create(metallica);
        musicianDao.create(acdc);
        musicianDao.create(avicii);
        musicianDao.create(dio);
        
        albumOne = new Album();
        albumTwo = new Album();
        albumOne.setTitle("album one");
        albumTwo.setTitle("album two");
        albumOne.setMusician(metallica);
        albumOne.setReleaseDate(new Date(0));
        albumTwo.setReleaseDate(new Date(0));
        albumTwo.setMusician(metallica);
    }
    
    @Test
    public void findAll() {
        albumDao.create(albumOne);
        albumDao.create(albumTwo);

        List<Album> albums = albumDao.findAll();
        Assert.assertEquals(2, albums.size());
        Assert.assertTrue(albums.contains(albumOne));
        Assert.assertTrue(albums.contains(albumTwo));
    }
    
    @Test
    public void findByTitle() {
        albumDao.create(albumOne);
        albumDao.create(albumTwo);

        Album found = albumDao.findByTitle(albumOne.getTitle());
        Assert.assertEquals(albumOne, found);
    }
    
    @Test
    public void createAlbum() {
        albumDao.create(albumOne);
        Assert.assertEquals(albumOne, albumDao.findById(albumOne.getId()));
        Assert.assertEquals(1, albumDao.findAll().size());
    }
    
    @Test
    public void deleteAlbum() {
        albumDao.create(albumOne);
        Assert.assertEquals(albumOne, albumDao.findById(albumOne.getId()));
        Assert.assertEquals(1, albumDao.findAll().size());
        
        albumDao.delete(albumOne);
        Assert.assertNull(albumDao.findById(albumOne.getId()));
        Assert.assertEquals(0, albumDao.findAll().size());
    }
    
    @Test
    public void updateAlbum() {
        albumDao.create(albumTwo);
        Assert.assertEquals(albumTwo, albumDao.findById(albumTwo.getId()));
        Assert.assertEquals(1, albumDao.findAll().size());
        
        albumTwo.setTitle("Random Title Change");
        albumDao.update(albumTwo);
        Assert.assertEquals(albumTwo, albumDao.findById(albumTwo.getId()));
    }
    
}
