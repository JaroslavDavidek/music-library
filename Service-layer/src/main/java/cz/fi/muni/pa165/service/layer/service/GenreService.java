package cz.fi.muni.pa165.service.layer.service;

import cz.fi.muni.pa165.entity.Genre;
import java.util.List;

/**
 *
 * @author Jergus Fasanek
 */
public interface GenreService {
    public Genre createGenre(Genre genre);
    public boolean deleteGenre(Genre genre);
    public Genre updateTitle(Genre genre, String newTitle);
    public Genre updateYearOfOrigin(Genre genre, int newYearOfOrigin);
    public Genre findGenreByID(Long genreID);
    public Genre findSongByTitle(String genreTitle);
    public List<Genre> findAll();
}
