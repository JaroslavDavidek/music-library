package cz.fi.muni.pa165.api.layer.dto;

import java.sql.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * 
 * @author Jergus Fasanek
 */

public class MusicianDTO {
    
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 128)
    private String realName;
    
    @NotNull
    @Size(min = 1, max = 128)
    private String artistName;
    
    @Past
    private Date dateOfBirth;
    
    public Long getId() {
        return this.id;
    }
    
    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getArtistName() {
        return this.artistName;
    }
    
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void setDateOfBirth(Date birthDate) {
        this.dateOfBirth = birthDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + realName.hashCode();
        hash = hash * 31 + artistName.hashCode();
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object == this){
            return true;
        }
        if (object == null || !(object instanceof MusicianDTO)){
            return false;
        }       
        MusicianDTO other = (MusicianDTO) object;
        
        if (this.getRealName()!= null ? !Objects.equals(this.getRealName(), other.getRealName()) : other.getRealName() != null){
            return false;
        }
        
        if (this.getArtistName()!= null ? !Objects.equals(this.getArtistName(), other.getArtistName()) : other.getArtistName() != null){
            return false;
        }
        
        return !(this.getDateOfBirth()!= null ? !Objects.equals(this.getDateOfBirth(), other.getDateOfBirth()) : other.getDateOfBirth() != null);
    }
    
    @Override
    public String toString() {
        return "real name: " + realName + ", artist name: " + artistName + 
                ", date of birth" + dateOfBirth;
    }
}
