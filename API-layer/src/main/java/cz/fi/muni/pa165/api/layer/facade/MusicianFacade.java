/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.MusicianDTO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author JJ
 */
public interface MusicianFacade {
    public Long createMusicianDTO(MusicianCreateDTO musician);
    public boolean deleteMusicianDTO(Long musicianID);
    public MusicianDTO updateRealName(Long musicianID, String newName);
    public MusicianDTO updateArtistName(Long musicianID, String newName);
    public MusicianDTO updateDateOfBirth(Long musicianID, Date date);
    public MusicianDTO findMusicianDTOByID(Long musicianID);
    public MusicianDTO findMusicianDTOByRealName(String realName);
    public MusicianDTO findMusicianDTOByArtistName(String artistName);
    public List<MusicianDTO> findAll();
    
}
