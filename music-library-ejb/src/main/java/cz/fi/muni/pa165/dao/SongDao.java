package cz.fi.muni.pa165.dao;
import cz.fi.muni.pa165.entity.Album;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.entity.Song;
import java.util.List;

/**
 *
 * @author JaroslavDavidek
 */
public interface SongDao {
    
    public Song findById(Long id);
    public Song findByTitle(String title);
    
    public List<Song> findAll();
    public List<Song> findAllByMusician(Musician musician);
    public List<Song> findAllByAlbum(Album album);
    public List<Song> findAllByGenre(Genre songGenre);
    
    public boolean create(Song song);
    public boolean delete(Song song);
}
