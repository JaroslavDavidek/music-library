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
    private Date birthDate;
    
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
    
    public Date getBirthDate() {
        return this.birthDate;
    }
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + realName.hashCode();
        hash = hash * 31 + artistName.hashCode();
        return hash;
    }
    
}
