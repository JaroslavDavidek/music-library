package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Genre;
import java.util.List;

/**
 *
 * @author JaroslavDavidek
 */
public interface GenreDao {
    /**
     * Finds genre according to its id
     * @param id the id of the genre entity
     * @return genre with respective id, otherwise null
     */
    public Genre findById(Long id);
    
    /**
     * Finds genre according to its title
     * @param title the title of the genre entity
     * @return genre with respective title, otherwise null
     */
    public Genre findByTitle(String title);
    
    /**
     * Lists all genres
     * @return list of all stored genres
     */
    public List<Genre> findAll();
    
    
    /**
     * Persists genre entity in case it is not stored yet
     * @param genre to persist
     * @return true if creation was successful, otherwise returns false
     */
    public boolean create(Genre genre);

    /**
     * Merges genre entity
     * @param genre genre to update
     * @return merged genre
     */
    public Genre update(Genre genre);
    
    /**
     * Removes genre entity in case it is stored
     * @param genre to remove
     * @return true if deletion was successful, otherwise returns false
     */
    public boolean delete(Genre genre);
}
