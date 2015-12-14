/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.api.layer.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Peter Franek
 */
public class GenreSearchDTO {
    
    private Long genreId;
    
    private String title;
    
    private int yearOfOrigin;
    
    private int fromYear;
    private int toYear;
    private boolean sortASC;
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getYearOfOrigin(){
        return this.yearOfOrigin;
    }
    
    public void setYearOfOrigin(int year){
        this.yearOfOrigin = year;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public boolean isSortASC() {
        return sortASC;
    }

    public void setSortASC(boolean sortASC) {
        this.sortASC = sortASC;
    }
    
    
    
}
