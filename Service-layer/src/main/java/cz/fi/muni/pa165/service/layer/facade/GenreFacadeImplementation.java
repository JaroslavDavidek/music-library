/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.GenreDTO;
import cz.fi.muni.pa165.api.layer.facade.GenreFacade;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jergus Fasanek
 */
@Service
@Transactional
public class GenreFacadeImplementation implements GenreFacade{
    
    @Inject
    private MappingService mappingService;

    @Override
    public Long createGenre(GenreDTO genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteGenre(Long genreID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenreDTO updateTitle(Long genreID, String newTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenreDTO updateYearOfOrigin(Long genreID, int newYearOfOrigin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenreDTO findGenreDTOByID(Long genreID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenreDTO findGenreDTOByTitle(String genreTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GenreDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
