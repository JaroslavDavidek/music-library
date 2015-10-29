/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Peter Franek
 */
@Entity
public class Musician {
    @Id
    private Long id;
    
    private String artistName;
    
    public Musician(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getArtistName() {
        return this.artistName;
    }
    
    public void setArtistName(String artistNameToSet) {
        this.artistName = artistNameToSet;
    }
}
