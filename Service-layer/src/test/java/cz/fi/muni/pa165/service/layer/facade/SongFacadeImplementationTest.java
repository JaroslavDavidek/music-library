package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.SongCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.SongDTO;
import cz.fi.muni.pa165.api.layer.facade.SongFacade;
import cz.fi.muni.pa165.dao.AlbumDao;
import cz.fi.muni.pa165.dao.GenreDao;
import cz.fi.muni.pa165.dao.MusicianDao;
import cz.fi.muni.pa165.dao.SongDao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
import cz.fi.muni.pa165.service.layer.service.SongService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author JaroslavDavidek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class SongFacadeImplementationTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private SongFacade songFacade;
    
    @Autowired
    private SongService songService;

    @Autowired
    public SongDao songDao;
    
    @Autowired
    public AlbumDao albumDao;
    
    @Autowired
    public GenreDao genreDao;
    
    @Autowired
    public MusicianDao musicianDao;
    
    private Musician acdc;
    
    private Musician direStraits;
    
    private Genre hardRock;
    
    private Genre rock;
    
    private Album backInBlackAlbum;
    
    private Album brothersInArmsAlbum;
    
    private Album onEveryStreetAlbum;
    
    private Song shootToThrillSong;
    
    private Song storedSong;
    
    private SongDTO shootToThrillSongDTO;
    
    private SongCreateDTO shootToThrillSongCreateDTO;
    
    @BeforeClass
    public void setUp() {
        
        hardRock = new Genre();
        hardRock.setTitle("Hard Rock");
        hardRock.setYearOfOrigin(1970); 
        
        rock = new Genre();
        rock.setTitle("Hard Rock");
        rock.setYearOfOrigin(1950); 
        
        acdc = new Musician();
        acdc.setRealName("Brian Johnson");
        acdc.setArtistName("AC/DC");
        acdc.setDateOfBirth(Date.valueOf("1945-10-15"));
        
        direStraits = new Musician();
        direStraits.setRealName("Mark Knopfler");
        direStraits.setArtistName("Dire Straits");
        direStraits.setDateOfBirth(Date.valueOf("1949-12-8"));

        backInBlackAlbum = new Album();
        backInBlackAlbum.setMusician(acdc);
        backInBlackAlbum.setReleaseDate(Date.valueOf("1980-7-25"));
        backInBlackAlbum.setTitle("Back In Black");
        
        brothersInArmsAlbum = new Album();
        brothersInArmsAlbum.setMusician(direStraits);
        brothersInArmsAlbum.setReleaseDate(Date.valueOf("1985-6-13"));
        brothersInArmsAlbum.setTitle("Brothers in Arms");
        
        onEveryStreetAlbum = new Album();
        onEveryStreetAlbum.setMusician(direStraits);
        onEveryStreetAlbum.setReleaseDate(Date.valueOf("1991-10-11"));
        onEveryStreetAlbum.setTitle("On Every Street");
        
        shootToThrillSong = new Song();
        shootToThrillSong.setTitle("Shoot To Thrill DTO");
        shootToThrillSong.setAlbum(backInBlackAlbum);      
        shootToThrillSong.setGenre(hardRock);
        shootToThrillSong.setMusician(acdc);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        
        shootToThrillSongCreateDTO = new SongCreateDTO();
        shootToThrillSongCreateDTO.setTitle(shootToThrillSong.getTitle());
        shootToThrillSongCreateDTO.setAlbumPosition(shootToThrillSong.getAlbumPosition());
        shootToThrillSongCreateDTO.setBitrate(shootToThrillSong.getBitrate());
        shootToThrillSongCreateDTO.setAlbumCommentary(backInBlackAlbum.getCommentary());
        shootToThrillSongCreateDTO.setAlbumCover(backInBlackAlbum.getCover());
        shootToThrillSongCreateDTO.setAlbumReleaseDate(backInBlackAlbum.getReleaseDate());
        shootToThrillSongCreateDTO.setAlbumTitle(backInBlackAlbum.getTitle());
        shootToThrillSongCreateDTO.setGenreTitle(hardRock.getTitle());
        shootToThrillSongCreateDTO.setGenreYearOfOrigin(hardRock.getYearOfOrigin());
        shootToThrillSongCreateDTO.setMusicianArtistName(acdc.getArtistName());
        shootToThrillSongCreateDTO.setMusicianRealName(acdc.getRealName());
        shootToThrillSongCreateDTO.setMusicianDateOfBirth(acdc.getDateOfBirth());
       
    }

    @Test
    public void testCreateSong() {
        
        System.out.println("createSong");
        Long createdSongID = songFacade.createSong(shootToThrillSongCreateDTO);
        storedSong = songService.findSongByID(createdSongID);
        assertEquals(storedSong.getId(), createdSongID);  
    }

  
    @Test
    public void testDeleteSong() {
        
        System.out.println("deleteSong");
        SongFacadeImplementation instance = new SongFacadeImplementation();
        songFacade.deleteSong(storedSong.getId());
        Song expectedResult = songService.findSongByID(storedSong.getId());
        assertEquals(null, expectedResult);        
    }

    
    @Test
    public void testUpdateTitle() {
        System.out.println("updateTitle");
        Long songID = null;
        String newTitle = "";
        SongFacadeImplementation instance = new SongFacadeImplementation();
        instance.updateTitle(songID, newTitle);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /*
    @Test
    public void testUpdateBitrate() {
        System.out.println("updateBitrate");
        Long songID = null;
        int newBitrate = 0;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        instance.updateBitrate(songID, newBitrate);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    @Test
    public void testUpdateAlbumPosition() {
        System.out.println("updateAlbumPosition");
        Long songID = null;
        int newAlbumPosition = 0;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        instance.updateAlbumPosition(songID, newAlbumPosition);
        // TODO review the generated test code and remove the default call to fail.
        
    }

  
    @Test
    public void testUpdateCommentary() {
        System.out.println("updateCommentary");
        Long songID = null;
        String newCommentary = "";
        SongFacadeImplementation instance = new SongFacadeImplementation();
        instance.updateCommentary(songID, newCommentary);
        // TODO review the generated test code and remove the default call to fail.
        
    }

   
    @Test
    public void testUpdateMusician() {
        System.out.println("updateMusician");
        Long songID = null;
        Long musicianID = null;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        instance.updateMusician(songID, musicianID);
        // TODO review the generated test code and remove the default call to fail.
        
    }

   
    @Test
    public void testUpdateGenre() {
        System.out.println("updateGenre");
        Long songID = null;
        Long genreID = null;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        instance.updateGenre(songID, genreID);
        // TODO review the generated test code and remove the default call to fail.
        
    }

   
    @Test
    public void testUpdateAlbum() {
        System.out.println("updateAlbum");
        Long songID = null;
        Long albumID = null;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        instance.updateAlbum(songID, albumID);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    @Test
    public void testFindSongByID() {
        System.out.println("findSongByID");
        Long songID = null;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        SongDTO expResult = null;
        SongDTO result = instance.findSongByID(songID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    @Test
    public void testFindAllSongsByMusician() {
        System.out.println("findAllSongsByMusician");
        Long musicianID = null;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        List<SongDTO> expResult = null;
        List<SongDTO> result = instance.findAllSongsByMusician(musicianID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    @Test
    public void testFindAllSongsByGenre() {
        System.out.println("findAllSongsByGenre");
        Long genreID = null;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        List<SongDTO> expResult = null;
        List<SongDTO> result = instance.findAllSongsByGenre(genreID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

 
    @Test
    public void testFindAllSongsByAlbum() {
        System.out.println("findAllSongsByAlbum");
        Long albumID = null;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        List<SongDTO> expResult = null;
        List<SongDTO> result = instance.findAllSongsByAlbum(albumID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    @Test
    public void testFindAllSongsByAlbumOrdered() {
        System.out.println("findAllSongsByAlbumOrdered");
        Long albumID = null;
        boolean ascending = false;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        List<SongDTO> expResult = null;
        List<SongDTO> result = instance.findAllSongsByAlbumOrdered(albumID, ascending);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

   
    @Test
    public void testFindAllSongsByMusicianAndReleaseYearRange() {
        System.out.println("findAllSongsByMusicianAndReleaseYearRange");
        Long musicianID = null;
        int fromYear = 0;
        int toYear = 0;
        SongFacadeImplementation instance = new SongFacadeImplementation();
        List<SongDTO> expResult = null;
        List<SongDTO> result = instance.findAllSongsByMusicianAndReleaseYearRange(musicianID, fromYear, toYear);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    */
}
