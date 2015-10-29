/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.PersistenceAplicationContext;
import cz.fi.muni.pa165.entity.Album;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
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

    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void findAll() {
        Album a = new Album();
        Album b = new Album();

        a.setTitle("album one");
        b.setTitle("album two");
        a.setReleaseDate(new Date(0));
        b.setReleaseDate(new Date(0));

        albumDao.create(a);
        albumDao.create(b);

        List<Album> albums = albumDao.findAll();
        Assert.assertEquals(2, albums.size());
        Assert.assertTrue(albums.contains(a));
        Assert.assertTrue(albums.contains(b));
    }
}
