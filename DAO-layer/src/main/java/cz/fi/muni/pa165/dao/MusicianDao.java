package cz.fi.muni.pa165.dao;
import cz.fi.muni.pa165.entity.Musician;
import java.util.List;
/**
 *
 * @author Jergus Fasanek
 */
public interface MusicianDao {
     /**
     * Find a musician by its ID in database
     * 
     * @param id ID of the searched musician
     * @return 
     */
    public Musician findById(Long id);
    
    /**
     * Find and return all musicians currently stored in the DB
     * 
     * @return all musicians in DB
     */
    public List<Musician> findAll();
    
    /**
     * Find a musician by its real name
     * 
     * @param realName of the searched musician   
     * @return musician with corresponding real name
     */
    public Musician findByRealName(String realName);
    
    /**
     * Find a musician by its artist name
     * 
     * @param artistName of the searched musician   
     * @return musician with corresponding artist name
     */
    public Musician findByArtistName(String artistName);
    
    /**
     * Create a new musician in DB
     * 
     * @param musician that is being added/created in db 
     * @return true if creation was successful, otherwise returns false
     */
    public boolean create(Musician musician);
    
    /**
     * Delete corresponding musician from db
     * 
     * @param musician that is being deleted from db
     * @return true if deletion was successful, otherwise returns false
     */
    public boolean delete(Musician musician);
    
    /**
     * Update given musician
     * 
     * @param musician that is being updated in db
     * @return true if updating was successful, otherwise returns false
     */
    public Musician update(Musician musician);
}
