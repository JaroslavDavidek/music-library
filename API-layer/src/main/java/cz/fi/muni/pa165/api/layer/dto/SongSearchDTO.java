/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.layer.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JaroslavDavidek
 */
public class SongSearchDTO {
    
    private Long songId;
    
    private String title;
    
    private Long albumId;
    
    private Long musicianId;
    
    private Long genreId;
    
    private boolean performOrderedSearch;
    
    private boolean sortASC;
    
    private int fromYear;
    
    private int toYear;
    
    public Long getSongId() {
        return this.songId;
    }
    
    public void setSongId(Long songIdToSet) {
        this.songId = songIdToSet;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String titleToSet) {
        this.title = titleToSet;
    }
       
    public Long getAlbumId() {
        return this.albumId;
    }
    
    public void setAlbumId(Long albumIdToSet) {
        this.albumId = albumIdToSet;
    }
    
    public Long getGenreId() {
        return this.genreId;
    }
    
    public void setGenreId(Long genreIdToSet) {
        this.genreId = genreIdToSet;
    }
    
    public Long getMusicianId() {
        return this.musicianId;
    }
    
    public void setMusicianId(Long musicianIdToSet) {
        this.musicianId = musicianIdToSet;
    } 
    
    public boolean getPerformOrderedSearch() {
        return this.performOrderedSearch;
    }
    
    public void setPerformOrderedSearch(boolean performOrderedSearchToSet) {
        this.performOrderedSearch = performOrderedSearchToSet;
    }
    
    public boolean getSortASC() {
        return this.sortASC;
    }
    
    public void setSortASC(boolean sortASCToSet) {
        this.sortASC = sortASCToSet;
    }
    
    public int getFromYear() {
        return this.fromYear;
    }
    
    public void setFromYear(int fromYearToSet) {
        this.fromYear = fromYearToSet;
    }
    
    public int getToYear() {
        return this.toYear;
    }
    
    public void setToYear(int toYearToSet) {
        this.toYear = toYearToSet;
    }
}
