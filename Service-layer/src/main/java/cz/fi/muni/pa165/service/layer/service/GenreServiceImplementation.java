package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.dao.GenreDao;
import cz.fi.muni.pa165.dao.SongDao;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.entity.Song;
import cz.fi.muni.pa165.service.layer.util.comparator.GenrePositionASCComparator;
import cz.fi.muni.pa165.service.layer.util.comparator.GenrePositionDSCComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    @Inject
    private SongDao songDao;
    
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
            for(Song song : songDao.findAllByGenre(genre)) {
                songDao.delete(song);
            }
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
    
    @Override
    public List<Genre> findAllGenresInYearRange(int from, int to){
        List<Genre> filteredGenres = new ArrayList<>();
        List<Genre> allGenres = this.findAll();
        if(from > to || allGenres.isEmpty()){
            return filteredGenres;
        }
        for(Genre genre : allGenres){
            int year = genre.getYearOfOrigin();
            if(year >= from && year <= to){
                filteredGenres.add(genre);
            }
        }
        return filteredGenres;
    }
    
    @Override
    public List<Genre> findAllGenresByYearOfOriginOrdered(boolean ascending){
        List<Genre> allGenres = this.findAll();
        if(allGenres.isEmpty()){
            return new ArrayList<>();
        }
        Comparator comparator;
        if(ascending){
            comparator = new GenrePositionASCComparator();
        } else {
            comparator = new GenrePositionDSCComparator();
        }
        Collections.sort(allGenres, comparator);
        return allGenres;
    }
    
}
