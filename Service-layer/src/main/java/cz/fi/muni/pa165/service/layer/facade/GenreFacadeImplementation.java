/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.GenreDTO;
import cz.fi.muni.pa165.api.layer.facade.GenreFacade;
import cz.fi.muni.pa165.entity.Genre;
import cz.fi.muni.pa165.service.layer.service.GenreService;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jergus Fasanek
 */
@Service
@Transactional
public class GenreFacadeImplementation implements GenreFacade {

    @Inject
    private MappingService mappingService;

    @Inject
    private GenreService genreService;

    @Override
    public Long createGenre(GenreDTO genre) {
        Genre mappedGenre = mappingService.mapTo(genre, Genre.class);
        return genreService.createGenre(mappedGenre).getId();
    }

    @Override
    public boolean deleteGenre(Long genreID) {
        return genreService.deleteGenre(genreService.findGenreByID(genreID));
    }

    @Override
    public GenreDTO updateTitle(Long genreID, String newTitle) {
        return mappingService.mapToEnforceID(genreService.updateTitle(genreService.findGenreByID(genreID), newTitle), GenreDTO.class);
    }

    @Override
    public GenreDTO updateYearOfOrigin(Long genreID, int newYearOfOrigin) {
        return mappingService.mapToEnforceID(genreService.updateYearOfOrigin(genreService.findGenreByID(genreID), newYearOfOrigin), GenreDTO.class);
    }

    @Override
    public GenreDTO findGenreByID(Long genreID) {
        if (genreService.findGenreByID(genreID) != null) {
            return mappingService.mapToEnforceID(genreService.findGenreByID(genreID), GenreDTO.class);
        } else {
            throw new NoResultException();
        }
    }

    @Override
    public GenreDTO findGenreByTitle(String genreTitle) {
        if (genreService.findGenreByTitle(genreTitle) != null) {
            return mappingService.mapToEnforceID(genreService.findGenreByTitle(genreTitle), GenreDTO.class);
        } else {
            throw new NoResultException();
        }
    }

    @Override
    public List<GenreDTO> findAll() {
        return mappingService.mapToCollectionEnforceID(genreService.findAll(), GenreDTO.class);
    }

    @Override
    public List<GenreDTO> findAllGenresInYearRange(int from, int to) {
        return mappingService.mapToCollectionEnforceID(genreService.findAllGenresInYearRange(from, to), GenreDTO.class);
    }

    @Override
    public List<GenreDTO> findAllGenresByYearOfOriginOrdered(boolean ascending) {
        return mappingService.mapToCollectionEnforceID(genreService.findAllGenresByYearOfOriginOrdered(ascending), GenreDTO.class);
    }
}
