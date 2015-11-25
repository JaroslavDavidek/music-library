package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.GenreDao;
import cz.fi.muni.pa165.entity.Genre;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jergus Fasanek
 */
@Service
public class GenreServiceImplementation implements GenreService{
    
    @Inject
    private GenreDao genreDao;
    
    @Override
    public Genre createGenre(Genre genre) {
        if(genreDao.create(genre)){
            return genre;
        }
        return null;
    }

    @Override
    public boolean deleteGenre(Genre genre) {
        if(genre != null){
            return genreDao.delete(genre);
        }
        return false;
    }

    @Override
    public Genre updateTitle(Genre genre, String newTitle) {
        if(genre != null && newTitle != null){
            genre.setTitle(newTitle);
            return genreDao.update(genre);
        }
        return null;
    }

    @Override
    public Genre updateYearOfOrigin(Genre genre, int newYearOfOrigin) {
        if(genre != null){
            genre.setYearOfOrigin(newYearOfOrigin);
            return genreDao.update(genre);
        }
        return null;
    }

    @Override
    public Genre findGenreByID(Long genreID) {
        return genreDao.findById(genreID);
    }

    @Override
    public Genre findGenreByTitle(String genreTitle) {
        return genreDao.findByTitle(genreTitle);
    }

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
    
}
