package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.AlbumDao;
import cz.fi.muni.pa165.dao.GenreDao;
import cz.fi.muni.pa165.dao.MusicianDao;
import cz.fi.muni.pa165.dao.SongDao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.exception.InvalidParamDataAccessExpection;
import cz.fi.muni.pa165.service.layer.config.MappingConfiguration;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author JaroslavDavidek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class SongServiceImplementationTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private SongDao songDao;
    
    @Mock
    private MusicianDao musicianDao;
    
    @Mock
    private GenreDao genreDao;
    
    @Mock
    private AlbumDao albumDao;
    
    @Autowired
    @InjectMocks
    private SongService songService;
    
    private Genre rock;
    
    private Genre hardRock;
    
    private Genre progressiveRock;
    
    private Musician acdc;  
    
    private Musician direStraits;
    
    private Musician direStraits2;
    
    private Album brothersInArmsAlbum;
    
    private Album backInBlackAlbum;
    
    private Album onEveryStreetAlbum;
    
    private Song shootToThrillSong;    
    
    private Song moneyForNothingSong;
    
    private Song moneyForNothingAlternativeTakeSong;
    
    private Song oneWorldSong;
    
    private Song yourLatestTrickSong;
    
    private Song heavyFuelSong;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        rock = new Genre();
        rock.setTitle("Hard Rock");
        rock.setYearOfOrigin(1950); 
        
        hardRock = new Genre();
        hardRock.setTitle("Hard Rock");
        hardRock.setYearOfOrigin(1970); 
        
        progressiveRock = new Genre();
        progressiveRock.setTitle("Progressive Rock");
        progressiveRock.setYearOfOrigin(1960); 
        
        acdc = new Musician();
        acdc.setRealName("Brian Johnson");
        acdc.setArtistName("AC/DC");
        acdc.setDateOfBirth(Date.valueOf("1945-10-15"));
        
        direStraits = new Musician();
        direStraits.setRealName("Mark Knopfler");
        direStraits.setArtistName("Dire Straits");
        direStraits.setDateOfBirth(Date.valueOf("1949-12-8"));
        
        direStraits2 = new Musician();
        direStraits2.setRealName("Alan Clark");
        direStraits2.setArtistName("Dire Straits");
        direStraits2.setDateOfBirth(Date.valueOf("1952-3-5"));
        
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
        shootToThrillSong.setTitle("Shoot To Thrill");
        shootToThrillSong.setAlbum(backInBlackAlbum);      
        shootToThrillSong.setGenre(hardRock);
        shootToThrillSong.setMusician(acdc);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        
        moneyForNothingSong = new Song();
        moneyForNothingSong.setTitle("Money For Nothing");
        moneyForNothingSong.setAlbum(brothersInArmsAlbum);      
        moneyForNothingSong.setGenre(rock);
        moneyForNothingSong.setMusician(direStraits);
        moneyForNothingSong.setAlbumPosition(2);
        moneyForNothingSong.setBitrate(320);
        
        moneyForNothingAlternativeTakeSong = moneyForNothingSong;
        
        oneWorldSong = new Song();
        oneWorldSong.setTitle("One World");
        oneWorldSong.setAlbum(brothersInArmsAlbum);      
        oneWorldSong.setGenre(rock);
        oneWorldSong.setMusician(direStraits);
        oneWorldSong.setAlbumPosition(8);
        oneWorldSong.setBitrate(320);
        
        yourLatestTrickSong = new Song();
        yourLatestTrickSong.setTitle("Your Latest Trick");
        yourLatestTrickSong.setAlbum(brothersInArmsAlbum);      
        yourLatestTrickSong.setGenre(rock);
        yourLatestTrickSong.setMusician(direStraits);
        yourLatestTrickSong.setAlbumPosition(4);
        yourLatestTrickSong.setBitrate(320);
        
        heavyFuelSong = new Song();
        heavyFuelSong.setTitle("Heavy Fuel");
        heavyFuelSong.setAlbum(onEveryStreetAlbum);      
        heavyFuelSong.setGenre(rock);
        heavyFuelSong.setMusician(direStraits);
        heavyFuelSong.setAlbumPosition(7);
        heavyFuelSong.setBitrate(320);      
    }
   
    @Test
    public void testCreateSong1() {
        System.out.println("createSong1");
        
        when(songDao.create(any(Song.class))).thenReturn(true);
        Song createdSong = songService.createSong(moneyForNothingSong);       
        assertEquals(moneyForNothingSong, createdSong);
    }
    
    @Test
    public void testCreateSong2() {
        System.out.println("createSong2");
        
        when(songDao.create(any(Song.class))).thenReturn(false);
        Song createdSong = songService.createSong(moneyForNothingSong);       
        assertEquals(null, createdSong);
    }
   
    @Test
    public void testDeleteSong1() {
        System.out.println("deleteSong1");
        
        when(songDao.delete(any(Song.class))).thenReturn(true);
        boolean result = songService.deleteSong(moneyForNothingSong);
        assertEquals(true, result);
    }
    
    @Test
    public void testDeleteSong2() {
        System.out.println("deleteSong2");
        
        when(songDao.delete(any(Song.class))).thenReturn(false);
        boolean result = songService.deleteSong(moneyForNothingSong);
        assertEquals(false, result);
    }
    
    @Test
    public void testUpdateTitle1() {
        System.out.println("updateTitle1");
        
        String expectedTitle = "Money For Nothing - Alternative Take";
        moneyForNothingAlternativeTakeSong.setTitle(expectedTitle);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);
        Song updatedResult = songService.updateTitle(moneyForNothingSong, expectedTitle);
        assertEquals(expectedTitle, updatedResult.getTitle());
    }
    
    @Test
    public void testUpdateTitle2() {
        System.out.println("updateTitle2");
        
        String expectedTitle = null;
        moneyForNothingAlternativeTakeSong.setTitle(expectedTitle);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);
        Song updatedResult = songService.updateTitle(moneyForNothingSong, expectedTitle);
        assertEquals(null, updatedResult);
    }
    
    @Test
    public void testUpdateBitrate() {
        System.out.println("updateBitrate");
        
        int expectedBitrate = 256;
        moneyForNothingAlternativeTakeSong.setBitrate(256);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);
        Song updatedResult = songService.updateBitrate(moneyForNothingSong, expectedBitrate);
        assertEquals(expectedBitrate, updatedResult.getBitrate());
    }
    
    @Test
    public void testUpdateAlbumPosition() {
        System.out.println("updateAlbumPosition");
        
        int expectedPosition = 1;
        moneyForNothingAlternativeTakeSong.setAlbumPosition(expectedPosition);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);
        Song updatedResult = songService.updateAlbumPosition(moneyForNothingSong, expectedPosition);
        assertEquals(expectedPosition, updatedResult.getAlbumPosition());
    }
    
    @Test
    public void testUpdateCommentary() {
        System.out.println("updateCommentary");
        
        String expectedCommentary = "Notable for its controversial lyrics.";
        moneyForNothingAlternativeTakeSong.setCommentary(expectedCommentary);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);
        Song updatedResult = songService.updateCommentary(moneyForNothingSong, expectedCommentary);
        assertEquals(expectedCommentary, updatedResult.getCommentary());
    }
    
    @Test
    public void testUpdateMusician1() {
        System.out.println("updateMusician1");
        
        Musician expectedMusician = direStraits2;
        moneyForNothingAlternativeTakeSong.setMusician(expectedMusician);
        when(musicianDao.findById(any(Long.class))).thenReturn(direStraits2);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);      
        Song updatedResult = songService.updateMusician(moneyForNothingSong, 1l);
        assertEquals(expectedMusician, updatedResult.getMusician());
    }
    
    @Test
    public void testUpdateMusician2() {
        System.out.println("updateMusician2");
        
        Musician expectedMusician = null;
        moneyForNothingAlternativeTakeSong.setMusician(expectedMusician);
        when(musicianDao.findById(any(Long.class))).thenReturn(null);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);      
        Song updatedResult = songService.updateMusician(moneyForNothingSong, -1l);
        assertEquals(expectedMusician, updatedResult);
    }
   
    @Test
    public void testUpdateGenre1() {
        System.out.println("updateGenre1");
        
        Genre expectedGenre = progressiveRock;
        moneyForNothingAlternativeTakeSong.setGenre(expectedGenre);
        when(genreDao.findById(any(Long.class))).thenReturn(progressiveRock);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);      
        Song updatedResult = songService.updateGenre(moneyForNothingSong, 1l);
        assertEquals(expectedGenre, updatedResult.getGenre());      
    }
    
    @Test
    public void testUpdateGenre2() {
        System.out.println("updateGenre2");
        
        Genre expectedGenre = null;
        moneyForNothingAlternativeTakeSong.setGenre(expectedGenre);
        when(genreDao.findById(any(Long.class))).thenReturn(null);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);      
        Song updatedResult = songService.updateGenre(moneyForNothingSong, -1l);
        assertEquals(expectedGenre, updatedResult);      
    }
    
    @Test
    public void testUpdateAlbum1() {
        System.out.println("updateAlbum1");
        
        Album expectedAlbum = onEveryStreetAlbum;
        moneyForNothingAlternativeTakeSong.setAlbum(onEveryStreetAlbum);
        when(albumDao.findById(any(Long.class))).thenReturn(onEveryStreetAlbum);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);      
        Song updatedResult = songService.updateAlbum(moneyForNothingSong, 1l);
        assertEquals(expectedAlbum, updatedResult.getAlbum());
    }
    
    @Test
    public void testUpdateAlbum2() {
        System.out.println("updateAlbum2");
        
        Album expectedAlbum = null;
        moneyForNothingAlternativeTakeSong.setAlbum(onEveryStreetAlbum);
        when(albumDao.findById(any(Long.class))).thenReturn(null);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);      
        Song updatedResult = songService.updateAlbum(moneyForNothingSong, -1l);
        assertEquals(expectedAlbum, updatedResult);
    }
    
    @Test
    public void testFindSongByID1() {
        System.out.println("findSongByID1");
      
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingAlternativeTakeSong);
        Song result = songService.findSongByID(1l);
        assertEquals(moneyForNothingAlternativeTakeSong, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindSongByID2() {
        System.out.println("findSongByID2");
      
        when(songDao.findById(any(Long.class))).thenThrow(new InvalidParamDataAccessExpection("SongDao - findById - invalid index param passed"));
        Song result = songService.findSongByID(-1l);
        assertEquals(moneyForNothingAlternativeTakeSong, result);
    }
    
    @Test
    public void testFindSongByTitle1() {
        System.out.println("findSongByTitle1");
      
        when(songDao.findByTitle(any(String.class))).thenReturn(moneyForNothingAlternativeTakeSong);
        Song result = songService.findSongByTitle(moneyForNothingAlternativeTakeSong.getTitle());
        assertEquals(moneyForNothingAlternativeTakeSong, result);
    }
    
    @Test(expectedExceptions=InvalidParamDataAccessExpection.class)
    public void testFindSongByTitle2() {
        System.out.println("findSongByID2");
      
        when(songDao.findByTitle(null)).thenThrow(new InvalidParamDataAccessExpection("SongDao - findByTitle - null songTitle param passed"));
        Song result = songService.findSongByTitle(null);
        assertEquals(moneyForNothingAlternativeTakeSong, result);
    }
    
    @Test
    public void testFindAll() {
        System.out.println("findSongByTitle1");
      
        List<Song> expectedResult = new ArrayList<>();
        expectedResult.add(this.oneWorldSong);
        expectedResult.add(this.heavyFuelSong);
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);
        when(songDao.findAll()).thenReturn(expectedResult);
        List<Song> foundSongs = songService.findAll();
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }

    @Test
    public void testFindAllSongsByMusician1() {
        System.out.println("findAllSongsByMusician1");
        
        List<Song> expectedResult = new ArrayList<>();
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);
        expectedResult.add(this.oneWorldSong);
        expectedResult.add(this.heavyFuelSong);      
        when(songDao.findAllByMusician(direStraits)).thenReturn(expectedResult);
        when(musicianDao.findById(any(Long.class))).thenReturn(direStraits);
        List<Song> foundSongs = songService.findAllSongsByMusician(1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }
    
    @Test
    public void testFindAllSongsByMusician2() {
        System.out.println("findAllSongsByMusician2");
        
        List<Song> expectedResult = new ArrayList<>();    
        when(musicianDao.findById(any(Long.class))).thenReturn(null);
        List<Song> foundSongs = songService.findAllSongsByMusician(-1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }
   
    @Test
    public void testFindAllSongsByGenre1() {
        System.out.println("findAllSongsByGenre1");
        
        List<Song> expectedResult = new ArrayList<>();    
        expectedResult.add(this.oneWorldSong);
        expectedResult.add(this.heavyFuelSong);
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);      
        when(songDao.findAllByGenre(rock)).thenReturn(expectedResult);
        when(genreDao.findById(any(Long.class))).thenReturn(rock);
        List<Song> foundSongs = songService.findAllSongsByGenre(1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }
    
    @Test
    public void testFindAllSongsByGenre2() {
        System.out.println("findAllSongsByGenre2");
        
        List<Song> expectedResult = new ArrayList<>();    
        when(genreDao.findById(any(Long.class))).thenReturn(null);
        List<Song> foundSongs = songService.findAllSongsByGenre(-1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }
   
    @Test
    public void testFindAllSongsByAlbum1() {
        System.out.println("findAllSongsByAlbum1");
        
        List<Song> expectedResult = new ArrayList<>();    
        expectedResult.add(this.oneWorldSong);
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);      
        when(songDao.findAllByAlbum(brothersInArmsAlbum)).thenReturn(expectedResult);
        when(albumDao.findById(any(Long.class))).thenReturn(brothersInArmsAlbum);
        List<Song> foundSongs = songService.findAllSongsByAlbum(1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }
    
    @Test
    public void testFindAllSongsByAlbum2() {
        System.out.println("findAllSongsByAlbum2");
        
        List<Song> expectedResult = new ArrayList<>();    
        when(albumDao.findById(any(Long.class))).thenReturn(null);
        List<Song> foundSongs = songService.findAllSongsByAlbum(-1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }   
    
    @Test
    public void testFindAllSongsByAlbumOrderedAscending1() {
        System.out.println("findAllSongsByAlbumOrderedAscending1");
        
        int albumPosition = 0;
        List<Song> expectedResult = new ArrayList<>();    
        expectedResult.add(this.oneWorldSong);              // album position: 8 
        expectedResult.add(this.moneyForNothingSong);       // album position: 2 
        expectedResult.add(this.yourLatestTrickSong);       // album position: 4  
        when(songDao.findAllByAlbum(brothersInArmsAlbum)).thenReturn(expectedResult);
        when(albumDao.findById(any(Long.class))).thenReturn(brothersInArmsAlbum);
        List<Song> foundSongs = songService.findAllSongsByAlbumOrdered(1l, true);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            int itemAlbumPosition = expectedResult.get(i).getAlbumPosition();
            Assert.assertTrue(albumPosition <= itemAlbumPosition);
            albumPosition = itemAlbumPosition;
        }
    }
    
    @Test
    public void testFindAllSongsByAlbumOrderedAscending2() {
        System.out.println("findAllSongsByAlbumOrderedAscending2");
        
        int albumPosition = 0;
        List<Song> expectedResult = new ArrayList<>();    
        expectedResult.add(this.oneWorldSong);              // album position: 8 
        expectedResult.add(this.moneyForNothingSong);       // album position: 2 
        expectedResult.add(this.yourLatestTrickSong);       // album position: 4  
        when(songDao.findAllByAlbum(brothersInArmsAlbum)).thenReturn(expectedResult);
        when(albumDao.findById(any(Long.class))).thenReturn(null);
        List<Song> foundSongs = songService.findAllSongsByAlbumOrdered(-1l, true);
        Assert.assertEquals(0, foundSongs.size());
    }
    
    @Test
    public void testFindAllSongsByAlbumOrderedDescending1() {
        System.out.println("findAllSongsByAlbumOrderedDescending1");
        
        int albumPosition = 100;
        List<Song> expectedResult = new ArrayList<>();    
        expectedResult.add(this.oneWorldSong);              // album position: 8 
        expectedResult.add(this.moneyForNothingSong);       // album position: 2 
        expectedResult.add(this.yourLatestTrickSong);       // album position: 4  
        when(songDao.findAllByAlbum(brothersInArmsAlbum)).thenReturn(expectedResult);
        when(albumDao.findById(any(Long.class))).thenReturn(brothersInArmsAlbum);
        List<Song> foundSongs = songService.findAllSongsByAlbumOrdered(1l, false);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            int itemAlbumPosition = expectedResult.get(i).getAlbumPosition();
            Assert.assertTrue(albumPosition >= itemAlbumPosition);
            albumPosition = itemAlbumPosition;
        }
    }
    
    @Test
    public void testFindAllSongsByAlbumOrderedDescending2() {
        System.out.println("findAllSongsByAlbumOrderedDescending2");
        
        int albumPosition = 100;
        List<Song> expectedResult = new ArrayList<>();    
        expectedResult.add(this.oneWorldSong);              // album position: 8 
        expectedResult.add(this.moneyForNothingSong);       // album position: 2 
        expectedResult.add(this.yourLatestTrickSong);       // album position: 4  
        when(songDao.findAllByAlbum(brothersInArmsAlbum)).thenReturn(expectedResult);
        when(albumDao.findById(any(Long.class))).thenReturn(null);
        List<Song> foundSongs = songService.findAllSongsByAlbumOrdered(-1l, false);
        Assert.assertEquals(0, foundSongs.size());
    }
       
    @Test
    public void testFindAllSongsByMusicianAndReleaseYearRange1() {
        System.out.println("findAllSongsByMusicianAndReleaseYearRange1");
        
        List<Song> allSongsByDireStraits = new ArrayList<>();
        allSongsByDireStraits.add(this.moneyForNothingSong);
        allSongsByDireStraits.add(this.yourLatestTrickSong);
        allSongsByDireStraits.add(this.oneWorldSong);
        allSongsByDireStraits.add(this.heavyFuelSong);       
        List<Song> expectedResult = new ArrayList<>();
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);
        expectedResult.add(this.oneWorldSong);
        when(songDao.findAllByMusician(direStraits)).thenReturn(allSongsByDireStraits);
        when(musicianDao.findById(any(Long.class))).thenReturn(direStraits);
        List<Song> foundSongs = songService.findAllSongsByMusicianAndReleaseYearRange(1l, 1982, 1986);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }
    
    @Test
    public void testFindAllSongsByMusicianAndReleaseYearRange2() {
        System.out.println("findAllSongsByMusicianAndReleaseYearRange2");       
        
        List<Song> allSongsByDireStraits = new ArrayList<>();
        allSongsByDireStraits.add(this.moneyForNothingSong);
        allSongsByDireStraits.add(this.yourLatestTrickSong);
        allSongsByDireStraits.add(this.oneWorldSong);
        allSongsByDireStraits.add(this.heavyFuelSong);
        List<Song> expectedResult = allSongsByDireStraits;       
        when(songDao.findAllByMusician(direStraits)).thenReturn(allSongsByDireStraits);
        when(musicianDao.findById(any(Long.class))).thenReturn(direStraits);
        List<Song> foundSongs = songService.findAllSongsByMusicianAndReleaseYearRange(1l, 1985, 1991);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(expectedResult.get(i), foundSongs.get(i));
        }
    }
    
    @Test
    public void testFindAllSongsByMusicianAndReleaseYearRange3() {
        System.out.println("findAllSongsByMusicianAndReleaseYearRange3");
        
        List<Song> allSongsByDireStraits = new ArrayList<>();
        allSongsByDireStraits.add(this.moneyForNothingSong);
        allSongsByDireStraits.add(this.yourLatestTrickSong);
        allSongsByDireStraits.add(this.oneWorldSong);
        allSongsByDireStraits.add(this.heavyFuelSong);      
        when(songDao.findAllByMusician(direStraits)).thenReturn(allSongsByDireStraits);
        when(musicianDao.findById(any(Long.class))).thenReturn(direStraits);
        List<Song> foundSongs = songService.findAllSongsByMusicianAndReleaseYearRange(1l, 1982, 1984);
        Assert.assertEquals(0, foundSongs.size());
    }
    
    @Test
    public void testFindAllSongsByMusicianAndReleaseYearRange4() {
        System.out.println("findAllSongsByMusicianAndReleaseYearRange4");
        
        List<Song> allSongsByDireStraits = new ArrayList<>();
        allSongsByDireStraits.add(this.moneyForNothingSong);
        allSongsByDireStraits.add(this.yourLatestTrickSong);
        allSongsByDireStraits.add(this.oneWorldSong);
        allSongsByDireStraits.add(this.heavyFuelSong);      
        when(songDao.findAllByMusician(direStraits)).thenReturn(allSongsByDireStraits);
        when(musicianDao.findById(any(Long.class))).thenReturn(direStraits);
        List<Song> foundSongs = songService.findAllSongsByMusicianAndReleaseYearRange(1l, 1992, 1982);
        Assert.assertEquals(0, foundSongs.size());
    }
  
    @Test
    public void testFindAllSongsByMusicianAndReleaseYearRange5() {
        System.out.println("findAllSongsByMusicianAndReleaseYearRange5");
              
        when(musicianDao.findById(any(Long.class))).thenReturn(null);
        List<Song> foundSongs = songService.findAllSongsByMusicianAndReleaseYearRange(-1l, 1984, 1987);
        Assert.assertEquals(0, foundSongs.size());
    }
}
