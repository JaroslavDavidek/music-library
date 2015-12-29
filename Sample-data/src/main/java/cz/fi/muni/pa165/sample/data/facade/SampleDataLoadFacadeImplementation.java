package cz.fi.muni.pa165.sample.data.facade;

import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.entity.User;
import cz.fi.muni.pa165.service.layer.service.AlbumService;
import cz.fi.muni.pa165.service.layer.service.GenreService;
import cz.fi.muni.pa165.service.layer.service.MusicianService;
import cz.fi.muni.pa165.service.layer.service.SongService;
import cz.fi.muni.pa165.service.layer.service.UserService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author JaroslavDavidek
 */
@Component
public class SampleDataLoadFacadeImplementation implements SampleDataLoadFacade{
    
    @Autowired
    private SongService songService;
    
    @Autowired
    private AlbumService albumService;

    @Autowired
    private GenreService genreService;
    
    @Autowired
    private MusicianService musicianService;

    @Autowired
    private UserService userService;
    
    @Override
    public void loadSampleData() throws IOException {
        
        storeUser("admin","admin","admin@musiclib.com",true);
        storeUser("user","user","user@musiclib.com",false);
        
        Genre rock = storeGenre("Rock", 1950);
        Genre hardRock = storeGenre("Hard Rock", 1970);
        Genre heavyMetal = storeGenre("Heavy Metal", 1960);
        Genre electro = storeGenre("Electronic music", 1920);
        Genre alternativeRock = storeGenre("Alternative Rock", 1980);
        Genre dnb = storeGenre("Drum and bass", 1990);
        Genre pop = storeGenre("Pop", 1990);
        
        Musician metallica = storeMusician("James Hetfield", "Metallica", Date.valueOf("1950-9-22"));
        Musician acdc = storeMusician("Brian Johnson", "AC-DC", Date.valueOf("1948-2-17"));
        Musician avicii = storeMusician("Tim Bergling", "Avicii", Date.valueOf("1945-6-14"));
        Musician dio = storeMusician("Ronnie James Dio", "Dio", Date.valueOf("1956-8-11"));
        Musician direStraits = storeMusician("Mark Knopfler", "Dire Straits", Date.valueOf("1949-8-12"));
        
        
        //Album backInBlackAlbum = storeAlbum("Back In Black", "", Date.valueOf("1980-7-25"), "backinblack_cover.jpg", acdc);
        Album backInBlackAlbum = storeAlbum("Back In Black", "", Date.valueOf("1980-7-25"), "", acdc);
        
        //Album brothersInArmsAlbum = storeAlbum("Brothers in Arms", "", Date.valueOf("1985-6-13"), "brothersinarms_cover.jpg", direStraits);
        Album brothersInArmsAlbum = storeAlbum("Brothers in Arms", "", Date.valueOf("1985-6-13"), "", direStraits);
        
        //Album onEveryStreetAlbum = storeAlbum("On Every Street", "", Date.valueOf("1991-10-11"), "oneverystreet_cover.jpg", direStraits);
        Album onEveryStreetAlbum = storeAlbum("On Every Street", "", Date.valueOf("1991-10-11"), "", direStraits);
       
        Album bornedAgainAlbum = storeAlbum("Borned Again", "", Date.valueOf("1983-11-15"), "", direStraits);
        
        Song backInBlack = storeSong("Back In Black", "", 320, 6, backInBlackAlbum, acdc, hardRock);
        Song shootToThrill = storeSong("Shoot To Thrill", "", 320, 2, backInBlackAlbum, acdc, hardRock);
        Song haveADrinkOnMe = storeSong("Have A Drink On Me", "", 320, 8, backInBlackAlbum, acdc, hardRock);
        Song hellBells = storeSong("Hell Bells", "", 320, 1, backInBlackAlbum, acdc, hardRock);
        Song moneyForNothing = storeSong("Money For Nothing", "", 256, 2, brothersInArmsAlbum, direStraits, rock);
        Song oneWorld = storeSong("One World", "", 256, 8, brothersInArmsAlbum, direStraits, rock);
        Song yourLatestTrick = storeSong("Your Latest Trick", "", 256, 4, brothersInArmsAlbum, direStraits, rock);
        Song heavyFuel = storeSong("Heavy Fuel", "", 192, 7, onEveryStreetAlbum, direStraits, rock);
        Song trashed = storeSong("Trashed", "", 128, 1, bornedAgainAlbum, dio, heavyMetal);
        
        List<Song> backInBlackAlbumPlaylist = new ArrayList<>();
        backInBlackAlbumPlaylist.add(backInBlack);
        backInBlackAlbumPlaylist.add(shootToThrill);
        backInBlackAlbumPlaylist.add(haveADrinkOnMe);
        backInBlackAlbumPlaylist.add(hellBells);
        
        List<Song> brothersInArmsAlbumPlaylist = new ArrayList<>();
        brothersInArmsAlbumPlaylist.add(moneyForNothing);
        brothersInArmsAlbumPlaylist.add(oneWorld);
        brothersInArmsAlbumPlaylist.add(yourLatestTrick);
        
        List<Song> onEveryStreetAlbumPlaylist = new ArrayList<>();
        onEveryStreetAlbumPlaylist.add(heavyFuel);
        
        List<Song> bornedAgainAlbumPlaylist = new ArrayList<>();
        bornedAgainAlbumPlaylist.add(trashed);
                
        addSongsToAlbum(backInBlackAlbum, backInBlackAlbumPlaylist);
        
        addSongsToAlbum(brothersInArmsAlbum, brothersInArmsAlbumPlaylist);
        
        addSongsToAlbum(onEveryStreetAlbum, onEveryStreetAlbumPlaylist);
        
        addSongsToAlbum(bornedAgainAlbum, bornedAgainAlbumPlaylist);
       
    }
    
