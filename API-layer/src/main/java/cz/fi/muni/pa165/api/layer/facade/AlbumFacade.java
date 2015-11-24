/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import java.util.List;

/**
 *
 * @author Peter Franek
 */
public interface AlbumFacade {
    Long createAlbum(AlbumDTO a);

    boolean deleteAlbum(Long albumId);

    List<AlbumDTO> findAll();

    AlbumDTO findById(Long id);

    AlbumDTO findByTitle(String title);
    
    AlbumDTO addSong(Long albumId, Long songId);

    AlbumDTO removeSong(Long albumId, Long songId);
}
