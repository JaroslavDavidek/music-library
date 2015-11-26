package cz.fi.muni.pa165.api.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.GenreDTO;
import java.util.List;

/**
 *
 * @author Jergus Fasanek
 */
public interface GenreFacade {
    public Long createGenre(GenreDTO genre);
    public boolean deleteGenre(Long genreID);
    public GenreDTO updateTitle(Long genreID, String newTitle);
    public GenreDTO updateYearOfOrigin(Long genreID, int newYearOfOrigin);
    public GenreDTO findGenreDTOByID(Long genreID);
    public GenreDTO findGenreDTOByTitle(String genreTitle);
    public List<GenreDTO> findAll();
}
