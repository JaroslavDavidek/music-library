/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.MusicianDTO;
import cz.fi.muni.pa165.api.layer.facade.MusicianFacade;
import cz.fi.muni.pa165.entity.Musician;
import cz.fi.muni.pa165.service.layer.service.MappingService;
import cz.fi.muni.pa165.service.layer.service.MusicianService;
import java.sql.Date;
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
public class MusicianFacadeImplementation implements MusicianFacade{

    @Inject
    private MappingService mappingService;
    
    @Inject 
    private MusicianService musicianService;
    
    @Override
    public Long createMusician(MusicianDTO musicianDto) {
        Musician musician = mappingService.mapTo(musicianDto, Musician.class);
        return musicianService.createMusician(musician).getId();
    }

    @Override
    public boolean deleteMusician(Long musicianID) {
        return musicianService.deleteMusician(musicianService.findMusicianByID(musicianID));
    }

    @Override
    public MusicianDTO updateRealName(Long musicianID, String newName) {
        return mappingService.mapTo(musicianService.updateRealName(musicianService.findMusicianByID(musicianID), newName), MusicianDTO.class);
    }

    @Override
    public MusicianDTO updateArtistName(Long musicianID, String newName) {
        return mappingService.mapTo(musicianService.updateArtistName(musicianService.findMusicianByID(musicianID), newName), MusicianDTO.class);
    }

    @Override
    public MusicianDTO updateDateOfBirth(Long musicianID, Date date) {
        return mappingService.mapTo(musicianService.updateDateOfBirth(musicianService.findMusicianByID(musicianID), date), MusicianDTO.class);
    }

    @Override
    public MusicianDTO findMusicianByID(Long musicianID) {
        return mappingService.mapTo(musicianService.findMusicianByID(musicianID), MusicianDTO.class);
    }

    @Override
    public MusicianDTO findMusicianByRealName(String realName) {
        return mappingService.mapTo(musicianService.findMusicianByRealName(realName), MusicianDTO.class);
    }

    @Override
    public MusicianDTO findMusicianByArtistName(String artistName) {
        return mappingService.mapTo(musicianService.findMusicianByArtistName(artistName), MusicianDTO.class);
    }

    @Override
    public List<MusicianDTO> findAll() {
        return mappingService.mapToCollection(musicianService.findAll(), MusicianDTO.class);
    }
    
    @Override
    public List<MusicianDTO> findAllMusiciansInYearRange(int from, int to){
        return mappingService.mapToCollection(musicianService.findAllMusiciansInYearRange(from, to), MusicianDTO.class);
    }
}
