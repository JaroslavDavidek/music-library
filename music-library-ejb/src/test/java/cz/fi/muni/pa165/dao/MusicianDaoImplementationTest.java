/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceAplicationContext;
import cz.fi.muni.pa165.entity.Musician;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Jergus Fasanek
 */
@ContextConfiguration(classes = PersistenceAplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class MusicianDaoImplementationTest extends AbstractTransactionalTestNGSpringContextTests{
    
    @Autowired
    public MusicianDao musicianDao;
    
    @PersistenceContext
    private EntityManager em;
    
    private Musician metallica;
    
    private Musician acdc;
    
    private Musician avicii;
    
    private Musician dio;
    

    
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
    }
    

    /**
     * Test of findById method, of class MusicianDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindById() {
        System.out.println("findById");
        Musician foundMusician = musicianDao.findById(metallica.getId());
        boolean result = metallica.equals(foundMusician);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of findAll method, of class MusicianDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindAll() {
        System.out.println("findAll");
        List<Musician> foundMusicians = musicianDao.findAll();
       
        List<Musician> expectedResult = new ArrayList();
        expectedResult.add(metallica);
        expectedResult.add(acdc);
        expectedResult.add(avicii);
        
        Assert.assertEquals(expectedResult.size(), foundMusicians.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundMusicians.get(i));
        }
    }

    /**
     * Test of findByRealName method, of class MusicianDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindByRealName() {
        System.out.println("findByRealName");        
        String realName = metallica.getRealName();
        Musician foundMusician = musicianDao.findByRealName(realName);
        boolean result = metallica.equals(foundMusician);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of findByArtistName method, of class MusicianDaoImplementation.
     */
    @Test
    @Transactional
    public void testFindByArtistName() {
        System.out.println("findByArtistName");        
        String artistName = metallica.getArtistName();
        Musician foundMusician = musicianDao.findByArtistName(artistName);
        boolean result = metallica.equals(foundMusician);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of create method, of class MusicianDaoImplementation.
     */
    @Test
    @Transactional
    public void testCreate() {
        System.out.println("create");
        
        boolean result1 = musicianDao.create(metallica);
        Assert.assertEquals(false, result1);
        
        boolean result2 = musicianDao.create(dio);
        Assert.assertEquals(true, result2);
    }

    /**
     * Test of delete method, of class MusicianDaoImplementation.
     */
    @Test
    @Transactional
    public void testDelete() {
        System.out.println("delete");
        
        boolean result1 = musicianDao.delete(metallica);
        Assert.assertEquals(true, result1);
        
        boolean result2 = musicianDao.delete(dio);
        Assert.assertEquals(false, result2);
    }
    
}
