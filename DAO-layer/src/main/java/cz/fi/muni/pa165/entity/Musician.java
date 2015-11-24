/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Jergus Fasanek
 */
@Entity
public class Musician {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable=false)
    private String realName;
    
    @NotNull
    private String artistName;
    
    private Date dateOfBirth;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName the realName to set
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return the artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artistName the artistName to set
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    
    @Override
    public boolean equals(Object object){
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }
        Musician other = (Musician) object;
        if (this.getRealName()!= null ? !Objects.equals(this.getRealName(), other.getRealName()) : other.getRealName() != null){
            return false;
        }
        
        if (this.getArtistName()!= null ? !Objects.equals(this.getArtistName(), other.getArtistName()) : other.getArtistName() != null){
            return false;
        }
        
        return !(this.getDateOfBirth()!= null ? !Objects.equals(this.getDateOfBirth(), other.getDateOfBirth()) : other.getDateOfBirth() != null);
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + realName.hashCode();
        hash = hash * 31 + artistName.hashCode();
        return hash;
    }
    
    @Override
    public String toString() {
        return "real name: " + realName + ", artist name: " + artistName + 
                ", date of birth" + dateOfBirth;
    }
    
}