    private void storeUser(String password, String login, String email, boolean isAdmin) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setAdmin(isAdmin);
        userService.registerUser(user, password);
    }
    
    private Song storeSong(String title, String commentary, int bitrate, int albumPosition, Album album, Musician musician, Genre genre) {
        Song song = new Song();
        song.setTitle(title);
        song.setCommentary(commentary);
        song.setBitrate(bitrate);
        song.setAlbumPosition(albumPosition);
        song.setAlbum(album);
        song.setGenre(genre);
        song.setMusician(musician);       
        songService.createSong(song);
        return song;
    }

    private Album storeAlbum(String title, String commentary, Date releaseDate, String coverFilePath, Musician musician) throws IOException {
        Album album = new Album();
        album.setTitle(title);
        album.setCommentary(commentary);
        album.setReleaseDate(releaseDate);
        album.setCover(readImage(coverFilePath));
        album.setMusician(musician);
        albumService.createAlbum(album);  
        return album;
    }
    
    private void addSongsToAlbum(Album album, List<Song> songs)
    {
        for(Song song : songs)
        {
            albumService.addSong(album, song);
        }
    }
    
    private Musician storeMusician(String realName, String artistName, Date dateOfBirth) {
        Musician musician = new Musician();
        musician.setRealName(realName);
        musician.setArtistName(artistName);
        musician.setDateOfBirth(dateOfBirth);
        musicianService.createMusician(musician);  
        return musician;
    }
    
    private Genre storeGenre(String title, int yearOfOrigin) {
        Genre genre = new Genre();
        genre.setTitle(title);
        genre.setYearOfOrigin(yearOfOrigin);
        genreService.createGenre(genre);
        return genre;
    }
    
    private Byte[] readImage(String file) throws IOException {
        if(file == null && file.isEmpty()){
            return new Byte[0];
        }
        try (InputStream is = this.getClass().getResourceAsStream("/" + file)) {
            int nRead;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] bytes = buffer.toByteArray();
            Byte[] outputBytes = new Byte[bytes.length];
            int i=0;    
            for(byte b: bytes){
               outputBytes[i++] = b; 
            }
            return outputBytes;
        }
    }
}
