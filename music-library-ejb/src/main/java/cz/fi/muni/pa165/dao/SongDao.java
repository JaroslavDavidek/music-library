package cz.fi.muni.pa165.dao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import java.util.List;

/**
 * Song DAO - supports basic operations over Song Entity
 * 
 * @author JaroslavDavidek
 */
public interface SongDao {
    
    /**
     * Finds Song according to its id
     * @param id the id of the song entity
     * @return Song with respective id, otherwise null
     */
    public Song findById(Long id);
    
    /**
     * Finds Song according to its title
     * @param title the title of the song entity
     * @return Song with respective title, otherwise null
     */
    public Song findByTitle(String title);
    
    /**
     * Lists all songs
     * @return list of all stored songs
     */
    public List<Song> findAll();
    
    /**
     * Lists all songs according to its author
     * @param musician author according to which songs are listed
     * @return list of all stored songs according to its author
     */
    public List<Song> findAllByMusician(Musician musician);
    
    /**
     * Lists all songs according to its Album
     * @param album album according to which songs are listed
     * @return list of all stored songs according to its Album
     */
    public List<Song> findAllByAlbum(Album album);
    
    /**
     * Lists all songs according to its Genre
     * @param songGenre genre according to which songs are listed
     * @return list of all stored songs according to its Genre
     */
    public List<Song> findAllByGenre(Genre songGenre);
    
    /**
     * Persists song entity in case it is not stored yet
     * @param song to persist
     * @return true if creation was successful, otherwise returns false
     */
    public boolean create(Song song);

    /**
     * Removes song entity in case it is stored
     * @param song to remove
     * @return true if deletion was successful, otherwise returns false
     */
    public boolean delete(Song song);
}
