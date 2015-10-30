package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceAplicationContext;
import cz.fi.muni.pa165.entity.Genre;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
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
 * @author JaroslavDavidek
 */
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@ContextConfiguration(classes = PersistenceAplicationContext.class)
public class GenreDaoImplementationTest  extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    public GenreDao genreDao;
    
    private Genre rock;
    
    private Genre heavyMetal;
    
    private Genre electronic;
    
    private Genre alternativeRock;
    
    @BeforeMethod
    public void setUpClass() {
        
        rock = new Genre();
        rock.setTitle("Rock");
        rock.setYearOfOrigin(1950);
        genreDao.create(rock);
        
        heavyMetal = new Genre();
        heavyMetal.setTitle("Heavy Metal");
        heavyMetal.setYearOfOrigin(1960);  
        
        electronic = new Genre();
        electronic.setTitle("Electronic music");
        electronic.setYearOfOrigin(1920);  
        
        alternativeRock = new Genre();
        alternativeRock.setTitle("Alternative Rock");
        alternativeRock.setYearOfOrigin(1980);  
    }

    /**
     * Test of findById method, of class GenreDaoImplementation.
     */
    @org.testng.annotations.Test
    @Transactional
    public void testFindById() {
        System.out.println("findById");
        
        Genre foundGenre = genreDao.findById(this.rock.getId());
        boolean result = this.rock.equals(foundGenre);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of findByTitle method, of class GenreDaoImplementation.
     */
    @org.testng.annotations.Test
    @Transactional
    public void testFindByTitle() {
        System.out.println("findByTitle");
        
        String genreTitle = this.rock.getTitle();
        Genre foundGenre = genreDao.findByTitle(genreTitle);
        boolean result = this.rock.equals(foundGenre);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of findAll method, of class GenreDaoImplementation.
     */
    @org.testng.annotations.Test
    @Transactional
    public void testFindAll() {
        System.out.println("findAll");
        
        genreDao.create(this.alternativeRock);
        List<Genre> foundGenres = genreDao.findAll();      
        List<Genre> expectedResult = new ArrayList();
        expectedResult.add(this.rock);
        expectedResult.add(this.alternativeRock);
        
        Assert.assertEquals(expectedResult.size(), foundGenres.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundGenres.get(i));
        }
    }

    /**
     * Test of create method, of class GenreDaoImplementation.
     */
    @org.testng.annotations.Test
    @Transactional
    public void testCreate() {
        System.out.println("create");
        
        boolean result01 = genreDao.create(this.rock);
        Assert.assertEquals(false, result01);
        
        boolean result02 = genreDao.create(this.heavyMetal);
        Assert.assertEquals(true, result02);
    }

    /**
     * Test of update method, of class GenreDaoImplementation.
     */
    @org.testng.annotations.Test
    @Transactional
    public void testUpdate() {
        System.out.println("update");
        
        this.rock.setYearOfOrigin(1970);
        genreDao.update(this.rock);     
        Genre mergedGenre = genreDao.findById(this.rock.getId());
        boolean result = this.rock.equals(mergedGenre);
        Assert.assertEquals(true, result);
    }

    /**
     * Test of delete method, of class GenreDaoImplementation.
     */
    @org.testng.annotations.Test
    @Transactional
    public void testDelete() {
        System.out.println("delete");
        
        boolean result01 = genreDao.delete(this.rock);
        Assert.assertEquals(true, result01);
        
        boolean result02 = genreDao.delete(this.electronic);
        Assert.assertEquals(false, result02);
    }
    
}
