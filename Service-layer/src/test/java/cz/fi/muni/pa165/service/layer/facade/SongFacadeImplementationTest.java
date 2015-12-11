package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import cz.fi.muni.pa165.api.layer.dto.GenreDTO;
import cz.fi.muni.pa165.api.layer.dto.MusicianDTO;
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
import cz.fi.muni.pa165.service.layer.service.MappingService;
import cz.fi.muni.pa165.service.layer.service.SongService;
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
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author JaroslavDavidek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class SongFacadeImplementationTest extends AbstractTestNGSpringContextTests {
    
    
    
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
    
    @Autowired
    private MappingService mappingService;
    
    @Autowired
    private SongFacade songFacade;
    
    private Musician acdc;  
    
    private Musician direStraits;
    
    private Musician direStraits2;
    
    private MusicianDTO musicianDTO;
    
    private Genre progressiveRock;
    
    private Genre hardRock;
    
    private Genre rock;
    
    private GenreDTO genreDTO;
    
    private Album backInBlackAlbum;
    
    private AlbumDTO albumDTO;
    
    private Album brothersInArmsAlbum;
    
    private Album onEveryStreetAlbum;
    
    private Song shootToThrillSong;
    
    private Song moneyForNothingSong;
    
    private Song yourLatestTrickSong;
    
    private Song heavyFuelSong;
    
    private Song moneyForNothingAlternativeTakeSong;
    
    private SongDTO shootToThrillSongDTO;
    
    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        hardRock = new Genre();
        hardRock.setTitle("Hard Rock");
        hardRock.setYearOfOrigin(1970); 
        
        rock = new Genre();
        rock.setTitle("Hard Rock");
        rock.setYearOfOrigin(1950); 
        
        genreDTO = new GenreDTO();
        genreDTO.setTitle(rock.getTitle());
        genreDTO.setYearOfOrigin(rock.getYearOfOrigin());
        
        acdc = new Musician();
        acdc.setRealName("Brian Johnson");
        acdc.setArtistName("AC/DC");
        acdc.setDateOfBirth(Date.valueOf("1945-10-15"));
        
        musicianDTO = new MusicianDTO();
        musicianDTO.setArtistName(acdc.getArtistName());
        musicianDTO.setRealName(acdc.getRealName());
        musicianDTO.setDateOfBirth(acdc.getDateOfBirth());
        
        direStraits = new Musician();
        direStraits.setRealName("Mark Knopfler");
        direStraits.setArtistName("Dire Straits");
        direStraits.setDateOfBirth(Date.valueOf("1949-12-8"));

        backInBlackAlbum = new Album();
        backInBlackAlbum.setMusician(acdc);
        backInBlackAlbum.setReleaseDate(Date.valueOf("1980-7-25"));
        backInBlackAlbum.setTitle("Back In Black");
        
        albumDTO = new AlbumDTO();
        albumDTO.setTitle(backInBlackAlbum.getTitle());
        albumDTO.setReleaseDate(backInBlackAlbum.getReleaseDate());
        albumDTO.setMusician(this.musicianDTO);
        albumDTO.setCover(backInBlackAlbum.getCover());
        
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
        
        shootToThrillSongDTO = new SongDTO();
        shootToThrillSongDTO.setTitle(shootToThrillSong.getTitle());
        shootToThrillSongDTO.setAlbumPosition(shootToThrillSong.getAlbumPosition());
        shootToThrillSongDTO.setBitrate(shootToThrillSong.getBitrate());
        shootToThrillSongDTO.setAlbum(this.albumDTO);
        shootToThrillSongDTO.setGenre(this.genreDTO);
        shootToThrillSongDTO.setMusician(this.musicianDTO);
     
       
        progressiveRock = new Genre();
        progressiveRock.setTitle("Progressive Rock");
        progressiveRock.setYearOfOrigin(1960); 
        
        direStraits2 = new Musician();
        direStraits2.setRealName("Alan Clark");
        direStraits2.setArtistName("Dire Straits");
        direStraits2.setDateOfBirth(Date.valueOf("1952-3-5"));
        
        moneyForNothingSong = new Song();
        moneyForNothingSong.setTitle("Money For Nothing");
        moneyForNothingSong.setAlbum(brothersInArmsAlbum);      
        moneyForNothingSong.setGenre(rock);
        moneyForNothingSong.setMusician(direStraits);
        moneyForNothingSong.setAlbumPosition(2);
        moneyForNothingSong.setBitrate(320);
        
        moneyForNothingAlternativeTakeSong = moneyForNothingSong;
        
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
    public void testCreateSong() {       
        System.out.println("createSong");
        /* new implementation has taken place
        // this test also checks that no exception is thrown during create call
        when(songDao.create(any(Song.class))).thenReturn(true);
        when(songDao.findById(any(Long.class))).thenReturn(shootToThrillSong);
        Long createdSongID = songFacade.createSong(shootToThrillSongDTO);
        // songID is null, since song has not been stored because mock objects were used
        assertEquals(null, createdSongID);  
                */
    }

  
    @Test
    public void testDeleteSong() {       
        System.out.println("deleteSong");
        
        when(songDao.findById(any(Long.class))).thenReturn(shootToThrillSong);
        when(songDao.delete(any(Song.class))).thenReturn(true);
        boolean expectedResult = songFacade.deleteSong(1l);
        assertEquals(true, expectedResult);        
    }

    
    @Test
    public void testUpdateTitle() {
        System.out.println("updateTitle");
             
        String expectedTitle = "Money For Nothing - Alternative Take";
        moneyForNothingAlternativeTakeSong.setTitle(expectedTitle);
        when(songDao.update(any(Song.class))).thenReturn(moneyForNothingAlternativeTakeSong);
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingSong);
        SongDTO updatedResult = songFacade.updateTitle(1l, expectedTitle);
        assertEquals(expectedTitle, updatedResult.getTitle());
    }

    
    @Test
    public void testUpdateBitrate() {
        System.out.println("updateBitrate");
        
        int expectedBitrate = 256;
        moneyForNothingAlternativeTakeSong.setBitrate(expectedBitrate);
        when(songDao.update(any(Song.class))).thenReturn(moneyForNothingAlternativeTakeSong);
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingSong);
        SongDTO updatedResult = songFacade.updateBitrate(1l, expectedBitrate);
        assertEquals(expectedBitrate, updatedResult.getBitrate());
    }

    
    @Test
    public void testUpdateAlbumPosition() {
        System.out.println("updateAlbumPosition");
        
        int expectedAlbumPosition = 1;
        moneyForNothingAlternativeTakeSong.setAlbumPosition(expectedAlbumPosition);
        when(songDao.update(any(Song.class))).thenReturn(moneyForNothingAlternativeTakeSong);
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingSong);
        SongDTO updatedResult = songFacade.updateAlbumPosition(1l, expectedAlbumPosition);
        assertEquals(expectedAlbumPosition, updatedResult.getAlbumPosition());
    }

  
    @Test
    public void testUpdateCommentary() {
        System.out.println("updateCommentary");
        
        String expectedCommentary = "Notable for its controversial lyrics.";
        moneyForNothingAlternativeTakeSong.setTitle(expectedCommentary);
        when(songDao.update(any(Song.class))).thenReturn(moneyForNothingAlternativeTakeSong);
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingSong);
        SongDTO updatedResult = songFacade.updateCommentary(1l, expectedCommentary);
        assertEquals(expectedCommentary, updatedResult.getCommentary());
    }

   
    @Test
    public void testUpdateMusician() {
        System.out.println("updateMusician");
        
        Musician expectedMusician = direStraits2;
        moneyForNothingAlternativeTakeSong.setMusician(expectedMusician);
        when(musicianDao.findById(any(Long.class))).thenReturn(expectedMusician);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingSong);
        SongDTO updatedResult = songFacade.updateMusician(1l, 1l);
        assertEquals(mappingService.mapTo(expectedMusician, MusicianDTO.class), updatedResult.getMusician());
    }

    @Test
    public void testUpdateGenre() {
        System.out.println("updateGenre");
        
        Genre expectedGenre = hardRock;
        moneyForNothingAlternativeTakeSong.setGenre(expectedGenre);
        when(genreDao.findById(any(Long.class))).thenReturn(expectedGenre);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingSong);
        SongDTO updatedResult = songFacade.updateGenre(1l, 1l);
        assertEquals(mappingService.mapTo(expectedGenre, GenreDTO.class), updatedResult.getGenre());
    }

   
    @Test
    public void testUpdateAlbum() {
        System.out.println("updateAlbum");
        
        Album expectedAlbum = onEveryStreetAlbum;
        moneyForNothingAlternativeTakeSong.setAlbum(expectedAlbum);
        when(albumDao.findById(any(Long.class))).thenReturn(expectedAlbum);
        when(songDao.update(moneyForNothingSong)).thenReturn(moneyForNothingAlternativeTakeSong);
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingSong);
        SongDTO updatedResult = songFacade.updateAlbum(1l, 1l);
        assertEquals(mappingService.mapTo(expectedAlbum, AlbumDTO.class), updatedResult.getAlbum());
    }
  
    @Test
    public void testFindSongByID() {
        System.out.println("findSongByID");
        
        when(songDao.findById(any(Long.class))).thenReturn(moneyForNothingAlternativeTakeSong);
        SongDTO result = songFacade.findSongByID(1l);
        assertEquals(mappingService.mapTo(moneyForNothingAlternativeTakeSong, SongDTO.class), result);
    }
    
    @Test
    public void testFindSongByTitle() {
        System.out.println("findSongByTitle");
        
        when(songDao.findByTitle(any(String.class))).thenReturn(moneyForNothingAlternativeTakeSong);
        SongDTO result = songFacade.findSongByTitle(moneyForNothingAlternativeTakeSong.getTitle());
        assertEquals(mappingService.mapTo(moneyForNothingAlternativeTakeSong, SongDTO.class), result);
    }
    
    @Test
    public void testFindAll() {
        System.out.println("findAll");
    
        List<Song> expectedResult = new ArrayList<>();
        expectedResult.add(this.heavyFuelSong);
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);
        expectedResult.add(this.shootToThrillSong);
        expectedResult.add(this.moneyForNothingAlternativeTakeSong);
        
        when(songFacade.findAll()).thenReturn(mappingService.mapToCollection(expectedResult, SongDTO.class));
        List<SongDTO> foundSongs = songFacade.findAll();
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), SongDTO.class), foundSongs.get(i));
        }
    }
        
    @Test
    public void testFindAllSongsByMusician() {
        System.out.println("findAllSongsByMusician");
    
        List<Song> expectedResult = new ArrayList<>();
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);
        expectedResult.add(this.heavyFuelSong);      
        when(songDao.findAllByMusician(direStraits)).thenReturn(expectedResult);
        when(musicianDao.findById(any(Long.class))).thenReturn(direStraits);
        List<SongDTO> foundSongs = songFacade.findAllSongsByMusician(1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), SongDTO.class), foundSongs.get(i));
        }
    }
  
    @Test
    public void testFindAllSongsByGenre() {
        System.out.println("findAllSongsByGenre");
    
        List<Song> expectedResult = new ArrayList<>();    
        expectedResult.add(this.heavyFuelSong);
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);      
        when(songDao.findAllByGenre(rock)).thenReturn(expectedResult);
        when(genreDao.findById(any(Long.class))).thenReturn(rock);
        List<SongDTO> foundSongs = songFacade.findAllSongsByGenre(1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), SongDTO.class), foundSongs.get(i));
        }
    }

    @Test
    public void testFindAllSongsByAlbum() {
        System.out.println("findAllSongsByAlbum");
    
        List<Song> expectedResult = new ArrayList<>();    
        expectedResult.add(this.moneyForNothingSong);
        expectedResult.add(this.yourLatestTrickSong);      
        when(songDao.findAllByAlbum(brothersInArmsAlbum)).thenReturn(expectedResult);
        when(albumDao.findById(any(Long.class))).thenReturn(brothersInArmsAlbum);
        List<SongDTO> foundSongs = songFacade.findAllSongsByAlbum(1l);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), SongDTO.class), foundSongs.get(i));
        }
    }
  
    @Test
    public void testFindAllSongsByAlbumOrdered() {
        System.out.println("findAllSongsByAlbumOrdered");
    
        int albumPosition = 0;
        List<Song> expectedResult = new ArrayList<>();          
        expectedResult.add(this.yourLatestTrickSong);       // album position: 4
        expectedResult.add(this.moneyForNothingSong);       // album position: 2   
        when(songDao.findAllByAlbum(brothersInArmsAlbum)).thenReturn(expectedResult);
        when(albumDao.findById(any(Long.class))).thenReturn(brothersInArmsAlbum);
        List<SongDTO> foundSongs = songFacade.findAllSongsByAlbumOrdered(1l, true);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            int itemAlbumPosition = expectedResult.get(i).getAlbumPosition();
            Assert.assertTrue(albumPosition <= itemAlbumPosition);
            albumPosition = itemAlbumPosition;
        }
    }

    @Test
    public void testFindAllSongsByMusicianAndReleaseYearRange() {
        System.out.println("findAllSongsByMusicianAndReleaseYearRange");
    
        List<Song> allSongsByDireStraits = new ArrayList<>();
        allSongsByDireStraits.add(this.moneyForNothingSong);
        allSongsByDireStraits.add(this.yourLatestTrickSong);
        allSongsByDireStraits.add(this.heavyFuelSong);
        List<Song> expectedResult = new ArrayList<>();
        expectedResult.add(this.heavyFuelSong);
        when(songDao.findAllByMusician(direStraits)).thenReturn(allSongsByDireStraits);
        when(musicianDao.findById(any(Long.class))).thenReturn(direStraits);
        List<SongDTO> foundSongs = songFacade.findAllSongsByMusicianAndReleaseYearRange(1l, 1988, 1993);
        Assert.assertEquals(expectedResult.size(), foundSongs.size());
        for(int i = 0; i < expectedResult.size(); i++)
        {
            Assert.assertEquals(mappingService.mapTo(expectedResult.get(i), SongDTO.class), foundSongs.get(i));
        }
    }
}
