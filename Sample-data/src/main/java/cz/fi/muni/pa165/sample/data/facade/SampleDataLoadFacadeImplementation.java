package cz.fi.muni.pa165.sample.data.facade;

import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.service.AlbumService;
import cz.fi.muni.pa165.service.layer.service.GenreService;
import cz.fi.muni.pa165.service.layer.service.MusicianService;
import cz.fi.muni.pa165.service.layer.service.SongService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

/**
 *
 * @author JaroslavDavidek
 */
@Component
public class SampleDataLoadFacadeImplementation implements SampleDataLoadFacade{
    
    @Inject
    private SongService songService;
    
    @Inject
    private AlbumService albumService;

    @Inject
    private GenreService genreService;
    
    @Inject
    private MusicianService musicianService;

    
    @Override
    public void loadSampleData() throws IOException {
        
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
        
        
        //Album backInBlackAlbum = storeAlbum("Back In Black", "", Date.valueOf("1980-7-25"), "backinblack_cover.jpg", acdc);
        Album backInBlackAlbum = storeAlbum("Back In Black", "", Date.valueOf("1980-7-25"), "", acdc);
        
        Song backInBlack = storeSong("Back In Black", "", 320, 6, backInBlackAlbum, acdc, hardRock);
        Song shootToThrill = storeSong("Shoot To Thrill", "", 320, 2, backInBlackAlbum, acdc, hardRock);
        Song haveADrinkOnMeSong = storeSong("Have A Drink On Me", "", 320, 8, backInBlackAlbum, acdc, hardRock);
        Song hellBellsSong = storeSong("Hell Bells", "", 320, 1, backInBlackAlbum, acdc, hardRock);
        
        List<Song> backInBlackAlbumPlaylist = new ArrayList<Song>();
        backInBlackAlbumPlaylist.add(backInBlack);
        backInBlackAlbumPlaylist.add(shootToThrill);
        backInBlackAlbumPlaylist.add(haveADrinkOnMeSong);
        backInBlackAlbumPlaylist.add(hellBellsSong);
                
        addSongsToAlbum(backInBlackAlbum, backInBlackAlbumPlaylist);
       
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
        return songService.createSong(song);
    }

    private Album storeAlbum(String title, String commentary, Date releaseDate, String coverFilePath, Musician musician) throws IOException {
        Album album = new Album();
        album.setTitle(title);
        album.setCommentary(commentary);
        album.setReleaseDate(releaseDate);
        album.setCover(readImage(coverFilePath));
        album.setMusician(musician);
        return albumService.createAlbum(album);    
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
        return musicianService.createMusician(musician);  
    }
    
    private Genre storeGenre(String title, int yearOfOrigin) {
        Genre genre = new Genre();
        genre.setTitle(title);
        genre.setYearOfOrigin(yearOfOrigin);
        return genreService.createGenre(genre);
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
