package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author JaroslavDavidek
 */
public class SongServiceImplementationTest {
    
    @Autowired
    private SongService songService;
    
    @BeforeMethod
    public void setUp() {
    }

    /**
     * Test of createSong method, of class SongServiceImplementation.
     */
    @Test
    public void testCreateSong() {
        System.out.println("createSong");
        Song song = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        Song expResult = null;
        Song result = instance.createSong(song);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteSong method, of class SongServiceImplementation.
     */
    @Test
    public void testDeleteSong() {
        System.out.println("deleteSong");
        Song song = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        instance.deleteSong(song);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateTitle method, of class SongServiceImplementation.
     */
    @Test
    public void testUpdateTitle() {
        System.out.println("updateTitle");
        Song song = null;
        String newTitle = "";
        SongServiceImplementation instance = new SongServiceImplementation();
        instance.updateTitle(song, newTitle);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateBitrate method, of class SongServiceImplementation.
     */
    @Test
    public void testUpdateBitrate() {
        System.out.println("updateBitrate");
        Song song = null;
        int newBitrate = 0;
        SongServiceImplementation instance = new SongServiceImplementation();
        instance.updateBitrate(song, newBitrate);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateAlbumPosition method, of class SongServiceImplementation.
     */
    @Test
    public void testUpdateAlbumPosition() {
        System.out.println("updateAlbumPosition");
        Song song = null;
        int newAlbumPosition = 0;
        SongServiceImplementation instance = new SongServiceImplementation();
        instance.updateAlbumPosition(song, newAlbumPosition);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateCommentary method, of class SongServiceImplementation.
     */
    @Test
    public void testUpdateCommentary() {
        System.out.println("updateCommentary");
        Song song = null;
        String newCommentary = "";
        SongServiceImplementation instance = new SongServiceImplementation();
        instance.updateCommentary(song, newCommentary);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateMusician method, of class SongServiceImplementation.
     */
    @Test
    public void testUpdateMusician() {
        System.out.println("updateMusician");
        Song song = null;
        Long musicianID = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        instance.updateMusician(song, musicianID);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateGenre method, of class SongServiceImplementation.
     */
    @Test
    public void testUpdateGenre() {
        System.out.println("updateGenre");
        Song song = null;
        Long genreID = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        instance.updateGenre(song, genreID);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateAlbum method, of class SongServiceImplementation.
     */
    @Test
    public void testUpdateAlbum() {
        System.out.println("updateAlbum");
        Song song = null;
        Long albumID = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        instance.updateAlbum(song, albumID);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of findSongByID method, of class SongServiceImplementation.
     */
    @Test
    public void testFindSongByID() {
        System.out.println("findSongByID");
        Long songID = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        Song expResult = null;
        Song result = instance.findSongByID(songID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of findAllSongsByMusician method, of class SongServiceImplementation.
     */
    @Test
    public void testFindAllSongsByMusician() {
        System.out.println("findAllSongsByMusician");
        Long musicianID = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllSongsByMusician(musicianID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of findAllSongsByGenre method, of class SongServiceImplementation.
     */
    @Test
    public void testFindAllSongsByGenre() {
        System.out.println("findAllSongsByGenre");
        Long genreID = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllSongsByGenre(genreID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of findAllSongsByAlbum method, of class SongServiceImplementation.
     */
    @Test
    public void testFindAllSongsByAlbum() {
        System.out.println("findAllSongsByAlbum");
        Long albumID = null;
        SongServiceImplementation instance = new SongServiceImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllSongsByAlbum(albumID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of findAllSongsByAlbumOrdered method, of class SongServiceImplementation.
     */
    @Test
    public void testFindAllSongsByAlbumOrdered() {
        System.out.println("findAllSongsByAlbumOrdered");
        Long albumID = null;
        boolean ascending = false;
        SongServiceImplementation instance = new SongServiceImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllSongsByAlbumOrdered(albumID, ascending);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of findAllSongsByMusicianAndReleaseYearRange method, of class SongServiceImplementation.
     */
    @Test
    public void testFindAllSongsByMusicianAndReleaseYearRange() {
        System.out.println("findAllSongsByMusicianAndReleaseYearRange");
        Long musicianID = null;
        int fromYear = 0;
        int toYear = 0;
        SongServiceImplementation instance = new SongServiceImplementation();
        List<Song> expResult = null;
        List<Song> result = instance.findAllSongsByMusicianAndReleaseYearRange(musicianID, fromYear, toYear);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
