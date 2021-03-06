package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import cz.fi.muni.pa165.api.layer.dto.GenreDTO;
import cz.fi.muni.pa165.api.layer.dto.MusicianDTO;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author JaroslavDavidek
 */
@ContextConfiguration(classes = MappingConfiguration.class)
public class MappingServiceImplementationTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private MappingService mappingService;
    
    private Musician acdc;
    
    private MusicianDTO musicianDTO;
    
    private Genre hardRock;
    
    private GenreDTO genreDTO;
    
    private Album backInBlackAlbum;
    
    private AlbumDTO albumDTO;
    
    private Song shootToThrillSong;
    
    private SongDTO shootToThrillSongDTO;
    
    private Song haveADrinkOnMeSong;
    
    private SongDTO haveADrinkOnMeSongDTO;
    
    private List<Song> songs;
    
    private List<SongDTO> dtoSongs;
    
    @BeforeClass
    public void setUp() {

        acdc = new Musician();
        acdc.setRealName("Brian Johnson");
        acdc.setArtistName("AC/DC");
        acdc.setDateOfBirth(Date.valueOf("1945-10-15"));
        
        hardRock = new Genre();
        hardRock.setTitle("Hard Rock");
        hardRock.setYearOfOrigin(1970); 
        
        backInBlackAlbum = new Album();
        backInBlackAlbum.setMusician(acdc);
        backInBlackAlbum.setReleaseDate(Date.valueOf("1980-7-25"));
        backInBlackAlbum.setTitle("Back In Black");
        
        shootToThrillSong = new Song();
        shootToThrillSong.setTitle("Shoot To Thrill");
        shootToThrillSong.setAlbum(backInBlackAlbum);      
        shootToThrillSong.setGenre(hardRock);
        shootToThrillSong.setMusician(acdc);
        shootToThrillSong.setAlbumPosition(2);
        shootToThrillSong.setBitrate(320);
        
        haveADrinkOnMeSong = new Song();
        haveADrinkOnMeSong.setTitle("Have A Drink On Me");
        haveADrinkOnMeSong.setAlbum(backInBlackAlbum);      
        haveADrinkOnMeSong.setGenre(hardRock);
        haveADrinkOnMeSong.setMusician(acdc);
        haveADrinkOnMeSong.setAlbumPosition(8);
        haveADrinkOnMeSong.setBitrate(320);
        
       
       
        genreDTO = new GenreDTO();
        genreDTO.setTitle(hardRock.getTitle());
        genreDTO.setYearOfOrigin(hardRock.getYearOfOrigin());
        
        musicianDTO = new MusicianDTO();
        musicianDTO.setArtistName(acdc.getArtistName());
        musicianDTO.setRealName(acdc.getRealName());
        musicianDTO.setDateOfBirth(acdc.getDateOfBirth());
        
        albumDTO = new AlbumDTO();
        albumDTO.setTitle(backInBlackAlbum.getTitle());
        albumDTO.setReleaseDate(backInBlackAlbum.getReleaseDate());
        albumDTO.setMusician(musicianDTO);
        albumDTO.setCover(backInBlackAlbum.getCover());
       
        shootToThrillSongDTO = new SongDTO();
        shootToThrillSongDTO.setTitle("Shoot To Thrill");
        shootToThrillSongDTO.setAlbumPosition(2);
        shootToThrillSongDTO.setBitrate(320);
        shootToThrillSongDTO.setAlbum(albumDTO);      
        shootToThrillSongDTO.setGenre(genreDTO);
        shootToThrillSongDTO.setMusician(musicianDTO);
     
        haveADrinkOnMeSongDTO = new SongDTO();
        haveADrinkOnMeSongDTO.setTitle("Have A Drink On Me");
        haveADrinkOnMeSongDTO.setAlbumPosition(8);
        haveADrinkOnMeSongDTO.setBitrate(320);
        haveADrinkOnMeSongDTO.setAlbum(albumDTO);      
        haveADrinkOnMeSongDTO.setGenre(genreDTO);
        haveADrinkOnMeSongDTO.setMusician(musicianDTO);
        
        songs = new ArrayList<Song>();
        songs.add(this.shootToThrillSong);
        songs.add(this.haveADrinkOnMeSong);
        
        dtoSongs = new ArrayList<SongDTO>();
        dtoSongs.add(this.shootToThrillSongDTO);
        dtoSongs.add(this.haveADrinkOnMeSongDTO);
    }
   
    @Test
    public void testGetMapper() {
        System.out.println("getMapper");
        Mapper mapper = mappingService.getMapper();       
        assertEquals(true, mapper instanceof Mapper);
    }

    
    @Test
    public void testMapTo_Object_Class() {
        System.out.println("mapTo");
        Song mappedSong = mappingService.mapTo(shootToThrillSongDTO, Song.class);
        assertEquals(shootToThrillSong, mappedSong);
    }

    @Test
    public void testMapTo_Collection_Class() {
        System.out.println("mapTo");
        List<Song> mappedSongs = mappingService.mapToCollection(dtoSongs, Song.class);
        assertEquals(songs, mappedSongs);
    }
}
