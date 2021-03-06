/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.layer.facade;

import cz.fi.muni.pa165.api.layer.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.api.layer.dto.AlbumDTO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Peter Franek
 */
public interface AlbumFacade {
    Long createAlbum(AlbumDTO a);
    
    long createAlbum(AlbumCreateDTO a);

    boolean deleteAlbum(Long albumId);
    
    AlbumDTO updateAlbumTitle(Long albumId, String title);
    
    AlbumDTO updateAlbumReleaseDate(Long albumId, Date releasedate);
    
    AlbumDTO updateAlbumMusician(long albumId, long musicianId);
    
    AlbumDTO updateAlbumCommentary(long albumId, String commentary);

    List<AlbumDTO> findAll();

    AlbumDTO findById(Long id);

    AlbumDTO findByTitle(String title);
    
    List<AlbumDTO> findAllAlbumsFromYears(int from, int to);
    
    List<AlbumDTO> findAllAlbumsOfMusician(long musicianId);
    
    AlbumDTO addSong(Long albumId, Long songId);

    AlbumDTO removeSong(Long albumId, Long songId);
}
